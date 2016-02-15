package theultimatehose.elementalspirits.network.packets.messages;

import io.netty.buffer.ByteBuf;
import theultimatehose.elementalspirits.network.Syncer;
import theultimatehose.elementalspirits.network.packets.BasicMessage;

public class StringMessage extends BasicMessage {

    public String[] data;

    public StringMessage() {}

    public StringMessage(Syncer.TargetType targetType, int[] targetPos, Class targetClass, String[] data) {
        super(targetType, targetPos, targetClass, int[].class);
        this.data = data;
    }

    /*
     * R/W Order:
     * String      length
     * String...   data
     */

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        this.data = new String[buf.readInt()];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = "";
            int textlength = buf.readInt();
            for (int j = 0; j < textlength; j++) {
                char c = buf.readChar();
                if (((int)c) != 0)
                    this.targetClass += c;
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
                if (((int)s.charAt(i)) != 0)
                    buf.writeChar(s.charAt(i));
            }
        }
    }

}
