package theultimatehose.elementalspirits.network.packets.messages;

import io.netty.buffer.ByteBuf;
import theultimatehose.elementalspirits.network.Syncer;
import theultimatehose.elementalspirits.network.packets.BasicMessage;

public class IntMessage extends BasicMessage {

    public int[] data;

    public IntMessage() {}

    public IntMessage(Syncer.TargetType targetType, int[] targetPos, Class targetClass, int[] data) {
        super(targetType, targetPos, targetClass, int[].class);
        this.data = data;
    }

    /*
     * R/W Order:
     * int      length
     * int...   data
     */

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        this.data = new int[buf.readInt()];
        for (int i = 0; i < data.length; i++) {
            data[i] = buf.readInt();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(data.length);
        for (int i : data) {
            buf.writeInt(i);
        }
    }
}
