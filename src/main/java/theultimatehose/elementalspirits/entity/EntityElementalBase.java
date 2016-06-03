package theultimatehose.elementalspirits.entity;

import com.google.common.base.Optional;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.item.ItemManager;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityElementalBase extends EntityCreature {

    private static final DataParameter<Optional<UUID>> MASTER_UUID = EntityDataManager.createKey(EntityElementalBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    private static final DataParameter<Boolean> IS_TAMED = EntityDataManager.createKey(EntityElementalBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> FOLLOW_MASTER = EntityDataManager.createKey(EntityElementalBase.class, DataSerializers.BOOLEAN);

    public EntityElementalBase(World worldIn) {
        super(worldIn);
        this.dataManager.register(MASTER_UUID, Optional.<UUID>absent());
        this.dataManager.register(IS_TAMED, false);
        this.dataManager.register(FOLLOW_MASTER, false);
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack) {
        if (stack != null && stack.stackSize == 1 && stack.getItem() == Items.PAPER) {
            player.inventory.setItemStack(player.inventory.decrStackSize(player.inventory.currentItem, 1));
            player.inventory.addItemStackToInventory(new ItemStack(ItemManager.itemAncientScroll));
        }
        return super.processInteract(player, hand, stack);
    }

    @Override
    public boolean getCanSpawnHere() {
        return true;
    }

    @Override
    protected boolean canDespawn() {
        return !isTamed();
    }

    public UUID getMaster() {
        return this.dataManager.get(MASTER_UUID).orNull();
    }

    public void setMaster(UUID uuid) {
        this.dataManager.set(MASTER_UUID, Optional.fromNullable(uuid));
    }

    public boolean isTamed() {
        return this.dataManager.get(IS_TAMED);
    }

    public void setIsTamed(boolean tamed) {
        this.dataManager.set(IS_TAMED, tamed);
    }

    public boolean getFollowMaster() {
        return this.dataManager.get(FOLLOW_MASTER);
    }

    public void setFollowMaster(boolean follow) {
        this.dataManager.set(FOLLOW_MASTER, follow);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setString("master", (getMaster() != null ? getMaster().toString() : ""));
        tagCompound.setBoolean("tamed", isTamed());
        tagCompound.setBoolean("follow", getFollowMaster());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        setMaster((!tagCompund.getString("master").equals("") ? UUID.fromString(tagCompund.getString("master")) : null));
        setIsTamed(tagCompund.getBoolean("tamed"));
        setFollowMaster(tagCompund.getBoolean("follow"));
    }
}
