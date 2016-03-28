package theultimatehose.elementalspirits.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.entity.EntityElementalBase;
import theultimatehose.elementalspirits.input.KeyBindManager;
import theultimatehose.elementalspirits.util.Util;

import java.util.HashMap;

public abstract class Overlay {

    public FontRenderer fontRenderer = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
    public ResourceLocation defaultBackground = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/OverlayElemental.png");
    public EntityElementalBase elemental;
    public EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    public HashMap<Position, WheelInteraction.Action> wheelActions;
    public boolean hasWheelActions;

    public int width, height;
    public int startLeft, startTop;

    public void init(int width, int height, int startLeft, int startTop, EntityElementalBase elemental) {
        this.width = width;
        this.height = height;
        this.startLeft = startLeft;
        this.startTop = startTop;
        this.elemental = elemental;

        if (this.elemental instanceof WheelInteraction.IWheelInteractionProvider) {
            this.wheelActions = ((WheelInteraction.IWheelInteractionProvider)this.elemental).getActions();
            if (this.wheelActions != null)
                this.hasWheelActions = true;
        }
    }

    public void render(float overlayAlpha) {
        this.drawDefaultBackground(overlayAlpha);
        this.processWheelKeyInput();
    }

    public void drawDefaultBackground(float overlayAlpha) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();

        Minecraft.getMinecraft().getTextureManager().bindTexture(defaultBackground);

        drawTexturedModalRectAlpha(this.startLeft, this.startTop, 0, 0, 0, 132, 132, overlayAlpha);

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

    public void processWheelKeyInput() {
        if (!this.hasWheelActions)
            return;

        if (KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_TL).pressed) {
            if (wheelActions.get(Position.top_left) != null)
                wheelActions.get(Position.top_left).perform();
            KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_TL).reset();
        } else if (KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_TC).pressed) {
            if (wheelActions.get(Position.top_center) != null)
                wheelActions.get(Position.top_center).perform();
            KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_TC).reset();
        } else if (KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_TR).pressed) {
            if (wheelActions.get(Position.top_right) != null)
                wheelActions.get(Position.top_right).perform();
            KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_TR).reset();
        } else if (KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_CL).pressed) {
            if (wheelActions.get(Position.center_left) != null)
                wheelActions.get(Position.center_left).perform();
            KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_CL).reset();
        } else if (KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_CR).pressed) {
            if (wheelActions.get(Position.center_right) != null)
                wheelActions.get(Position.center_right).perform();
            KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_CR).reset();
        } else if (KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_BL).pressed) {
            if (wheelActions.get(Position.bottom_left) != null)
                wheelActions.get(Position.bottom_left).perform();
            KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_BL).reset();
        } else if (KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_BC).pressed) {
            if (wheelActions.get(Position.bottom_center) != null)
                wheelActions.get(Position.bottom_center).perform();
            KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_BC).reset();
        } else if (KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_BR).pressed) {
            if (wheelActions.get(Position.bottom_right) != null)
                wheelActions.get(Position.bottom_right).perform();
            KeyBindManager.INSTANCE.bindings.get(Names.KEYBIND_WHEEL_BR).reset();
        }
    }

    public enum Position {
        top_left("tl", 25, 30), top_center("tc", 132, -1), top_right("tr", 242, 30),
        center_left("cl", 5, 64), center_right("cr", 264, 64),
        bottom_left("bl", 31, 102), bottom_center("bc", 132, 127), bottom_right("br", 235, 102);

        final String shortName;
        final int x, y;

        Position(String shortName, int x, int y) {
            this.shortName = shortName;
            this.x = x;
            this.y = y;
        }

        String getLocalizedString(String type) {
            String unlocalized = "overlay." + Util.MOD_ID_LOWER + "." + type + "." + this.shortName + ".name";
            return StatCollector.translateToLocal(unlocalized);
        }

    }

}