package theultimatehose.elementalspirits.entity.elemental_earth.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import theultimatehose.elementalspirits.entity.elemental_earth.EntityElementalEarth;
import theultimatehose.elementalspirits.item.ItemManager;
import theultimatehose.elementalspirits.item.ItemEarthRod;
import theultimatehose.elementalspirits.overlay.WheelInteraction;

public class ActionInfuse extends WheelInteraction.Action {

    public EntityElementalEarth elemental;
    public EntityPlayer player;

    public ActionInfuse(EntityElementalEarth elemental, EntityPlayer player) {
        super("infuse");
        this.elemental = elemental;
        this.player = player;
    }

    @Override
    public void perform() {
        ItemStack stack = player.inventory.getCurrentItem();
        if (stack.getItem() == ItemManager.itemEarthRod) {
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
