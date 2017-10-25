package net.tinzin.forge.nurma.items;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import net.tinzin.forge.nurma.Nurma;
import net.tinzin.forge.nurma.items.armor.ArmorHelmetCrystal;

public class ModItems {
    public static ItemBase tabletStone = new ItemBase("tablet_stone", 16).setCreativeTab(Nurma.creativeTab);
    public static ItemBase knife = new ToolBase("knife",100,2,-.5f);
    public static ItemBase netherCore = new ItemBase("nether_core",1).setCreativeTab(Nurma.creativeTab);
    public static ItemBase netherCoreCharged = new ItemCharged("nether_core",1);

    //refined crystals
    public static ItemBase refinedEmerald = new ItemBase("refined_emerald",1).setCreativeTab(Nurma.creativeTab);
    public static ItemBase refinedDiamond = new ItemBase("refined_diamond",1).setCreativeTab(Nurma.creativeTab);
    public static ItemBase refinedPrismarine = new ItemBase("refined_prismarine",1).setCreativeTab(Nurma.creativeTab);
    public static ItemBase refinedQuartz = new ItemBase("refined_quartz",1).setCreativeTab(Nurma.creativeTab);

    //failed/repeated refines
    public static ItemBase shatteredEmerald = new ItemBase("shattered_emerald").setCreativeTab(Nurma.creativeTab);
    public static ItemBase shatteredDiamond = new ItemBase("shattered_diamond").setCreativeTab(Nurma.creativeTab);
    public static ItemBase glassShard = new ItemBase("glass_shard").setCreativeTab(Nurma.creativeTab);

    //misc refined stuff
    public static ItemBase goldDust = new ItemBase("dust_gold",64,"dustGold").setCreativeTab(Nurma.creativeTab);

    //ink components
    public static ItemBase soot = new ItemBase("soot",64,"dyeBlack").setCreativeTab(Nurma.creativeTab);
    public static ItemBase inkstick = new ItemInkstick("inkstick").setCreativeTab(Nurma.creativeTab);

    //??
    public static ItemBase lantern = new ItemLantern("lantern").setCreativeTab(Nurma.toolsTab);
    public static ItemBase crystal = new ItemBase("crystal").setCreativeTab(Nurma.creativeTab);

    //armor
    public static ArmorHelmetCrystal helmetCrystal = new ArmorHelmetCrystal();

    public static ItemBase[] allItems = {tabletStone,
            knife, netherCore, netherCoreCharged,
            refinedEmerald, refinedDiamond, refinedPrismarine, refinedQuartz,
            shatteredEmerald, shatteredDiamond, glassShard,
            goldDust,
            soot, inkstick,
            lantern, crystal};

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(allItems);
        registry.register(helmetCrystal);
    }

    public static void registerModels() {
        for (int i = 0; i < allItems.length ; i++) {
            allItems[i].registerItemModel();
        }
        helmetCrystal.registerItemModel();
    }

    public static void registerOreDict() {
        for (int i = 0; i < allItems.length ; i++) {
            allItems[i].initOreDict();
        }
    }
}
