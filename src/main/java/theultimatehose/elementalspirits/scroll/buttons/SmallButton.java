package theultimatehose.elementalspirits.scroll.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import theultimatehose.elementalspirits.util.Util;

import java.awt.event.ActionListener;

public class SmallButton extends GuiButton {

    private ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/GuiAncientScroll.png");

    int textureX, textureY;
    ActionListener event;

    public SmallButton(int buttonId, int x, int y, int textureX, int textureY, ActionListener event) {
        super(buttonId, x, y, "");
        this.xPosition = x;
        this.yPosition = y;
        this.width = 16;
        this.height = 16;
        this.textureX = textureX;
        this.textureY = textureY;
        this.event = event;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(resLoc);
        drawTexturedModalRect(this.xPosition, this.yPosition, textureX, textureY, this.width, this.height);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (mouseX > xPosition && mouseY > yPosition && mouseX < xPosition + getButtonWidth() && mouseY < yPosition + this.height) {
            this.event.actionPerformed(null);
            return true;
        } else {
            return false;
        }
    }
}
