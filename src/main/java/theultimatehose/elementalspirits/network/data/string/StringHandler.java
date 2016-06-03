package theultimatehose.elementalspirits.network.data.string;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import theultimatehose.elementalspirits.network.Syncer;

public class StringHandler {

    public static class ServerHandler implements IMessageHandler<StringPacket, IMessage> {

        @Override
        public IMessage onMessage(StringPacket message, MessageContext ctx) {
            World world = DimensionManager.getWorld(message.targetPos[0]);

            if (message.targetType == Syncer.TargetType.Entity) {
                Entity target = world.getEntityByID(message.targetPos[1]);
                IStringSyncer is = (IStringSyncer) target;
                is.setStringData(message.data);
            } else if (message.targetType == Syncer.TargetType.TileEntity) {
                TileEntity target = world.getTileEntity(new BlockPos(message.targetPos[1], message.targetPos[2], message.targetPos[3]));
                IStringSyncer is = (IStringSyncer) target;
                is.setStringData(message.data);
            }

            return null;
        }
    }

    public static class ClientHandler implements IMessageHandler<StringPacket, IMessage> {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(StringPacket message, MessageContext ctx) {
            World world = DimensionManager.getWorld(message.targetPos[0]);

            if (message.targetType == Syncer.TargetType.Entity) {
                Entity target = world.getEntityByID(message.targetPos[1]);
                IStringSyncer is = (IStringSyncer) target;
                is.setStringData(message.data);
            } else if (message.targetType == Syncer.TargetType.TileEntity) {
                TileEntity target = world.getTileEntity(new BlockPos(message.targetPos[1], message.targetPos[2], message.targetPos[3]));
                IStringSyncer is = (IStringSyncer) target;
                is.setStringData(message.data);
            }

            return null;
        }
    }

}
