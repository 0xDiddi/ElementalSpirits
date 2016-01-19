package theultimatehose.elementalspirits.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import theultimatehose.elementalspirits.util.Util;

public class GuiScroll extends GuiScreen {

    ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/ancient_scroll_gui.png");

    public int guiTop, guiLeft, guiWidth = 160, guiHeight = 205;

    public GuiScroll() {
        super();
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiTop = (this.height-this.guiHeight)/2;
        this.guiLeft = (this.width-this.guiWidth)/2;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(resLoc);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, guiWidth, guiHeight);
        super.drawScreen(mouseX, mouseY, partialTicks);

        this.fontRendererObj.drawSplitString(EnumChatFormatting.BOLD + StatCollector.translateToLocal("scroll." + Util.MOD_ID_LOWER + ".chapter.entry.1"), guiLeft + 12, guiTop + 12, 150, 0);
    }

    @Override
    public void onResize(Minecraft mcIn, int par1, int par2) {
        super.onResize(mcIn, par1, par2);
        this.guiTop = (this.height-this.guiHeight)/2;
        this.guiLeft = (this.width-this.guiWidth)/2;
    }
}
