package theultimatehose.elementalspirits.scroll.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import theultimatehose.elementalspirits.scroll.structure.pages.PageMultiBlockAndText;
import theultimatehose.elementalspirits.util.ModUtil;

public class LayerChangeButon extends GuiButton {

    public ResourceLocation resLoc = new ResourceLocation(ModUtil.MOD_ID_LOWER, "textures/gui/GuiAncientScroll.png");

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
        this.xPosition = x;
        this.yPosition = y;
        this.width = 11;
        this.height = 16;
        this.direction = direction;
        this.targetPage = targetPage;
    }

    @Override
    public void drawButtonForegroundLayer(int mouseX, int mouseY) {
        int textureX = 40, textureY = 0;

        if (this.direction == Direction.up)
            textureY = 205;
        else if (this.direction == Direction.down)
            textureY = 221;

        if (mouseX > this.xPosition && mouseY > this.yPosition && mouseX < this.xPosition + getButtonWidth() && mouseY < this.yPosition + this.height) {
            textureX = 51;
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(this.resLoc);
        drawTexturedModalRect(this.xPosition, this.yPosition, textureX, textureY, this.width, this.height);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (mouseX > this.xPosition && mouseY > this.yPosition && mouseX < this.xPosition + getButtonWidth() && mouseY < this.yPosition + this.height) {
            this.targetPage.currentLayer += this.direction.value;
            return true;
        } else {
            return false;
        }
    }

}
