package net.tinzin.forge.nurma.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.tinzin.forge.nurma.Nurma;
import net.tinzin.forge.nurma.blocks.tiles.BlockGrinder;

public class ModBlocks {

    public static BlockBase stonePolished = new BlockRock("stone_polished").setCreativeTab(Nurma.creativeTab);
    public static BlockGrinder grinder = new BlockGrinder();
    public static BlockInkstone inkstone = new BlockInkstone();

    public static BlockBase[] allBlocks = {
            stonePolished, grinder, inkstone
    };

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(allBlocks);
        GameRegistry.registerTileEntity(grinder.getTileEntityClass(), grinder.getRegistryName().toString());
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        for (int i = 0; i < allBlocks.length; i++) {
            BlockBase block = allBlocks[i];
            registry.register(block.createItemBlock());
        }
    }

    public static void registerItemModels() {
        for (int i = 0; i < allBlocks.length; i++) {
            BlockBase block = allBlocks[i];
            block.registerItemModel(Item.getItemFromBlock(block));
        }
    }
}
