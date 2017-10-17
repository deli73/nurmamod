package net.tinzin.forge.nurma.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tinzin.forge.nurma.Nurma;
import net.tinzin.forge.nurma.items.ModItems;

public class NurmaTab extends CreativeTabs {
    public NurmaTab(){
        super(Nurma.modId);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.refinedEmerald);
    }
}
