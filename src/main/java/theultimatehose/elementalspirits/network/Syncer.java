package theultimatehose.elementalspirits.network;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import theultimatehose.elementalspirits.network.packets.int_packet.IntHandler;
import theultimatehose.elementalspirits.network.packets.int_packet.IntMessage;
import theultimatehose.elementalspirits.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Syncer {

    public static SimpleNetworkWrapper network;

    public static void init() {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Util.MOD_ID_LOWER);

        network.registerMessage(IntHandler.class, IntMessage.class, 0, Side.SERVER);
    }

    public static void sync(Object obj) {
        Class syncingClass = obj.getClass();
        Method[] methods = syncingClass.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(SyncMethodGet.class) && m.getParameterTypes().length == 0) {
                TargetType target = getTargetType(obj);
                int[] pos = getPosForTargetType(target, obj);
                Class returnType = m.getReturnType();
                Object data = null;
                try {
                    data = m.invoke(obj);
                } catch (Exception ignored) {}
                if (returnType == int[].class) {
                    IntMessage msg = new IntMessage(target, pos, syncingClass, int[].class.cast(data));
                    network.sendToServer(msg);
                } else if (returnType == boolean[].class) {
                    //todo: sync boolean
                } else if (returnType == String[].class) {
                    //todo: sync string
                } else {
                    throw new UnsupportedSyncTypeException("Tried to sync unsyncable object of type " + returnType.toString());
                }
            }
        }
    }

    private static TargetType getTargetType(Object obj) {
        if (obj instanceof Entity)
            return TargetType.Entity;
        else if (obj instanceof TileEntity)
            return TargetType.TileEntity;

        return null;
    }

    private static int[] getPosForTargetType(TargetType type, Object obj) {
        int[] i = null;
        if (type == TargetType.Entity) {
            Entity e = (Entity) obj;
            i = new int[] {e.getEntityWorld().provider.getDimensionId(), e.getEntityId()};
        } else if (type == TargetType.TileEntity) {
            TileEntity e = (TileEntity) obj;
            BlockPos pos = e.getPos();
            i = new int[] {e.getWorld().provider.getDimensionId(), pos.getX(), pos.getY(), pos.getZ()};
        }
        return i;
    }

    /**
     * Returns the set method in the class.
     * The method needs to have the {@link SyncMethodSet} annotation set
     * and must only have one parameter of the given type.
     * That parameter has to be an array
     * @param theClass The class to search for the set method
     * @param parameter The necessary parameter class
     * @return The set method which can be invoked
     */
    public static Method getSetMethodForValue(Class theClass, Class parameter) {
        for (Method m : theClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(SyncMethodSet.class)) {
                if (m.getParameterTypes().length > 0) {
                    for (Class c : m.getParameterTypes()) {
                        if (c != parameter)
                            return null;
                    }
                    return m;
                }
            }
        }
        return null;
    }

    public enum TargetType {
        Entity, TileEntity
    }

    public static class UnsupportedSyncTypeException extends RuntimeException {
        public UnsupportedSyncTypeException(String s) {
            super(s);
        }
    }

}
