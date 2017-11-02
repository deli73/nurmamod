package net.tinzin.forge.nurma.items;

import net.minecraft.item.ItemStack;
import net.tinzin.forge.nurma.Nurma;

public class ItemBrush extends ItemBase {
    public ItemBrush() {
        super("brush", 1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + Nurma.modId + ".brush." + (stack.getMetadata() == 0 ? "dry" : "inked");
    }

    @Override
    public void registerItemModel() {
        Nurma.proxy.registerItemRenderer(this, 0, name+"_dry");
        Nurma.proxy.registerItemRenderer(this, 1, name+"_inked");
    }


}
