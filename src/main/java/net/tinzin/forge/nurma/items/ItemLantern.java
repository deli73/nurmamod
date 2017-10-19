package net.tinzin.forge.nurma.items;


import com.elytradev.mirage.lighting.IColoredLight;
import com.elytradev.mirage.lighting.Light;

import javax.annotation.Nullable;

public class ItemLantern extends ItemBase implements IColoredLight {
    private static float r = 0.717647059f;
    private static float g = 0.843137255f;
    private static float b = 0.803921569f;
    private static float radius = 13;

    public ItemLantern (String name) {
        super(name, 1);
    }

    //TODO: move light cretion code to something that iterates over players in LightHandler


    @Nullable
    @Override
    public Light getColoredLight() {
        Light light = new Light.Builder().color(r,g,b).radius(radius).pos(0,0,0).build();
        return light;
    }
}
