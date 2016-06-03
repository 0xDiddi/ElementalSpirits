package theultimatehose.elementalspirits.scroll.structure.pages;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import theultimatehose.elementalspirits.scroll.GuiScroll;
import theultimatehose.elementalspirits.scroll.structure.Page;
import theultimatehose.elementalspirits.util.ModUtil;

public class PageImageAndText extends Page {

    public final ResourceLocation resLoc;

    public PageImageAndText(int number, String image) {
        super(number);
        this.resLoc = new ResourceLocation(ModUtil.MOD_ID_LOWER, "textures/gui/scroll_images/" + image + ".png");
    }

    @Override
    public void render(GuiScroll gui, int x, int y) {
        gui.mc.getTextureManager().bindTexture(((theultimatehose.elementalspirits.scroll.structure.pages.PageImageAndText)gui.currentPage).resLoc);
        Gui.drawScaledCustomSizeModalRect(gui.guiLeft + 24, gui.guiTop + 24, 0, 0, 280, 320, 113, 130, 280, 320);
        gui.getFontRendererObj().drawSplitString(gui.parseIdentifier(gui.currentChapter.identifier + "." + gui.currentEntry.subIdentifier + "." + gui.currentPage.number), x, gui.guiTop + gui.guiHeight - 49, GuiScroll.TEXT_WRAP_WIDTH, 0);
    }
}
