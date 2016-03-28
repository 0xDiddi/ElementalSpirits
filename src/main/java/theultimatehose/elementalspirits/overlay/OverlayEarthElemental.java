package theultimatehose.elementalspirits.overlay;

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
                String localized = pos.getLocalizedString("earth");
                int stringWidth = this.fontRenderer.getStringWidth(localized);
                this.fontRenderer.drawString(localized, this.startLeft + ((pos.x - stringWidth) / 2), this.startTop + pos.y, 0xFFFFFF);
            }
        }

    }
}
