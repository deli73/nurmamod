package net.tinzin.forge.nurma;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.tinzin.forge.nurma.blocks.ModBlocks;
import net.tinzin.forge.nurma.items.ModItems;
import net.tinzin.forge.nurma.network.PacketResultSound;
import net.tinzin.forge.nurma.proxy.CommonProxy;
import net.tinzin.forge.nurma.sound.SoundRegisterListener;
import net.tinzin.forge.nurma.tabs.NurmaTab;
import net.tinzin.forge.nurma.tabs.ToolTab;


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



    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new SoundRegisterListener());
        //MinecraftForge.EVENT_BUS.register(LightHandler.class);
        ModItems.registerOreDict(); // register oredict
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
    }
}
