package theultimatehose.elementalspirits.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import theultimatehose.elementalspirits.util.Util;

public class GuiScroll extends GuiScreen {

    public enum Types {
        overview, chapter, entry;
    }

    Types currentType;
    Structure.ChapterList currentEntry;

    ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/ancient_scroll_gui.png");

    public int guiTop, guiLeft, guiWidth = 160, guiHeight = 205;

    TextButton btn;

    public GuiScroll() {
        super();
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiTop = (this.height-this.guiHeight)/2;
        this.guiLeft = (this.width-this.guiWidth)/2;

        btn = new TextButton(0, guiLeft + 10, guiTop + 30, "Click me");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(resLoc);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, guiWidth, guiHeight);
        super.drawScreen(mouseX, mouseY, partialTicks);

        this.fontRendererObj.drawSplitString(EnumChatFormatting.BOLD + StatCollector.translateToLocal("scroll." + Util.MOD_ID_LOWER + ".contents.name"), guiLeft + 12, guiTop + 12, 150, 0);

        btn.drawButtonForegroundLayer(mouseX, mouseY);
    }

    @Override
    public void onResize(Minecraft mcIn, int par1, int par2) {
        super.onResize(mcIn, par1, par2);
        this.guiTop = (this.height-this.guiHeight)/2;
        this.guiLeft = (this.width-this.guiWidth)/2;
    }
}
