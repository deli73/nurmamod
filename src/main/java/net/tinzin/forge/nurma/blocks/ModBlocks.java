package net.tinzin.forge.nurma.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.tinzin.forge.nurma.blocks.tiles.BlockGrinder;

public class ModBlocks {

    public static BlockBase stonePolished = new BlockRock("stone_polished").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockGrinder grinder = new BlockGrinder();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
            stonePolished,
            grinder
        );
        GameRegistry.registerTileEntity(grinder.getTileEntityClass(), grinder.getRegistryName().toString());
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
            stonePolished.createItemBlock(),
            grinder.createItemBlock()
        );
    }

    public static void registerItemModels() {
        stonePolished.registerItemModel(Item.getItemFromBlock(stonePolished));
        grinder.registerItemModel(Item.getItemFromBlock(grinder));
    }
}
