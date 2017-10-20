package net.tinzin.forge.nurma.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.tinzin.forge.nurma.Nurma;
import net.tinzin.forge.nurma.blocks.fluids.BlockCrystalWater;
import net.tinzin.forge.nurma.blocks.tiles.BlockGrinder;

public class ModBlocks {

    public static Fluid fluidCrystal = new Fluid("nurma:crystal",
            new ResourceLocation("nurma", "blocks/fluids/crystal_still"),
            new ResourceLocation("nurma", "blocks/fluids/crystal_flowing"))
            .setDensity(1030) //approximately the density of saltwater
            .setTemperature(275) // nearly freezing!!
            .setRarity(EnumRarity.EPIC);

    public static BlockBase stonePolished = new BlockRock("stone_polished").setCreativeTab(Nurma.creativeTab);
    public static BlockGrinder grinder = new BlockGrinder();
    public static BlockInkstone inkstone = new BlockInkstone();
    public static BlockWood crystalwood = new BlockWood("crystal_log");
    public static BlockCrystalWater crystalwater = new BlockCrystalWater(fluidCrystal,"fluid_crystal");

    public static IBlockBase[] allBlocks = {
            stonePolished, grinder, inkstone, crystalwood
    };

    public static void register(IForgeRegistry<Block> registry) {
        for (int i = 0; i < allBlocks.length; i++) {
            IBlockBase block = allBlocks[i];
            registry.register(block.toBlock());
        }
        GameRegistry.registerTileEntity(grinder.getTileEntityClass(), grinder.getRegistryName().toString());
        fluidCrystal.setBlock(ModBlocks.crystalwater);
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        for (int i = 0; i < allBlocks.length; i++) {
            IBlockBase block = allBlocks[i];
            registry.register(block.createItemBlock());
        }
    }

    public static void registerItemModels() {
        for (int i = 0; i < allBlocks.length; i++) {
            IBlockBase block = allBlocks[i];
            block.registerItemModel(Item.getItemFromBlock(block.toBlock()));
        }
    }
}
