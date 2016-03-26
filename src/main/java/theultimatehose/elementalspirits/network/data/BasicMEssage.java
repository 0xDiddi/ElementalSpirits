package theultimatehose.elementalspirits.network.data;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import theultimatehose.elementalspirits.network.Syncer;

public class BasicMessage implements IMessage {

    public Syncer.TargetType targetType;
    public int[] targetPos;

    public BasicMessage() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        this.targetType = (buf.readBoolean() ? Syncer.TargetType.TileEntity : Syncer.TargetType.Entity);
        this.targetPos = new int[]{buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt()};
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
    }
}
