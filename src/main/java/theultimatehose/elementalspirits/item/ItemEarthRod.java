package theultimatehose.elementalspirits.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.entity.elemental_earth.EntityElementalEarth;
import theultimatehose.elementalspirits.infusion.InfusionRitual;
import theultimatehose.elementalspirits.item.base.ItemESBase;
import theultimatehose.elementalspirits.util.Util;

public class ItemEarthRod extends ItemESBase {

    public static final String KEY_INFUSED = "isInfused";
    public static final String KEY_GREATER = "isGreater";

    public static final String KEY_PERFORM_INFUSE = "shouldInfuse";
    public static final String KEY_INFUSE_NOTIFY = "shouldNotifyPlayer";

    public ItemEarthRod() {
        super(Names.ITEM_EARTH_ROD);
        this.setMaxStackSize(1);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        NBTTagCompound compound = stack.getTagCompound();
        if (!worldIn.isRemote) {
            if (compound != null) {
                if (isSelected) {
                    if (compound.getBoolean(KEY_INFUSE_NOTIFY)) {
                        entityIn.addChatMessage(new TextComponentTranslation("chat.elementalspirits.infuse_start.msg"));
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
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            NBTTagCompound compound = stack.getTagCompound();
            if (compound != null) {
                if (compound.getBoolean(KEY_INFUSED)) {
                    if (player.getRidingEntity() != null && player.getRidingEntity() instanceof EntityElementalEarth) {
                        EntityElementalEarth elemental = (EntityElementalEarth) player.getRidingEntity();
                        elemental.aiRiddenByPlayer.boost();
                    }
                }
            }
        }
        return super.onItemRightClick(stack, world, player, hand);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
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
                        player.addChatMessage(new TextComponentTranslation("chat." + Util.MOD_ID_LOWER + ".infuse_failed.msg"));
                }

                compound.setBoolean(KEY_PERFORM_INFUSE, false);
                stack.setTagCompound(compound);
            }
        }
        return super.onItemUse(stack, player, world, pos, hand, facing, hitX, hitY, hitZ);
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
        if (stack.getItem() instanceof ItemEarthRod) {
            NBTTagCompound compound = stack.getTagCompound();
            if (compound != null) {
                return compound.getBoolean(KEY_INFUSED);
            }
        }
        return false;
    }

}
