package net.tinzin.forge.nurma.items;

import com.elytradev.mirage.lighting.Light;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tinzin.forge.nurma.light.LightHandler;

public class ItemLantern extends ItemBase {
    private static float r = 0.717647059f;
    private static float g = 0.843137255f;
    private static float b = 0.803921569f;

    public ItemLantern (String name) {
        super(name, 1);
    }

    //TODO: move light cretion code to something that iterates over players in LightHandler

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        LightHandler.lights.clear();
        if(!isSelected) {return;}

        Light light = Light.builder().color(r,g,b).pos(entityIn).radius(13).build();

        LightHandler.lights.add(light);
    }
}
