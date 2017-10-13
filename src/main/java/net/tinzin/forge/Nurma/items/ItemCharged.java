package net.tinzin.forge.Nurma.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCharged extends ItemBase {
    public ItemCharged(String name, int maxStack) {
        super(name+"_charged", maxStack);
    }
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
}
