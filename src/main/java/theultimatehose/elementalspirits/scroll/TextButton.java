package theultimatehose.elementalspirits.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import theultimatehose.elementalspirits.scroll.structure.BookMark;
import theultimatehose.elementalspirits.scroll.structure.Chapter;
import theultimatehose.elementalspirits.scroll.structure.Entry;
import theultimatehose.elementalspirits.scroll.structure.Page;

public class TextButton extends GuiButton {

    int xPos, yPos;
    String text;
    GuiScroll parent;
    BookMark bookMark;

    public TextButton(int buttonId, int x, int y, String buttonText, GuiScroll parent, BookMark bookMark) {
        super(buttonId, x, y, buttonText);
        this.xPos = x;
        this.yPos = y;
        this.text = buttonText;
        this.height = 9;
        this.parent = parent;
        this.bookMark = bookMark;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        fontRenderer.drawString(this.text, this.xPos, this.yPos, 0);
        if (mouseX > this.xPos && mouseY > this.yPos && mouseX < this.xPos + getButtonWidth() && mouseY < this.yPos + this.height) {
            drawGradientRect(this.xPos, yPos + 8, this.xPos + this.getButtonWidth(), this.yPos + 9, 0xFFCC0000, 0xFF000000);
        }
    }

    @Override
    public int getButtonWidth() {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.text);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (mouseX > this.xPos && mouseY > this.yPos && mouseX < this.xPos + getButtonWidth() && mouseY < this.yPos + this.height) {
            this.bookMark.jumpTo(this.parent);
            return true;
        } else {
            return false;
        }
    }
}
