package theultimatehose.elementalspirits.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import theultimatehose.elementalspirits.scroll.structure.pages.PageMultiBlockAndText;
import theultimatehose.elementalspirits.util.Util;

import java.security.DigestException;

public class LayerChangeButon extends GuiButton {

    public ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/GuiAncientScroll.png");

    int xPos, yPos;
    Direction direction;
    PageMultiBlockAndText targetPage;

    public enum Direction {
        up(1), down(-1);

        public final int value;

        Direction(int val) {
            this.value = val;
        }

    }

    public LayerChangeButon(int buttonId, int x, int y, Direction direction, PageMultiBlockAndText targetPage) {
        super(buttonId, x, y, null);
        this.xPos = x;
        this.yPos = y;
        this.width = 11;
        this.height = 16;
        this.direction = direction;
        this.targetPage = targetPage;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        super.drawButtonForegroundLayer(mouseX, mouseY);
        int textureX = 40, textureY = 0;

        if (direction == Direction.up)
            textureY = 205;
        else if (direction == Direction.down)
            textureY = 221;

        if (mouseX > xPos && mouseY > yPos && mouseX < xPos + getButtonWidth() && mouseY < yPos + this.height) {
            textureX = 51;
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(resLoc);
        drawTexturedModalRect(xPos, yPos, textureX, textureY, width, height);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (mouseX > xPos && mouseY > yPos && mouseX < xPos + getButtonWidth() && mouseY < yPos + this.height) {
            this.targetPage.currentLayer += this.direction.value;
            return true;
        } else {
            return false;
        }
    }

}
