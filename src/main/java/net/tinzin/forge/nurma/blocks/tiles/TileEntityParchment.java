package net.tinzin.forge.nurma.blocks.tiles;

import com.elytradev.concrete.inventory.ConcreteItemStorage;
import com.elytradev.concrete.inventory.IContainerInventoryHolder;
import com.elytradev.concrete.inventory.ValidatedInventoryView;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

public class TileEntityParchment extends TileEntity implements IContainerInventoryHolder{
    @Override
    public IInventory getContainerInventory() {
        return new ValidatedInventoryView(new ConcreteItemStorage(0).withName(""));
    }
}
