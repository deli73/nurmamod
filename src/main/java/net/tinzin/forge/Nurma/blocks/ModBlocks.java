package net.tinzin.forge.Nurma.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static BlockBase stonePolished = new BlockRock("stone_polished").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
            stonePolished
        );
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
            stonePolished.createItemBlock()
        );
    }

    public static void registerItemModels() {
        stonePolished.registerItemModel(Item.getItemFromBlock(stonePolished));
    }
}
