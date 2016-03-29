package theultimatehose.elementalspirits.entity.elemental_earth.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.entity.elemental_earth.EntityElementalEarth;
import theultimatehose.elementalspirits.items.ItemEarthRod;
import theultimatehose.elementalspirits.overlay.WheelInteraction;

public class ActionInfuse implements WheelInteraction.Action {

    public EntityElementalEarth elemental;
    public EntityPlayer player;

    public ActionInfuse(EntityElementalEarth elemental, EntityPlayer player) {
        this.elemental = elemental;
        this.player = player;
    }

    @Override
    public void perform() {
        ItemStack stack = player.getCurrentEquippedItem();
        if (stack.getItem() == ElementalSpirits.instance.itemEarthRod) {
            NBTTagCompound compound = stack.getTagCompound();
            if (compound == null)
                compound = new NBTTagCompound();

            if (!compound.getBoolean(ItemEarthRod.KEY_PERFORM_INFUSE)) {
                compound.setBoolean(ItemEarthRod.KEY_PERFORM_INFUSE, true);
                compound.setBoolean(ItemEarthRod.KEY_INFUSE_NOTIFY, true);
                stack.setTagCompound(compound);
            }
        }
    }

}
