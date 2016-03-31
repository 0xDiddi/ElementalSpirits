package theultimatehose.elementalspirits.scroll.structure.pages;

import theultimatehose.elementalspirits.scroll.GuiScroll;
import theultimatehose.elementalspirits.scroll.structure.Page;

public class PageTextOnly extends Page {
    public PageTextOnly(int number) {
        super(number);
    }

    @Override
    public void render(GuiScroll gui, int x, int y) {
        gui.getFontRendererObj().drawSplitString(gui.parseIdentifier(gui.currentChapter.identifier + "." + gui.currentEntry.subIdentifier + "." + gui.currentPage.number), x, y, GuiScroll.TEXT_WRAP_WIDTH, 0);
    }
}
