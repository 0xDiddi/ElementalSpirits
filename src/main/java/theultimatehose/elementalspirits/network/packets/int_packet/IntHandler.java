package theultimatehose.elementalspirits.network.packets.int_packet;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import theultimatehose.elementalspirits.network.Syncer;

import java.lang.reflect.Method;

public class IntHandler implements IMessageHandler<IntMessage, IMessage> {

    @Override
    @SuppressWarnings("PrimitiveArrayArgumentToVariableArgMethod")
    public IMessage onMessage(IntMessage message, MessageContext ctx) {
        World world = DimensionManager.getWorld(message.worldID);
        try {
            if (message.targetType == Syncer.TargetType.Entity) {
                Entity entity = world.getEntityByID(message.targetPos[0]);
                Method m = Syncer.getSetMethodForValue(entity.getClass(), int.class);
                if (m != null) {
                    m.invoke(entity, message.data);
                }
            } else if (message.targetType == Syncer.TargetType.TileEntity) {
                TileEntity entity = world.getTileEntity(new BlockPos(message.targetPos[0], message.targetPos[1], message.targetPos[2]));
                Method m = Syncer.getSetMethodForValue(entity.getClass(), int.class);
                if (m != null) {
                    m.invoke(entity, message.data);
                }
            }
        } catch (Exception ignored) {}
        return null;
    }
 }
