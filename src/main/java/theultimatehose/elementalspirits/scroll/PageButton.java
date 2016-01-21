package theultimatehose.elementalspirits.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import theultimatehose.elementalspirits.util.Util;

public class PageButton extends GuiButton {

    public ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/ancient_scroll_gui.png");

    int xPos, yPos;
    GuiScroll parent;
    GuiScroll.Types targetType;
    Structure.ChapterList targetChapter;
    Structure.EntryList targetEntry;
    int targetPage;
    Direction direction;

    public enum Direction {
        back, left, right;
    }

    public PageButton(int x, int y, Direction direction, GuiScroll parent, GuiScroll.Types targetType, Structure.ChapterList targetChapter, Structure.EntryList targetEntry, int targetPage) {
        super(0, x, y, "");
        xPos = x;
        yPos = y;
        this.height = 10;
        this.width = 20;
        this.direction = direction;
        this.parent = parent;
        this.targetType = targetType;
        this.targetChapter = targetChapter;
        this.targetEntry = targetEntry;
        this.targetPage = targetPage;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        super.drawButtonForegroundLayer(mouseX, mouseY);
        int textureX = 0, textureY = 0;

        if (direction == Direction.right)
            textureY = 205;
        else if (direction == Direction.left)
            textureY = 215;
        else if (direction == Direction.back)
            textureY = 225;

        if (mouseX > xPos && mouseY > yPos && mouseX < xPos + getButtonWidth() && mouseY < yPos + this.height) {
            textureX = 20;
        }
        parent.mc.getTextureManager().bindTexture(resLoc);
        drawTexturedModalRect(xPos, yPos, textureX, textureY, width, height);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (mouseX > xPos && mouseY > yPos && mouseX < xPos + getButtonWidth() && mouseY < yPos + this.height) {
            this.parent.currentChapter = targetChapter;
            this.parent.currentEntry = targetEntry;
            this.parent.currentType = targetType;
            this.parent.currentPage = targetPage;
            return true;
        } else {
            return false;
        }
    }

}
