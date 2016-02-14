package theultimatehose.elementalspirits.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.server.MinecraftServer;
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

        EntityPlayer plr = null;

        try {
            plr = MinecraftServer.getServer().getConfigurationManager().getPlayerByUUID(UUID.fromString(elemental.getMaster()));
        } catch (Exception ignored) {}

        if (plr == null) {
            return false;
        } else if (plr.isSpectator()) {
            return false;
        } else if (this.elemental.getDistanceSqToEntity(plr) < 6) {
            return false;
        }else if (!elemental.getFollowMaster()) {
            return false;
        } else {
            this.master = plr;
            return true;
        }

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
