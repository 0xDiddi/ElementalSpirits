package theultimatehose.elementalspirits.network.packets;

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
import theultimatehose.elementalspirits.network.packets.messages.IntMessage;

import java.lang.reflect.Method;

public class ClientHandler implements IMessageHandler<IntMessage, IMessage> {

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"PrimitiveArrayArgumentToVariableArgMethod", "unchecked"})
    public IMessage onMessage(IntMessage message, MessageContext ctx) {
        World world = DimensionManager.getWorld(message.targetPos[0]);
        try {
            if (message.targetType == Syncer.TargetType.Entity) {
                Class<Entity> target = (Class<Entity>) Class.forName(message.targetClass);
                Entity entity = target.cast(world.getEntityByID(message.targetPos[1]));
                Method m = Syncer.getSetMethodForValue(entity.getClass(), Class.forName(message.syncClass));
                if (m != null) {
                    m.invoke(entity, message.data);
                }
            } else if (message.targetType == Syncer.TargetType.TileEntity) {
                Class<TileEntity> target = (Class<TileEntity>) Class.forName(message.targetClass);
                TileEntity entity = target.cast(world.getTileEntity(new BlockPos(message.targetPos[1], message.targetPos[2], message.targetPos[3])));
                Method m = Syncer.getSetMethodForValue(entity.getClass(), Class.forName(message.syncClass));
                if (m != null) {
                    m.invoke(entity, message.data);
                }
            }
        } catch (Exception ignored) {}
        return null;
    }
 }
