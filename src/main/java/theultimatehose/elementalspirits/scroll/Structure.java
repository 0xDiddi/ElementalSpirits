package theultimatehose.elementalspirits.scroll;

import net.minecraft.util.ResourceLocation;
import theultimatehose.elementalspirits.util.Util;

import java.util.ArrayList;

public class Structure {

    public static class Book {

        public static ArrayList<Chapter> chapters;

        public static void init() {
            chapters = new ArrayList<Chapter>();

            Chapter elementals = new Chapter("elemental");

            Entry elementals_earth = new Entry("earth_elementals");
            elementals_earth.addPage(new PageTextOnly(1));
            elementals_earth.addPage(new PageImageAndText(2, "elemental_earth"));
            elementals.addEntry(elementals_earth);

            Chapter getting_started = new Chapter("gettingStarted");

            Entry introduction = new Entry("introduction");
            introduction.addPage(new PageTextOnly(1));
            getting_started.addEntry(introduction);

            Entry the_eiw = new Entry("the_eiw");
            the_eiw.addPage(new PageTextOnly(1));
            getting_started.addEntry(the_eiw);

            chapters.add(elementals);
            chapters.add(getting_started);
        }

    }

    public static class Chapter {
        public final String identifier;
        public ArrayList<Entry> entries;

        public Chapter(String identifier) {
            this.identifier = identifier;
            this.entries = new ArrayList<Entry>();
        }

        public void addEntry(Entry entry) {
            this.entries.add(entry);
        }
    }

    public static class Entry {
        public final String subIdentifier;
        public ArrayList<Page> pages;

        Entry(String subIdentifier) {
            this.subIdentifier = subIdentifier;
            this.pages = new ArrayList<Page>();
        }

        public void addPage(Page page) {
            this.pages.add(page);
        }
    }

    public static class Page {
        public final int number;

        public Page(int number) {
            this.number = number;
        }
    }

    public static class PageTextOnly extends Page {
        public PageTextOnly(int number) {
            super(number);
        }
    }

    public static class PageImageAndText extends Page {

        public final ResourceLocation resLoc;

        public PageImageAndText(int number, String image) {
            super(number);
            this.resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/scroll_images/" + image + ".png");
        }
    }

}
