package theultimatehose.elementalspirits.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import theultimatehose.elementalspirits.entity.EntityElementalBase;

public class OverlayHandler {

    private int pretimer;
    private float alpha;

    private Overlay currentOverlay;
    private EntityElementalBase prevElemental;

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event) {
        Entity entityHit = Minecraft.getMinecraft().objectMouseOver.entityHit;
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        int width = event.getResolution().getScaledWidth(), height = event.getResolution().getScaledHeight();

        if (entityHit instanceof EntityElementalBase) {
            EntityElementalBase elemental = (EntityElementalBase) entityHit;
            if (elemental.getMaster() != null && elemental.getMaster().equals(player.getUniqueID())) {
                if (alpha < 1 && pretimer > 150)
                    alpha += (alpha > .2 ? .01 : .001);
                else
                    pretimer++;

                if (alpha > 1)
                    alpha = 1;
                if (alpha < 0)
                    alpha = 0;

                if (elemental != this.prevElemental) {
                    this.prevElemental = elemental;
                    if (elemental instanceof IOverlayProvider) {
                        currentOverlay = ((IOverlayProvider) elemental).getOverlay();
                        if (currentOverlay != null)
                            currentOverlay.init(width, height, (width - 132) / 2, (height - 132) / 2, elemental);
                    }
                }

                if (currentOverlay != null) {
                    currentOverlay.render(alpha);
                }

            }
        } else {
            this.prevElemental = null;
            if (alpha > 0) {
                alpha -= (alpha > .2 ? .01 : .001);
                if (currentOverlay != null && alpha > 0)
                    currentOverlay.drawDefaultBackground(alpha);
            } else
                pretimer = 0;
        }
    }
}