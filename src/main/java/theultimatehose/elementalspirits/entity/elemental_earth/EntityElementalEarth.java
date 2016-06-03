package theultimatehose.elementalspirits.entity.elemental_earth;

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
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import theultimatehose.elementalspirits.entity.EntityElementalBase;
import theultimatehose.elementalspirits.entity.ai.ElementalAIFollowMaster;
import theultimatehose.elementalspirits.entity.ai.EntityAIRiddenByPlayer;
import theultimatehose.elementalspirits.entity.elemental_earth.actions.ActionApplyStrength;
import theultimatehose.elementalspirits.entity.elemental_earth.actions.ActionInfuse;
import theultimatehose.elementalspirits.entity.elemental_earth.actions.ActionMount;
import theultimatehose.elementalspirits.item.ItemEarthRod;
import theultimatehose.elementalspirits.network.Syncer;
import theultimatehose.elementalspirits.network.data.integer.IIntegerSyncer;
import theultimatehose.elementalspirits.overlay.IOverlayProvider;
import theultimatehose.elementalspirits.overlay.Overlay;
import theultimatehose.elementalspirits.overlay.OverlayEarthElemental;
import theultimatehose.elementalspirits.overlay.WheelInteraction;

import javax.annotation.Nullable;
import java.util.HashMap;

public class EntityElementalEarth extends EntityElementalBase implements IIntegerSyncer, IOverlayProvider, WheelInteraction.IWheelInteractionProvider {

    private int riderID;
    private boolean shouldUpdateRider;

    public EntityAIRiddenByPlayer aiRiddenByPlayer;

    public EntityElementalEarth(World worldIn) {
        super(worldIn);
        this.setSize(0.8f, 1);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiRiddenByPlayer = new EntityAIRiddenByPlayer(this));
        this.tasks.addTask(3, new ElementalAIFollowMaster(this));
        this.tasks.addTask(5, new EntityAITempt(this, 1, Items.EMERALD, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1));
        this.tasks.addTask(10, new EntityAILookIdle(this));

    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack) {
        if (!worldObj.isRemote) {
            if (!isTamed()) {
                ItemStack inUse = player.inventory.getCurrentItem();
                int i = -1;

                if (inUse != null) {
                    Item iInUse = inUse.getItem();

                    if (iInUse == Items.EMERALD)
                        i = getRNG().nextInt(8);
                    else if (iInUse == Items.DIAMOND)
                        i = getRNG().nextInt(16);
                    else if (iInUse == Items.QUARTZ)
                        i = getRNG().nextInt(24);
                    else if (iInUse == Items.REDSTONE)
                        i = getRNG().nextInt(32);
                    else if (iInUse == Items.COAL)
                        i = getRNG().nextInt(64);
                    else if (iInUse == Item.getItemFromBlock(Blocks.STONE))
                        i = getRNG().nextInt(128);
                    else if (iInUse == Item.getItemFromBlock(Blocks.GRAVEL))
                        i = getRNG().nextInt(256);
                    else if (iInUse == Item.getItemFromBlock(Blocks.COBBLESTONE))
                        i = getRNG().nextInt(512);

                    if (i >= 0) {
                        inUse.stackSize--;
                        if (inUse.stackSize <= 0)
                            inUse = null;
                        player.inventory.setItemStack(inUse);
                        if (i == 7) {
                            setMaster(player.getUniqueID());
                            setIsTamed(true);
                            return true;
                        }
                    }
                }
            } else if (player.getGameProfile().getId().equals(getMaster())) {
                if (player.inventory.getCurrentItem().getItem() instanceof ItemEarthRod) {
                    NBTTagCompound compound = player.inventory.getCurrentItem().getTagCompound();
                    if (compound == null)
                        compound = new NBTTagCompound();
                    if (compound.getBoolean(ItemEarthRod.KEY_GREATER))
                        compound.setBoolean(ItemEarthRod.KEY_INFUSED, true);
                    player.inventory.getCurrentItem().setTagCompound(compound);
                    return true;
                } else {
                    setFollowMaster(!getFollowMaster());
                    player.addChatMessage(new TextComponentString(getFollowMaster() ? "Now following you." : "No longer following you."));
                    return true;
                }
            }
        }
        return super.processInteract(player, hand, stack);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
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
                    //TODO: Fix mounting entities
                    //worldObj.getEntityByID(riderID).mountEntity(this);
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

    @Override
    public Overlay getOverlay() {
        return new OverlayEarthElemental();
    }

    @Override
    public HashMap<Overlay.Position, WheelInteraction.Action> getActions() {
        HashMap<Overlay.Position, WheelInteraction.Action> map = new HashMap<>(8);
        map.put(Overlay.Position.top_center, new ActionMount(this));
        map.put(Overlay.Position.top_right, new ActionInfuse(this, FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(this.getMaster())));
        map.put(Overlay.Position.center_left, new ActionApplyStrength(FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(this.getMaster())));
        return map;
    }
}
