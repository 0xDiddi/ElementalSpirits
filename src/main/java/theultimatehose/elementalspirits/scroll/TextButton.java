package theultimatehose.elementalspirits.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class TextButton extends GuiButton {

    int xPos, yPos;
    String text;

    public TextButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
        xPos = x;
        yPos = y;
        text = buttonText;
        this.height = 9;
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
}
