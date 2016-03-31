package theultimatehose.elementalspirits.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import theultimatehose.elementalspirits.scroll.structure.Chapter;
import theultimatehose.elementalspirits.scroll.structure.Entry;
import theultimatehose.elementalspirits.scroll.structure.Page;

public class TextButton extends GuiButton {

    int xPos, yPos;
    String text;
    GuiScroll parent;
    Chapter targetChapter;
    Entry targetEntry;
    Page targetPage;

    public TextButton(int buttonId, int x, int y, String buttonText, GuiScroll parent, Chapter targetChapter, Entry targetEntry, Page targetPage) {
        super(buttonId, x, y, buttonText);
        xPos = x;
        yPos = y;
        text = buttonText;
        this.height = 9;
        this.parent = parent;
        this.targetChapter = targetChapter;
        this.targetEntry = targetEntry;
        this.targetPage = targetPage;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        super.drawButtonForegroundLayer(mouseX, mouseY);
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        fontRenderer.drawString(text, xPos, yPos, 0);
        if (mouseX > xPos && mouseY > yPos && mouseX < xPos + getButtonWidth() && mouseY < yPos + this.height) {
            drawGradientRect(xPos, yPos + 8, xPos + this.getButtonWidth(), yPos + 9, 0xFFCC0000, 0xFF000000);
        }
    }

    @Override
    public int getButtonWidth() {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (mouseX > xPos && mouseY > yPos && mouseX < xPos + getButtonWidth() && mouseY < yPos + this.height) {
            this.parent.currentChapter = targetChapter;
            this.parent.currentEntry = targetEntry;
            this.parent.currentPage = targetPage;
            return true;
        } else {
            return false;
        }
    }
}
