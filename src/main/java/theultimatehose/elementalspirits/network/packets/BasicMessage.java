package theultimatehose.elementalspirits.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import theultimatehose.elementalspirits.network.Syncer;

public class BasicMessage implements IMessage {

    public int worldID;
    public Syncer.TargetType targetType;
    /**
     * This is x-y-z for TileEntities and the EntityID for Entities
     */
    public int[] targetPos;

    public BasicMessage(int worldID, Syncer.TargetType targetType, int[] targetPos) {
        this.worldID = worldID;
        this.targetType = targetType;
        this.targetPos = targetPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }
}
