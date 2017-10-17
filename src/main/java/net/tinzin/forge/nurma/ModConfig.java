package net.tinzin.forge.nurma;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Nurma.modId)
@Config.LangKey("nurma.config.title")
public class ModConfig {
    public static final Client client = new Client();

    public static class Client{
        @Config.Comment("audio settings")
        public final Sounds sound = new Sounds();

        public static class Sounds{
            @Config.Comment("Automatable tile sounds")
            @Config.Name("Tile Sounds On")
            public boolean tile_sounds_on = true;
        }
    }

    @Mod.EventBusSubscriber(modid = Nurma.modId)
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Nurma.modId)) {
                ConfigManager.sync(Nurma.modId, Config.Type.INSTANCE);
            }
        }
    }
}
