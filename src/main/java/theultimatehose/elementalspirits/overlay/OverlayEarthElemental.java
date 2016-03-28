package theultimatehose.elementalspirits.overlay;

import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.entity.EntityElementalBase;
import theultimatehose.elementalspirits.entity.EntityElementalEarth;
import theultimatehose.elementalspirits.input.KeyBindManager;

public class OverlayEarthElemental extends Overlay {

    @Override
    public void render(float overlayAlpha, EntityElementalBase elementalBase) {
        super.render(overlayAlpha, elementalBase);

        EntityElementalEarth elemental;

        if (elementalBase instanceof EntityElementalEarth)
            elemental = (EntityElementalEarth) elementalBase;
        else
            return;

        if (this.fontRenderer != null && overlayAlpha == 1) {
            this.fontRenderer.drawString("Press Num8 to mount.", width / 2, height / 2, 0xFFFFFF);
        }

        if (KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_TC).pressed) {
            if (!player.isRiding()) {
                elemental.scheduleRiderUpdate(player);
            }
            KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_TC).reset();
        }

    }
}
