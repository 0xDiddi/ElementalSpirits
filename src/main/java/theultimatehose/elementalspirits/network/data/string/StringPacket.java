package theultimatehose.elementalspirits.network.data.string;

import io.netty.buffer.ByteBuf;
import theultimatehose.elementalspirits.network.data.BasicMessage;

public class StringPacket extends BasicMessage {

    public String[] data;

    @SuppressWarnings("unused")
    public StringPacket() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        this.data = new String[buf.readInt()];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = "";
            int textlength = buf.readInt();
            for (int j = 0; j < textlength; j++) {
                this.data[i] += buf.readChar();
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(data.length);
        for (String s : data) {
            buf.writeInt(s.length());
            for (int i = 0; i < s.length(); i++) {
                buf.writeChar(s.charAt(i));
            }
        }
    }
}
