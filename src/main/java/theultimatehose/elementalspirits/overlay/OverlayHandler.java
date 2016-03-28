package theultimatehose.elementalspirits.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.entity.EntityElementalBase;
import theultimatehose.elementalspirits.entity.EntityElementalEarth;
import theultimatehose.elementalspirits.input.KeyBindManager;
import theultimatehose.elementalspirits.util.Util;

public class OverlayHandler {

    int pretimer;
    float alpha;

    public Overlay currentOverlay;

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event) {
        Entity entityHit = Minecraft.getMinecraft().objectMouseOver.entityHit;
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        int width = event.resolution.getScaledWidth(), height = event.resolution.getScaledHeight();

        if (entityHit instanceof EntityElementalBase) {
            EntityElementalBase elemental = (EntityElementalBase) entityHit;
            if (elemental.getMaster().equals(player.getGameProfile().getId().toString())) {
                if (alpha < 1 && pretimer > 150)
                    alpha += (alpha > .2 ? .01 : .001);
                else
                    pretimer++;

                if (alpha > 1)
                    alpha = 1;
                if (alpha < 0)
                    alpha = 0;

                if (elemental instanceof EntityElementalEarth)
                    currentOverlay = new OverlayEarthElemental();

                if (currentOverlay != null) {
                    currentOverlay.init(width, height);
                    currentOverlay.render(alpha, elemental);
                }

            }
        } else {
            if (alpha > 0) {
                alpha -= (alpha > .2 ? .01 : .001);
                if (currentOverlay != null && alpha > 0)
                    currentOverlay.drawDefaultBackground(alpha);
            } else
                pretimer = 0;
        }
    }
}
