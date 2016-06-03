package theultimatehose.elementalspirits.network;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import theultimatehose.elementalspirits.network.data.integer.IIntegerSyncer;
import theultimatehose.elementalspirits.network.data.integer.IntHandler;
import theultimatehose.elementalspirits.network.data.integer.IntPacket;
import theultimatehose.elementalspirits.network.data.string.IStringSyncer;
import theultimatehose.elementalspirits.network.data.string.StringHandler;
import theultimatehose.elementalspirits.network.data.string.StringPacket;
import theultimatehose.elementalspirits.util.Util;

public class Syncer {

    public static SimpleNetworkWrapper network;

    public static void init() {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Util.MOD_ID_LOWER);

        network.registerMessage(new IntHandler.ClientHandler(), IntPacket.class, 0, Side.CLIENT);
        network.registerMessage(new IntHandler.ServerHandler(), IntPacket.class, 1, Side.SERVER);

        network.registerMessage(new StringHandler.ClientHandler(), StringPacket.class, 2, Side.CLIENT);
        network.registerMessage(new StringHandler.ServerHandler(), StringPacket.class, 3, Side.SERVER);

    }

    public static void syncInt(Object o, Side targetSide) {
        if (!(o instanceof IIntegerSyncer))
            return;

        IntPacket packet = new IntPacket();
        packet.targetType = TargetType.Entity;
        packet.targetPos = getTargetPos(o);
        packet.data = ((IIntegerSyncer) o).getIntData();

        if (targetSide == Side.CLIENT) {
            network.sendToAllAround(packet, getTargetPoint(o));
        } else if (targetSide == Side.SERVER) {
            network.sendToServer(packet);
        }

    }

    public static void syncString(Object o, Side targetSide) {
        if (!(o instanceof IStringSyncer))
            return;

        StringPacket packet = new StringPacket();
        packet.targetType = TargetType.Entity;
        packet.targetPos = getTargetPos(o);
        packet.data = ((IStringSyncer) o).getStringData();

        if (targetSide == Side.CLIENT) {
            network.sendToAllAround(packet, getTargetPoint(o));
        } else if (targetSide == Side.SERVER) {
            network.sendToServer(packet);
        }

    }

    public static int[] getTargetPos(Object o) {
        if (o instanceof Entity) {
            Entity e = (Entity) o;
            return new int[] {e.getEntityWorld().provider.getDimension(), e.getEntityId()};
        } else if (o instanceof TileEntity) {
            TileEntity e = (TileEntity) o;
            return new int[] {e.getWorld().provider.getDimension(), e.getPos().getX(), e.getPos().getY(), e.getPos().getZ()};
        }
        return null;
    }

    public static NetworkRegistry.TargetPoint getTargetPoint(Object o) {
        if (o instanceof Entity) {
            Entity e = (Entity) o;
            return new NetworkRegistry.TargetPoint(e.getEntityWorld().provider.getDimension(), e.getPosition().getX(), e.getPosition().getY(), e.getPosition().getZ(), 128);
        } else if (o instanceof TileEntity) {
            TileEntity e = (TileEntity) o;
            return new NetworkRegistry.TargetPoint(e.getWorld().provider.getDimension(), e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), 128);
        }
        return null;
    }

    public enum TargetType {
        Entity, TileEntity
    }

}
