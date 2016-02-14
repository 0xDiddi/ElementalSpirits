package theultimatehose.elementalspirits.network.packets.int_packet;

import io.netty.buffer.ByteBuf;
import theultimatehose.elementalspirits.network.Syncer;
import theultimatehose.elementalspirits.network.packets.BasicMessage;

public class IntMessage extends BasicMessage {

    public int[] data;

    public IntMessage(int worldID, Syncer.TargetType targetType, int[] targetPos) {
        super(worldID, targetType, targetPos);

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }
}
