package theultimatehose.elementalspirits.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import theultimatehose.elementalspirits.entity.ai.ElementalAIFollowMaster;
import theultimatehose.elementalspirits.network.SyncMethodGet;
import theultimatehose.elementalspirits.network.SyncMethodSet;
import theultimatehose.elementalspirits.network.Syncer;

public class EntityElementalEarth extends  EntityElementalBase {

    final int UPDATE_RIDER_POS = 23;
    int riderID;
    boolean shouldUpdateRider;

    public EntityElementalEarth(World worldIn) {
        super(worldIn);
        this.setSize(0.8f, 1);
        this.tasks.addTask(1, new EntityAIControlledByPlayer(this, 1f));
        this.tasks.addTask(2, new EntityAIWander(this, 1));
        this.tasks.addTask(3, new ElementalAIFollowMaster(this));
        this.tasks.addTask(5, new EntityAITempt(this, 1, Items.emerald, false));
        this.tasks.addTask(10, new EntityAILookIdle(this));

        this.dataWatcher.addObject(UPDATE_RIDER_POS, 0);

    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
    }

    @Override
    public float getEyeHeight() {
        return 1f;
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        if (!worldObj.isRemote) {
            if (shouldUpdateRider) {
                if (worldObj.getEntityByID(riderID) instanceof EntityPlayer) {
                    worldObj.getEntityByID(riderID).mountEntity(this);
                    shouldUpdateRider = false;
                }
            }
        }
    }

    public void scheduleRiderUpdate(EntityPlayer rider) {
        this.riderID = rider.getEntityId();
        Syncer.sync(this, Side.SERVER);
    }

    @SyncMethodGet
    public int[] getData() {
        return new int[] {riderID};
    }

    @SyncMethodSet
    public void setData(int[] data) {
        this.riderID = data[0];
        this.shouldUpdateRider = true;
    }

}
