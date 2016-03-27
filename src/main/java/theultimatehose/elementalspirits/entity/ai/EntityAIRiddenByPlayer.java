package theultimatehose.elementalspirits.entity.ai;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import theultimatehose.elementalspirits.entity.EntityElementalBase;

import java.util.UUID;

public class EntityAIRiddenByPlayer extends EntityAIBase {

    public EntityElementalBase elemental;
    public EntityPlayer master;
    public EntityPlayer rider;

    public EntityAIRiddenByPlayer(EntityElementalBase elemental) {
        this.elemental = elemental;
        this.master = null;
    }

    @Override
    public boolean shouldExecute() {
        this.rider = (EntityPlayer) this.elemental.riddenByEntity;

        return this.rider != null;

    }

    @Override
    public boolean continueExecuting() {
        return this.rider != null;
    }

    @Override
    public void updateTask() {
        this.rider = (EntityPlayer) this.elemental.riddenByEntity;
        if (this.rider == null) return;

        this.elemental.rotationYaw = this.rider.rotationYaw;

        if (this.rider.isSprinting()) {
            System.out.println("sprint");
            this.elemental.moveEntityWithHeading(0, 1);
        }

    }


}
