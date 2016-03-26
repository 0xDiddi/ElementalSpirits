package theultimatehose.elementalspirits.network.data.integer;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import theultimatehose.elementalspirits.network.Syncer;

public class IntHandler {

    public static class ServerHandler implements IMessageHandler<IntPacket, IMessage> {

        @Override
        public IMessage onMessage(IntPacket message, MessageContext ctx) {
            World world = DimensionManager.getWorld(message.targetPos[0]);

            if (message.targetType == Syncer.TargetType.Entity) {
                Entity target = world.getEntityByID(message.targetPos[1]);
                IIntegerSyncer is = (IIntegerSyncer) target;
                is.setIntData(message.data);
            } else if (message.targetType == Syncer.TargetType.TileEntity) {
                TileEntity target = world.getTileEntity(new BlockPos(message.targetPos[1], message.targetPos[2], message.targetPos[3]));
                IIntegerSyncer is = (IIntegerSyncer) target;
                is.setIntData(message.data);
            }

            return null;
        }
    }

    public static class ClientHandler implements IMessageHandler<IntPacket, IMessage> {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(IntPacket message, MessageContext ctx) {
            World world = DimensionManager.getWorld(message.targetPos[0]);

            if (message.targetType == Syncer.TargetType.Entity) {
                Entity target = world.getEntityByID(message.targetPos[1]);
                IIntegerSyncer is = (IIntegerSyncer) target;
                is.setIntData(message.data);
            } else if (message.targetType == Syncer.TargetType.TileEntity) {
                TileEntity target = world.getTileEntity(new BlockPos(message.targetPos[1], message.targetPos[2], message.targetPos[3]));
                IIntegerSyncer is = (IIntegerSyncer) target;
                is.setIntData(message.data);
            }

            return null;
        }
    }

}
