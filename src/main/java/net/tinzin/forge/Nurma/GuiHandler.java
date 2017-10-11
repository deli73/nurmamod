package net.tinzin.forge.Nurma;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.tinzin.forge.Nurma.blocks.tiles.ContainerGrinder;
import net.tinzin.forge.Nurma.blocks.tiles.GuiGrinder;
import net.tinzin.forge.Nurma.blocks.tiles.TileEntityGrinder;

public class GuiHandler implements IGuiHandler {
    public static final int GRINDER = 0;

    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GRINDER:
                return new ContainerGrinder(player.inventory, (TileEntityGrinder) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GRINDER:
                return new GuiGrinder(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            default:
                return null;
        }
    }
}
