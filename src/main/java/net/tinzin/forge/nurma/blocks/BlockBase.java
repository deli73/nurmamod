package net.tinzin.forge.nurma.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.tinzin.forge.nurma.Nurma;

public class BlockBase extends Block implements IBlockBase{
    protected String name;

    public BlockBase(Material material, String name) {
        super(material);

        this.name = name;

        setUnlocalizedName(Nurma.modId+"."+name);
        setRegistryName(name);
    }

    public void registerItemModel(Item itemBlock) {
        Nurma.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    public Block toBlock(){
        return this;
    }
}
