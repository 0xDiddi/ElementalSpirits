package theultimatehose.elementalspirits.entity.ai;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import theultimatehose.elementalspirits.entity.EntityElementalBase;

import java.util.UUID;

public class ElementalAIFollowMaster extends EntityAIBase {

    public EntityElementalBase elemental;
    public EntityPlayer master;
    public PathNavigate navigator;

    public ElementalAIFollowMaster(EntityElementalBase entity) {
        this.elemental = entity;
        navigator = entity.getNavigator();
    }

    @Override
    public boolean shouldExecute() {

        try {
            master = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(elemental.getMaster());
        } catch (IllegalArgumentException ignored) {
            //When ending up here, there is no UUID, so the master must be null, so returning false
            return false;
        }

        if (master == null) return false;
        if (master.isSpectator()) return false;
        else if (this.elemental.getDistanceSqToEntity(master) < 6) return false;
        else return elemental.getFollowMaster();

    }

    @Override
    public void resetTask() {
        this.navigator.clearPathEntity();
    }

    @Override
    public boolean continueExecuting() {
        return !this.navigator.noPath() && this.elemental.getDistanceSqToEntity(this.master) > 4 && elemental.getFollowMaster();
    }

    @Override
    public void updateTask() {
        this.elemental.getLookHelper().setLookPositionWithEntity(this.master, 10, this.elemental.getVerticalFaceSpeed());

        this.navigator.tryMoveToEntityLiving(this.master, 1.5);
    }
}
