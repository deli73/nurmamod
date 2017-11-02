package net.tinzin.forge.nurma.items;

import net.minecraft.item.ItemStack;
import net.tinzin.forge.nurma.Nurma;

import java.util.Locale;

public class ItemHorsehair extends ItemBase {
    public enum EnumHorsehairColor{
        WHITE, CREAMY, CHESTNUT, BROWN, BLACK;
        public final String lowerName = name().toLowerCase(Locale.ROOT);
        public String getColorString(){
            return lowerName;

        }
        public static EnumHorsehairColor getColorFromMeta(int meta){
            return values()[meta];
        }
        public static int getMetaFromColor(EnumHorsehairColor color){
            return color.ordinal();
        }
    }

    public ItemHorsehair(){
        super("horsehair",64,"horsehair");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        return "item." + Nurma.modId + ".horsehair." + EnumHorsehairColor.getColorFromMeta(meta).getColorString();
    }

    @Override
    public void registerItemModel() {
        for(EnumHorsehairColor c : EnumHorsehairColor.values()){
            Nurma.proxy.registerItemRenderer(this, EnumHorsehairColor.getMetaFromColor(c),name+"_"+c.getColorString());
        }
    }
}
