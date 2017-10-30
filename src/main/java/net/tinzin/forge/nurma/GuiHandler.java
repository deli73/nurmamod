package net.tinzin.forge.nurma;

import com.elytradev.concrete.inventory.gui.ConcreteContainer;
import com.elytradev.concrete.inventory.gui.client.ConcreteGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tinzin.forge.nurma.blocks.tiles.*;

public class GuiHandler implements IGuiHandler {
    public static final int GRINDER = 0;
    public static final int PARCHMENT = 1;

    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GRINDER:
                return new ContainerGrinder(player.inventory, (TileEntityGrinder) world.getTileEntity(new BlockPos(x, y, z)));
            case PARCHMENT:
                return new ContainerParchment(player.inventory, ((TileEntityParchment) world.getTileEntity(new BlockPos(x, y, z))).getContainerInventory());
            default:
                return null;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GRINDER:
                return new GuiGrinder(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            case PARCHMENT:
                ConcreteContainer container = new ContainerParchment(player.inventory, ((TileEntityParchment) world.getTileEntity(new BlockPos(x, y, z))).getContainerInventory());
                return new ConcreteGui(container);
            default:
                return null;
        }
    }
}
