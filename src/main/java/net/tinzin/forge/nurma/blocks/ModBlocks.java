package net.tinzin.forge.nurma.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.tinzin.forge.nurma.Nurma;
import net.tinzin.forge.nurma.blocks.fluids.BlockCrystalWater;
import net.tinzin.forge.nurma.blocks.tiles.BlockGrinder;

public class ModBlocks {

    public static Fluid fluidCrystal = new Fluid("crystal",
            new ResourceLocation("nurma", "blocks/fluids/crystal_still"),
            new ResourceLocation("nurma", "blocks/fluids/crystal_flowing"))
            .setDensity(1030) //approximately the density of saltwater
            .setTemperature(275) // nearly freezing!!
            .setRarity(EnumRarity.EPIC);

    public static BlockBase stonePolished = new BlockRock("stone_polished").setCreativeTab(Nurma.creativeTab);
    public static BlockBase adornedStoneEmerald = new BlockRock("adorned_stone_emerald").setCreativeTab(Nurma.creativeTab);
    public static BlockBase adornedStoneDiamond = new BlockRock("adorned_stone_diamond").setCreativeTab(Nurma.creativeTab);
    public static BlockBase adornedStoneQuartz = new BlockRock("adorned_stone_quartz").setCreativeTab(Nurma.creativeTab);
    public static BlockGrinder grinder = new BlockGrinder();
    public static BlockInkstone inkstone = new BlockInkstone();
    public static BlockWood crystalwood = new BlockWood("crystal_log");
    public static BlockCrystalWater crystalwater;
    public static BlockBase crystalLattice = new BlockBase(Material.GLASS,"crystal_lattice");
    public static BlockBase lamp = new BlockLight();

    public static IBlockBase[] allBlocks = {
            stonePolished,
            adornedStoneEmerald, adornedStoneDiamond, adornedStoneQuartz,
            grinder, inkstone, crystalwood, crystalLattice,
            lamp
    };

    public static void register(IForgeRegistry<Block> registry) {
        for (int i = 0; i < allBlocks.length; i++) {
            IBlockBase block = allBlocks[i];
            registry.register(block.toBlock());
        }
        GameRegistry.registerTileEntity(grinder.getTileEntityClass(), grinder.getRegistryName().toString());
        FluidRegistry.registerFluid(ModBlocks.fluidCrystal);
        BlockCrystalWater crystalwater = new BlockCrystalWater(fluidCrystal,"fluid_crystal");
        registry.register(crystalwater);
        fluidCrystal.setBlock(crystalwater);
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
