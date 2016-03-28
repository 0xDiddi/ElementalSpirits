package theultimatehose.elementalspirits.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import theultimatehose.elementalspirits.entity.EntityElementalBase;
import theultimatehose.elementalspirits.util.Util;

public abstract class Overlay {

    public FontRenderer fontRenderer = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
    public ResourceLocation defaultBackground = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/OverlayElemental.png");
    public EntityPlayer player = Minecraft.getMinecraft().thePlayer;

    public int width, height;

    public void init(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void render(float overlayAlpha, EntityElementalBase elemental) {
        this.drawDefaultBackground(overlayAlpha);
    }

    public void drawDefaultBackground(float overlayAlpha) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();

        Minecraft.getMinecraft().getTextureManager().bindTexture(defaultBackground);

        drawTexturedModalRectAlpha((width - 132) / 2, (height - 132) / 2, 0, 0, 0, 132, 132, overlayAlpha);

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
