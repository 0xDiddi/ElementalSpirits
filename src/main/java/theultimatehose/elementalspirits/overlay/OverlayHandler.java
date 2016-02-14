package theultimatehose.elementalspirits.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.entity.EntityElementalEarth;
import theultimatehose.elementalspirits.util.Util;

public class OverlayHandler {

    int pretimer;
    float alpha;
    boolean lastPressed;

    KeyBinding bind1 = new KeyBinding("test", Keyboard.KEY_NUMPAD8, Util.MOD_ID_LOWER + "." + Names.KEYBIND_UI);

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        lastPressed = bind1.isPressed();
    }

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event) {
        Entity entityHit = Minecraft.getMinecraft().objectMouseOver.entityHit;
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        int width = event.resolution.getScaledWidth(), height = event.resolution.getScaledHeight();

        if (entityHit instanceof EntityElementalEarth) {
            EntityElementalEarth elemental = (EntityElementalEarth) entityHit;
            if (elemental.getMaster().equals(player.getGameProfile().getId().toString())) {
                if (alpha < 1 && pretimer > 150)
                    alpha += (alpha > .2 ? .01 : .001);
                else
                    pretimer++;

                if (alpha > 1)
                    alpha = 1;
                if (alpha < 0)
                    alpha = 0;

                FontRenderer fr = Minecraft.getMinecraft().getRenderManager().getFontRenderer();

                if (fr != null && alpha == 1) {
                    fr.drawString("Press Num8 to mount.", width / 2, height / 2, 0xFFFFFF);
                }

                if (lastPressed) {
                    if (!player.isRiding()) {
                        elemental.updateRider(player);
                    }
                    lastPressed = false;
                }

            }
        } else {
            if (alpha > 0)
                alpha -= (alpha > .2 ? .01 : .001);
            else
                pretimer = 0;
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();

        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/OverlayElemental.png"));

        drawTexturedModalRectAlpha((width - 132) / 2, (height - 132) / 2, 0, 0, 0, 132, 132, alpha);

        GlStateManager.popMatrix();

    }

    public void drawTexturedModalRectAlpha(int x, int y, double z, int textureX, int textureY, int width, int height, float alpha) {
        float f = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer wr = tessellator.getWorldRenderer();
        wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        wr.pos((double) (x), (double) (y + height), z).tex((double) ((float) (textureX) * f), (double) ((float) (textureY + height) * f)).color(1, 1, 1, alpha).endVertex();
        wr.pos((double) (x + width), (double) (y + height), z).tex((double) ((float) (textureX + width) * f), (double) ((float) (textureY + height) * f)).color(1, 1, 1, alpha).endVertex();
        wr.pos((double) (x + width), (double) (y), z).tex((double) ((float) (textureX + width) * f), (double) ((float) (textureY) * f)).color(1, 1, 1, alpha).endVertex();
        wr.pos((double) (x), (double) (y), z).tex((double) ((float) (textureX) * f), (double) ((float) (textureY) * f)).color(1, 1, 1, alpha).endVertex();
        tessellator.draw();
    }

}
