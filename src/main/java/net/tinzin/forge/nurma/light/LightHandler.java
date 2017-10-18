package net.tinzin.forge.nurma.light;

import com.elytradev.mirage.event.GatherLightsEvent;
import com.elytradev.mirage.lighting.Light;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class LightHandler {
    public static ArrayList<Light> lights = new ArrayList<>();

    @SubscribeEvent
    public static void gatherLights (GatherLightsEvent event) {
        for (int i = 0; i < lights.size() ; i++) {
            event.getLightList().add(lights.get(i));
        }
    }
}
