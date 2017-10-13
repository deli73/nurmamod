package net.tinzin.forge.Nurma.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tinzin.forge.Nurma.sound.SoundRegistrator;

public class PacketResultSound implements IMessage {
    private BlockPos pos;
    private boolean success;

    public PacketResultSound(BlockPos pos, boolean success){
        this.pos = pos;
        this.success = success;
    }

    public PacketResultSound(){}

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeLong(pos.toLong());
        buf.writeBoolean(success);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        success = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketResultSound, IMessage>{
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketResultSound message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if (message.success) {
                    Minecraft.getMinecraft().world.playSound(message.pos, SoundRegistrator.GRIND, SoundCategory.BLOCKS, 1F, 1.0F, false);
                }
                else{
                    Minecraft.getMinecraft().world.playSound(message.pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1F, 1.0F, false);
                }
            });
            return null;
        }
    }
}
