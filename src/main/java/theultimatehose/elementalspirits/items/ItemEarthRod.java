package theultimatehose.elementalspirits.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.entity.elemental_earth.EntityElementalEarth;
import theultimatehose.elementalspirits.infusion.InfusionRitual;
import theultimatehose.elementalspirits.util.Util;

public class ItemEarthRod extends Item {

    public static final String KEY_INFUSED = "isInfused";
    public static final String KEY_GREATER = "isGreater";

    public static final String KEY_PERFORM_INFUSE = "shouldInfuse";
    public static final String KEY_INFUSE_NOTIFY = "shouldNotifyPlayer";

    public ItemEarthRod() {
        this.setMaxStackSize(1);
        ElementalSpirits.proxy.addSimpleRenderer(new ItemStack(this), new ResourceLocation(Util.MOD_ID_LOWER, Names.ITEM_EARTH_ROD));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        NBTTagCompound compound = stack.getTagCompound();
        if (!worldIn.isRemote) {
            if (compound != null) {
                if (isSelected) {
                    if (compound.getBoolean(KEY_INFUSE_NOTIFY)) {
                        entityIn.addChatMessage(new ChatComponentTranslation("chat.elementalspirits.infuse_start.msg"));
                        compound.setBoolean(KEY_INFUSE_NOTIFY, false);
                        stack.setTagCompound(compound);
                    }
                }
            }
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound != null) {
            if (compound.getBoolean(KEY_INFUSED))
                return EnumAction.BLOCK;
        }
        return EnumAction.NONE;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            NBTTagCompound compound = stack.getTagCompound();
            if (compound != null) {
                if (compound.getBoolean(KEY_INFUSED)) {
                    if (player.ridingEntity != null && player.ridingEntity instanceof EntityElementalEarth) {
                        EntityElementalEarth elemental = (EntityElementalEarth) player.ridingEntity;
                        elemental.aiRiddenByPlayer.boost();
                    }
                }
            }
        }
        return super.onItemRightClick(stack, world, player);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound != null) {
            if (compound.getBoolean(KEY_PERFORM_INFUSE)) {
                boolean anyExecuted = false;
                for (InfusionRitual ritual : InfusionRitual.registeredRituals) {
                    if (ritual.canExecuteRitual(player, stack, world, pos)) {
                        ritual.executeRitual(player, stack, world, pos);
                        anyExecuted = true;
                        break;
                    }
                }

                if (!anyExecuted) {
                    if (!world.isRemote)
                        player.addChatMessage(new ChatComponentTranslation("chat." + Util.MOD_ID_LOWER + ".infuse_failed.msg"));
                }

                compound.setBoolean(KEY_PERFORM_INFUSE, false);
                stack.setTagCompound(compound);
            }
        }
        return false;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound != null) {
            if (compound.getBoolean(KEY_INFUSED))
                return true;
        }
        return false;
    }

    public static boolean isRodInfused(ItemStack stack) {
        if (stack.getItem() == ElementalSpirits.instance.itemEarthRod) {
            NBTTagCompound compound = stack.getTagCompound();
            if (compound != null) {
                return compound.getBoolean(KEY_INFUSED);
            }
        }
        return false;
    }

}
