package theultimatehose.elementalspirits.network.data.integer;

import io.netty.buffer.ByteBuf;
import theultimatehose.elementalspirits.network.data.BasicMessage;

public class IntPacket extends BasicMessage {

    public int[] data;

    @SuppressWarnings("unused")
    public IntPacket() {}

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
