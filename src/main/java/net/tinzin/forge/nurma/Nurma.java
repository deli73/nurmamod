package net.tinzin.forge.nurma;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.tinzin.forge.nurma.blocks.ModBlocks;
import net.tinzin.forge.nurma.items.ItemHorsehair;
import net.tinzin.forge.nurma.items.ModItems;
import net.tinzin.forge.nurma.network.PacketResultSound;
import net.tinzin.forge.nurma.proxy.CommonProxy;
import net.tinzin.forge.nurma.sound.SoundRegisterListener;
import net.tinzin.forge.nurma.tabs.NurmaTab;
import net.tinzin.forge.nurma.tabs.ToolTab;
import net.tinzin.forge.nurma.worldgen.ModWorldGeneration;

import java.util.Random;


@Mod(modid = Nurma.modId, name = Nurma.name, version = Nurma.version)
public class Nurma {
    public static final String modId = "nurma";
    public static final String name  = "nurma";
    public static final String version = "1.0.0";

    @Mod.Instance(modId)
    public static Nurma instance;

    @SidedProxy(serverSide = "net.tinzin.forge.nurma.proxy.CommonProxy", clientSide = "net.tinzin.forge.nurma.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static SimpleNetworkWrapper network;

    public static final NurmaTab creativeTab = new NurmaTab();
    public static final ToolTab toolsTab = new ToolTab();

    static {
        //blame Falkreon if this breaks something
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is loading!");
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        network = NetworkRegistry.INSTANCE.newSimpleChannel(modId);
        network.registerMessage(new PacketResultSound.Handler(), PacketResultSound.class, 0, Side.CLIENT);

        GameRegistry.registerWorldGenerator(new ModWorldGeneration(), 3);

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new SoundRegisterListener());
        //MinecraftForge.EVENT_BUS.register(LightHandler.class);
        ModItems.registerOreDict(); // register oredict

        LootTableList.register(new ResourceLocation(modId,"soot"));
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerItemModels();
        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }
        @SubscribeEvent
        public static void lootTables(LootTableLoadEvent event){
            if(event.getName().equals(LootTableList.ENTITIES_BLAZE)){
                ResourceLocation soot = new ResourceLocation(modId,"inject/soot");
                LootEntry entry = new LootEntryTable(soot, 1, 0, new LootCondition[]{}, "inject/soot");
                LootPool pool = new LootPool(new LootEntry[] {entry}, new LootCondition[]{}, new RandomValueRange(1,1), new RandomValueRange(0,1), "inject/soot");
                event.getTable().addPool(pool);
            }
            if (event.getName().toString().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
                ResourceLocation soot = new ResourceLocation(modId,"inject/simple_dungeon");
                LootEntry entry = new LootEntryTable(soot, 1, 0, new LootCondition[]{}, "inject/simple_dungeon");
                LootPool pool = new LootPool(new LootEntry[] {entry}, new LootCondition[]{}, new RandomValueRange(0,1), new RandomValueRange(0,1), "inject/simple_dungeon");
                event.getTable().addPool(pool);
            }
        }
    }

    @Mod.EventBusSubscriber
    public static class EventHandler{
        @SubscribeEvent
        public static void rightClick(PlayerInteractEvent.EntityInteract event){
            ItemStack item = event.getEntityPlayer().getHeldItem(event.getHand());
            Entity entity = event.getTarget();

            if(entity instanceof EntityHorse && !entity.world.isRemote && item.getItem() instanceof ItemShears){
                EntityHorse horse = (EntityHorse) entity;

                ItemHorsehair.EnumHorsehairColor color;
                switch(horse.getHorseVariant() & 0xFF){ // horse color
                    case 0: color = ItemHorsehair.EnumHorsehairColor.WHITE; break;
                    case 1: color = ItemHorsehair.EnumHorsehairColor.CREAMY; break;
                    case 2: color = ItemHorsehair.EnumHorsehairColor.CHESTNUT; break;
                    case 3:
                    case 6: color = ItemHorsehair.EnumHorsehairColor.BROWN; break;
                    case 4:
                    case 5: color = ItemHorsehair.EnumHorsehairColor.BLACK; break;
                    default: return;
                }

                ItemStack drop = new ItemStack(ModItems.horsehair);
                drop.setItemDamage(ItemHorsehair.EnumHorsehairColor.getMetaFromColor(color));
                Random rand = horse.world.rand;
                EntityItem dropEntity = horse.entityDropItem(drop, 1F);
                dropEntity.motionY += rand.nextFloat() * 0.05F;
                dropEntity.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                dropEntity.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                item.damageItem(1, horse);

                horse.world.playSound(horse.posX,horse.posY,horse.posZ, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1, 1, true);
                event.setCanceled(true);
            }
        }
    }
}
