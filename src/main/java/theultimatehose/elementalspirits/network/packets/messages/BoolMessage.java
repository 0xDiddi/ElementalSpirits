package theultimatehose.elementalspirits.network.packets.messages;

import io.netty.buffer.ByteBuf;
import theultimatehose.elementalspirits.network.Syncer;
import theultimatehose.elementalspirits.network.packets.BasicMessage;

public class BoolMessage extends BasicMessage {

    public boolean[] data;

    public BoolMessage() {}

    public BoolMessage(Syncer.TargetType targetType, int[] targetPos, Class targetClass, boolean[] data) {
        super(targetType, targetPos, targetClass, int[].class);
        this.data = data;
    }

    /*
     * R/W Order:
     * boolean      length
     * boolean...   data
     */

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        this.data = new boolean[buf.readInt()];
        for (int i = 0; i < data.length; i++) {
            data[i] = buf.readBoolean();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(data.length);
        for (boolean i : data) {
            buf.writeBoolean(i);
        }
    }

}
