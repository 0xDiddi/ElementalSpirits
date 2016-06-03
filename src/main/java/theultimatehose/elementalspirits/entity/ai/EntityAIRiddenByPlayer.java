package theultimatehose.elementalspirits.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import theultimatehose.elementalspirits.entity.EntityElementalBase;
import theultimatehose.elementalspirits.util.VanillaStuffUtil;

public class EntityAIRiddenByPlayer extends EntityAIBase {

    public EntityElementalBase elemental;
    public EntityPlayer master;
    public EntityPlayer rider;
    public boolean boosted;
    public float boost_speed;
    public int ticks_since_boost;

    public EntityAIRiddenByPlayer(EntityElementalBase elemental) {
        this.elemental = elemental;
        this.master = null;
        this.boosted = false;
        this.boost_speed = 0;
        this.setMutexBits(0b1111111);
    }

    @Override
    public boolean shouldExecute() {
        this.rider = (EntityPlayer) this.elemental.getRidingEntity();

        return this.rider != null && this.boosted;

    }

    @Override
    public boolean continueExecuting() {
        return this.rider != null && this.boosted;
    }

    @Override
    public void resetTask() {
        this.boosted = false;
    }

    @Override
    public void updateTask() {
        this.rider = (EntityPlayer) this.elemental.getRidingEntity();
        if (this.rider == null) return;

        if (this.boosted) {
            ticks_since_boost++;
            this.elemental.rotationYaw = this.rider.rotationYaw;

            VanillaStuffUtil.doEntityJump(this.elemental, this.boost_speed);

            this.elemental.moveEntityWithHeading(0, this.boost_speed);
        }
    }

    public void boost() {
        if (this.ticks_since_boost >= 0 && this.ticks_since_boost < 15 && this.boosted) {
            if (this.boost_speed < 1)
                this.boost_speed += 0.25f;
        } else if (this.boosted) {
            this.boosted = false;
            this.ticks_since_boost = 0;
            this.boost_speed = 0;
        } else {
            this.boosted = true;
            this.boost_speed = 0.25f;
        }
    }

}
