package theultimatehose.elementalspirits.entity.elemental_earth.actions;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import theultimatehose.elementalspirits.entity.elemental_earth.EntityElementalEarth;
import theultimatehose.elementalspirits.overlay.WheelInteraction;
import theultimatehose.elementalspirits.util.Util;

public class ActionMount extends WheelInteraction.Action {

    public EntityElementalEarth elemental;

    public ActionMount(EntityElementalEarth elemental) {
        super("mount");
        this.elemental = elemental;
    }

    @Override
    public void perform() {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        if (!player.isRiding()) {
            elemental.scheduleRiderUpdate(player);
        }
    }

}
