package net.tinzin.forge.Nurma.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
    public static ItemBase tabletStone = new ItemBase("tablet_stone", 16).setCreativeTab(CreativeTabs.MISC);
    public static ItemBase knife = new ToolBase("knife",100,2,(float)-.5);

    //refined crystals
    public static ItemBase refinedEmerald = new ItemBase("refined_emerald",1).setCreativeTab(CreativeTabs.MATERIALS);
    public static ItemBase refinedDiamond = new ItemBase("refined_diamond",1).setCreativeTab(CreativeTabs.MATERIALS);
    public static ItemBase refinedPrismarine = new ItemBase("refined_prismarine",1).setCreativeTab(CreativeTabs.MATERIALS);
    public static ItemBase refinedQuartz = new ItemBase("refined_quartz",1).setCreativeTab(CreativeTabs.MATERIALS);

    //failed/repeated refines
    public static ItemBase shatteredEmerald = new ItemBase("shattered_emerald").setCreativeTab(CreativeTabs.MATERIALS);
    public static ItemBase shatteredDiamond = new ItemBase("shattered_diamond").setCreativeTab(CreativeTabs.MATERIALS);
    public static ItemBase glassShard = new ItemBase("glass_shard").setCreativeTab(CreativeTabs.MATERIALS);

    //misc refined stuff
    public static ItemBase goldDust = new ItemBase("dust_gold").setCreativeTab(CreativeTabs.MATERIALS);

    public static ItemBase[] allItems = {tabletStone,
            knife,
            refinedEmerald, refinedDiamond, refinedPrismarine, refinedQuartz,
            shatteredEmerald, shatteredDiamond, glassShard,
            goldDust};

    public static void register(IForgeRegistry<Item> registry) {
        for (int i = 0; i < allItems.length ; i++) {
            registry.register(allItems[i]);
        }
    }

    public static void registerModels() {
        for (int i = 0; i < allItems.length ; i++) {
            allItems[i].registerItemModel();
        }
    }
}
