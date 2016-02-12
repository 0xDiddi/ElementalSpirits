package theultimatehose.elementalspirits.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class EntityElementalBase extends EntityCreature {

    final int UUID_POS = 20;
    final int TAMED_POS = 21;
    final int FOLLOW_POS = 22;

    public EntityElementalBase(World worldIn) {
        super(worldIn);
        this.dataWatcher.addObject(UUID_POS, "");
        this.dataWatcher.addObject(TAMED_POS, 0);
        this.dataWatcher.addObject(FOLLOW_POS, 0);
    }

    @Override
    public boolean getCanSpawnHere() {
        return true;
    }

    @Override
    protected boolean canDespawn() {
        return !getIsTamed();
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
    }

    @Override
    public boolean interact(EntityPlayer player) {
        if (!getIsTamed()) {
            ItemStack inUse = player.getCurrentEquippedItem();
            if (inUse != null && inUse.getItem() == Items.emerald) {
                inUse.stackSize--;
                if (inUse.stackSize <= 0)
                    inUse = null;
                player.setCurrentItemOrArmor(0, inUse);
                if (getRNG().nextInt(16) == 7) {
                    setMaster(player.getGameProfile().getId().toString());
                    setIsTamed(true);
                    return true;
                }
            }
        } else if (player.getGameProfile().getId().toString() == getMaster()) {
            setFollowMaster(!getFollowMaster());
            player.addChatMessage(new ChatComponentText(getFollowMaster() ? "Now following you." : "No longer following you."));
            return true;
        }
        return super.interact(player);
    }

    public String getMaster() {
        return this.dataWatcher.getWatchableObjectString(UUID_POS);
    }

    public void setMaster(String player_uuid) {
        this.dataWatcher.updateObject(UUID_POS, player_uuid);
    }

    public boolean getIsTamed() {
        return this.dataWatcher.getWatchableObjectInt(TAMED_POS) == 1;
    }

    public void setIsTamed(boolean tamed) {
        this.dataWatcher.updateObject(TAMED_POS, (tamed ? 1 : 0));
    }

    public boolean getFollowMaster() {
        return this.dataWatcher.getWatchableObjectInt(FOLLOW_POS) == 1;
    }

    public void setFollowMaster(boolean follow) {
        this.dataWatcher.updateObject(FOLLOW_POS, (follow ? 1 : 0));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setString("master", getMaster());
        tagCompound.setBoolean("tamed", getIsTamed());
        tagCompound.setBoolean("follow", getFollowMaster());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        setMaster(tagCompund.getString("master"));
        setIsTamed(tagCompund.getBoolean("tamed"));
        setFollowMaster(tagCompund.getBoolean("follow"));
    }
}
