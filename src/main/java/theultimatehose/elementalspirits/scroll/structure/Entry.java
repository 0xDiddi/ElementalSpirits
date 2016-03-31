package theultimatehose.elementalspirits.scroll.structure;

import java.util.ArrayList;

public class Entry {

    public final String subIdentifier;
    public ArrayList<Page> pages;

    public Entry(String subIdentifier) {
        this.subIdentifier = subIdentifier;
        this.pages = new ArrayList<Page>();
    }

    public void addPage(Page page) {
        this.pages.add(page);
    }

    public Page getPageByNumber(int num) {
        if (num <= pages.size() && num > 0)
            return pages.get(num - 1);
        return null;
    }
}
