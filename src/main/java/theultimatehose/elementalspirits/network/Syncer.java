package theultimatehose.elementalspirits.network;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import theultimatehose.elementalspirits.network.packets.BasicMessage;
import theultimatehose.elementalspirits.network.packets.ClientHandler;
import theultimatehose.elementalspirits.network.packets.ServerHandler;
import theultimatehose.elementalspirits.network.packets.messages.BoolMessage;
import theultimatehose.elementalspirits.network.packets.messages.IntMessage;
import theultimatehose.elementalspirits.network.packets.messages.StringMessage;
import theultimatehose.elementalspirits.util.Util;

import java.lang.reflect.Method;

public class Syncer {

    public static SimpleNetworkWrapper network;

    /**
     * Initializes the NetworkChannel, the messages and the Handlers.
     * To be called during initialization phase
     */
    public static void init() {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Util.MOD_ID_LOWER);

        network.registerMessage(ServerHandler.class, IntMessage.class, 0, Side.SERVER);
        network.registerMessage(ClientHandler.class, IntMessage.class, 1, Side.CLIENT);
    }

    /**
     * Syncs all available Values to the given {@link Side}. <br>
     * For each value which should be synchronised, there must be a method marked
     * with {@link SyncMethodGet} and another one marked with {@link SyncMethodSet}
     * The get-method must not take arguments and return an array of a valid type and
     * the set-method will be given an array of the synced values. <br>
     * The valid types are: <br>
     *     - int <br>
     *     - boolean <br>
     *     - String
     * @param obj The Entity or TileEntity to be synced
     * @param targetSide the {@link Side} on which the packet should be received
     */
    public static void sync(Object obj, Side targetSide) {
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

                BasicMessage msg;

                if (returnType == int[].class) {
                    msg = new IntMessage(target, pos, syncingClass, int[].class.cast(data));
                } else if (returnType == boolean[].class) {
                    msg = new BoolMessage(target, pos, syncingClass, boolean[].class.cast(data));
                } else if (returnType == String[].class) {
                    msg = new StringMessage(target, pos, syncingClass, String[].class.cast(data));
                } else {
                    throw new UnsupportedSyncTypeException("Tried to sync unsyncable object of type " + returnType.toString());
                }

                if (targetSide == Side.SERVER)
                    network.sendToServer(msg);
                else if (targetSide == Side.CLIENT)
                    network.sendToDimension(msg, pos[0]);

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
