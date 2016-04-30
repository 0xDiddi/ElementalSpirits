package theultimatehose.elementalspirits.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.ElementalSpirits;

public class EntityElementalBase extends EntityCreature {

    private final int UUID_POS = 20;
    private final int TAMED_POS = 21;
    private final int FOLLOW_POS = 22;

    public EntityElementalBase(World worldIn) {
        super(worldIn);
        this.dataWatcher.addObject(UUID_POS, "");
        this.dataWatcher.addObject(TAMED_POS, 0);
        this.dataWatcher.addObject(FOLLOW_POS, 0);
    }

    @Override
    protected boolean interact(EntityPlayer player) {
        ItemStack stack = player.getCurrentEquippedItem();
        if (stack != null && stack.stackSize == 1 && stack.getItem() == Items.paper) {
            player.destroyCurrentEquippedItem();
            player.setCurrentItemOrArmor(0, new ItemStack(ElementalSpirits.INSTANCE.itemAncientScroll));
        }
        return super.interact(player);
    }

    @Override
    public boolean getCanSpawnHere() {
        return true;
    }

    @Override
    protected boolean canDespawn() {
        return !getIsTamed();
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
