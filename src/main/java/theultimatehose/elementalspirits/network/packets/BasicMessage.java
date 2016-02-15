package theultimatehose.elementalspirits.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import theultimatehose.elementalspirits.network.Syncer;

public class BasicMessage implements IMessage {

    public Syncer.TargetType targetType;
    /**
     * This is x-y-z for TileEntities and the EntityID for Entities
     */
    public int[] targetPos;
    public String targetClass;
    public String syncClass;

    @SuppressWarnings("unused")
    public BasicMessage() {}

    public BasicMessage(Syncer.TargetType targetType, int[] targetPos, Class targetClass, Class syncClass) {
        this.targetType = targetType;
        this.targetPos = targetPos;
        this.targetClass = targetClass.getName();
        this.syncClass = syncClass.getName();
    }

    /*
     * R/W Order:
     * boolean  type        - true for TE, false for E
     * int      pos         - world
     * int      pos         - x / entityID
     * int      pos         - y [if TE]
     * int      pos         - z [if TE]
     * int      targetClass - length
     * char[]   targetClass - Name
     * int      syncClass   - length
     * char[]   syncClass   - Name
     */

    @Override
    public void fromBytes(ByteBuf buf) {
        this.targetType = (buf.readBoolean() ? Syncer.TargetType.TileEntity : Syncer.TargetType.Entity);
        this.targetPos = new int[]{buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt()};

        this.targetClass = "";
        int textlength = buf.readInt();
        for (int i = 0; i < textlength; i++) {
            char c = buf.readChar();
            if (((int)c) != 0)
                this.targetClass += c;
        }
        this.syncClass = "";
        textlength = buf.readInt();
        for (int i = 0; i < textlength; i++) {
            char c = buf.readChar();
            if (((int)c) != 0)
                this.syncClass += c;
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(targetType == Syncer.TargetType.TileEntity);
        buf.writeInt(targetPos[0]);
        buf.writeInt(targetPos[1]);
        if (targetPos.length > 2) {
            buf.writeInt(targetPos[2]);
            buf.writeInt(targetPos[3]);
        } else {
            buf.writeInt(0);
            buf.writeInt(0);
        }

        buf.writeInt(targetClass.length());
        for (int i = 0; i < targetClass.length(); i++) {
            if (((int)targetClass.charAt(i)) != 0)
                buf.writeChar(targetClass.charAt(i));
        }
        buf.writeInt(syncClass.length());
        for (int i = 0; i < syncClass.length(); i++) {
            if (((int)syncClass.charAt(i)) != 0)
                buf.writeChar(syncClass.charAt(i));
        }
    }
}
