package theultimatehose.elementalspirits.scroll.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import theultimatehose.elementalspirits.scroll.GuiScroll;
import theultimatehose.elementalspirits.scroll.structure.BookMark;
import theultimatehose.elementalspirits.scroll.structure.Chapter;
import theultimatehose.elementalspirits.scroll.structure.Entry;
import theultimatehose.elementalspirits.scroll.structure.Page;

public class TextButton extends GuiButton {

    String text;
    GuiScroll parent;
    BookMark bookMark;

    public TextButton(int buttonId, int x, int y, String buttonText, GuiScroll parent, BookMark bookMark) {
        super(buttonId, x, y, buttonText);
        this.xPosition = x;
        this.yPosition = y;
        this.text = buttonText;
        this.height = 9;
        this.parent = parent;
        this.bookMark = bookMark;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        fontRenderer.drawString(this.text, this.xPosition, this.yPosition, 0);
        if (mouseX > this.xPosition && mouseY > this.yPosition && mouseX < this.xPosition + this.getButtonWidth() && mouseY < this.yPosition + this.height) {
            this.drawGradientRect(this.xPosition, this.yPosition + 8, this.xPosition + this.getButtonWidth(), this.yPosition + 9, 0xFFCC0000, 0xFF000000);
        }
    }

    @Override
    public int getButtonWidth() {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.text);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (mouseX > this.xPosition && mouseY > this.yPosition && mouseX < this.xPosition + getButtonWidth() && mouseY < this.yPosition + this.height) {
            this.bookMark.jumpTo(this.parent);
            return true;
        } else {
            return false;
        }
    }
}
