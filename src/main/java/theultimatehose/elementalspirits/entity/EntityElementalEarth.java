package theultimatehose.elementalspirits.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.entity.ai.ElementalAIFollowMaster;
import theultimatehose.elementalspirits.entity.ai.EntityAIRiddenByPlayer;
import theultimatehose.elementalspirits.network.Syncer;
import theultimatehose.elementalspirits.network.data.integer.IIntegerSyncer;

public class EntityElementalEarth extends EntityElementalBase implements IIntegerSyncer {

    final int UPDATE_RIDER_POS = 23;
    int riderID;
    boolean shouldUpdateRider;
    public EntityAIRiddenByPlayer aiRiddenByPlayer;

    public EntityElementalEarth(World worldIn) {
        super(worldIn);
        this.setSize(0.8f, 1);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiRiddenByPlayer = new EntityAIRiddenByPlayer(this));
        this.tasks.addTask(3, new ElementalAIFollowMaster(this));
        this.tasks.addTask(5, new EntityAITempt(this, 1, Items.emerald, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1));
        this.tasks.addTask(10, new EntityAILookIdle(this));

        this.dataWatcher.addObject(UPDATE_RIDER_POS, 0);

    }

    @Override
    public boolean interact(EntityPlayer player) {
        if (!worldObj.isRemote) {
            if (!getIsTamed()) {
                ItemStack inUse = player.getCurrentEquippedItem();
                int i = -1;

                if (inUse != null) {
                    Item iInUse = inUse.getItem();

                    if (iInUse == Items.emerald)
                        i = getRNG().nextInt(8);
                    else if (iInUse == Items.diamond)
                        i = getRNG().nextInt(16);
                    else if (iInUse == Items.quartz)
                        i = getRNG().nextInt(24);
                    else if (iInUse == Items.redstone)
                        i = getRNG().nextInt(32);
                    else if (iInUse == Items.coal)
                        i = getRNG().nextInt(64);
                    else if (iInUse == Item.getItemFromBlock(Blocks.stone))
                        i = getRNG().nextInt(128);
                    else if (iInUse == Item.getItemFromBlock(Blocks.gravel))
                        i = getRNG().nextInt(256);
                    else if (iInUse == Item.getItemFromBlock(Blocks.cobblestone))
                        i = getRNG().nextInt(512);

                    if (i >= 0) {
                        inUse.stackSize--;
                        if (inUse.stackSize <= 0)
                            inUse = null;
                        player.setCurrentItemOrArmor(0, inUse);
                        if (i == 7) {
                            setMaster(player.getGameProfile().getId().toString());
                            setIsTamed(true);
                            return true;
                        }
                    }
                }
            } else if (player.getGameProfile().getId().toString().equals(getMaster())) {
                if (player.getCurrentEquippedItem().getItem() == ElementalSpirits.instance.itemEarthRod) {
                    NBTTagCompound compound = player.getCurrentEquippedItem().getTagCompound();
                    if (compound == null)
                        compound = new NBTTagCompound();
                    compound.setBoolean("isInfused", true);
                    player.getCurrentEquippedItem().setTagCompound(compound);
                    return true;
                } else {
                    setFollowMaster(!getFollowMaster());
                    player.addChatMessage(new ChatComponentText(getFollowMaster() ? "Now following you." : "No longer following you."));
                    return true;
                }
            }
        }
        return super.interact(player);
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
        Syncer.syncInt(this, Side.SERVER);
    }

    @Override
    public boolean canBeSteered() {
        return false;
    }

    @Override
    public int[] getIntData() {
        return new int[] {riderID};
    }

    @Override
    public void setIntData(int[] data) {
        this.riderID = data[0];
        this.shouldUpdateRider = true;
    }
}
