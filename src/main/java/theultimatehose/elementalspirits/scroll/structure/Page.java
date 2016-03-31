package theultimatehose.elementalspirits.scroll.structure;

import theultimatehose.elementalspirits.scroll.GuiScroll;

public abstract class Page {

    public final int number;

    public Page(int number) {
        this.number = number;
    }

    public abstract void render(GuiScroll gui, int x, int y);

}
