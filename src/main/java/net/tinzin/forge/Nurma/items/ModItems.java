package net.tinzin.forge.Nurma.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
    public static ItemBase tabletStone = new ItemBase("tablet_stone", 16).setCreativeTab(CreativeTabs.MISC);
    public static ItemBase knife = new ToolBase("knife",100,2,(float)-.5);

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                tabletStone,
                knife
        );
    }

    public static void registerModels() {
        tabletStone.registerItemModel();
        knife.registerItemModel();
    }
}
