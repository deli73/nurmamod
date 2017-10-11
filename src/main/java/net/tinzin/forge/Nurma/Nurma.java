package net.tinzin.forge.Nurma;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.tinzin.forge.Nurma.blocks.ModBlocks;
import net.tinzin.forge.Nurma.items.ModItems;
import net.tinzin.forge.Nurma.proxy.CommonProxy;
import net.tinzin.forge.Nurma.sound.SoundRegisterListener;


@Mod(modid = Nurma.modId, name = Nurma.name, version = Nurma.version)
public class Nurma {
    public static final String modId = "nurma";
    public static final String name  = "Nurma";
    public static final String version = "1.0.0";

    @Mod.Instance(modId)
    public static Nurma instance;

    @SidedProxy(serverSide = "net.tinzin.forge.Nurma.proxy.CommonProxy", clientSide = "net.tinzin.forge.Nurma.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is loading!");
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new SoundRegisterListener());
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
