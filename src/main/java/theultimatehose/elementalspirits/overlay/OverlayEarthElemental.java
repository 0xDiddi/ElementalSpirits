package theultimatehose.elementalspirits.overlay;

import net.minecraft.util.StatCollector;
import theultimatehose.elementalspirits.entity.elemental_earth.EntityElementalEarth;

public class OverlayEarthElemental extends Overlay {

    @Override
    public void render(float overlayAlpha) {
        super.render(overlayAlpha);

        if (!(elemental instanceof EntityElementalEarth)) {
            return;
        }

        if (this.fontRenderer != null && overlayAlpha == 1) {
            for (Position pos : Position.values()) {
                if (this.wheelActions.get(pos) != null) {
                    String localized = StatCollector.translateToLocal(this.wheelActions.get(pos).unlocalizedName);
                    int stringWidth = this.fontRenderer.getStringWidth(localized);
                    this.fontRenderer.drawString(localized, this.startLeft + ((pos.x - stringWidth) / 2), this.startTop + pos.y, 0xFFFFFF);
                }
            }
        }

    }
}
