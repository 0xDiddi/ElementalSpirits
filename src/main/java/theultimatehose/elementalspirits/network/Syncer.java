package theultimatehose.elementalspirits.network;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import theultimatehose.elementalspirits.network.packets.int_packet.IntHandler;
import theultimatehose.elementalspirits.network.packets.int_packet.IntMessage;
import theultimatehose.elementalspirits.util.Util;

import java.lang.reflect.Method;

public class Syncer {

    public static SimpleNetworkWrapper network;

    public static void init() {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Util.MOD_ID_LOWER);

        network.registerMessage(IntHandler.class, IntMessage.class, 0, Side.SERVER);
    }

    public static void syncEntity(Entity entity) {
        Class entityClass = entity.getClass();
        Method[] methods = entityClass.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(SyncMethodGet.class)) {
                Class returnType = m.getReturnType();
                if (returnType == int.class) {
                    //todo: sync int
                } else if (returnType == boolean.class) {
                    //todo: sync boolean
                } else if (returnType == String.class) {
                    //todo: sync string
                } else {
                    throw new UnsupportedSyncTypeException("Tried to sync unsyncable object of type " + returnType.toString());
                }
            }
        }
    }

    /**
     *Returns the get method in the class
     * The Method needs to have the {@link SyncMethodGet} annotation set
     * Invocation will (probably) fail if it has any args so args are not accepted.
     * @param theClass The class to search for the get method
     * @param returnValue The necessary return value class
     * @return The get method which can be invoked
     */
    public static Method getGetMethodForValue(Class theClass, Class returnValue) {
        for (Method m : theClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(SyncMethodGet.class)) {
                if (m.getReturnType() == returnValue && m.getParameterTypes().length == 0)
                    return m;
            }
        }
        return null;
    }

    /**
     * Returns the set method in the class.
     * The method needs to have the {@link SyncMethodSet} annotation set
     * and must only have one parameter of the given type.
     * That parameter may be an array or ...-type
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
