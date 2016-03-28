package theultimatehose.elementalspirits.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.entity.elemental_earth.EntityElementalEarth;
import theultimatehose.elementalspirits.util.Util;

public class ItemEarthRod extends Item {

    public ItemEarthRod() {
        this.setMaxStackSize(1);
        ElementalSpirits.proxy.addSimpleRenderer(new ItemStack(this), new ResourceLocation(Util.MOD_ID_LOWER, Names.ITEM_EARTH_ROD));
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound != null) {
            if (compound.getBoolean("isInfused"))
                return EnumAction.BLOCK;
        }
        return EnumAction.NONE;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            NBTTagCompound compound = stack.getTagCompound();
            if (compound != null) {
                if (compound.getBoolean("isInfused")) {
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
    public boolean hasEffect(ItemStack stack) {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound != null) {
            if (compound.getBoolean("isInfused"))
                return true;
        }
        return false;
    }
}
