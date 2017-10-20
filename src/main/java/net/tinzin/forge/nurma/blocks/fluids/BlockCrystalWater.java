package net.tinzin.forge.nurma.blocks.fluids;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.tinzin.forge.nurma.Nurma;

public class BlockCrystalWater extends BlockFluidClassic{
    protected String name;

    public BlockCrystalWater(Fluid fluid, String name){
        super(fluid, Material.WATER);
        this.quantaPerBlock = 8;
        this.quantaPerBlockFloat = 8f;
        this.tickRate = 7;

        this.name = name;

        setUnlocalizedName(Nurma.modId+".fluid."+name);
        setRegistryName(name);
    }
}
