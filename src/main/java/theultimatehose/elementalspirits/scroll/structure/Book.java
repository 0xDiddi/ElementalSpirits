package theultimatehose.elementalspirits.scroll.structure;

import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.multiblock.structures.EarthRodStructure;
import theultimatehose.elementalspirits.multiblock.structures.EarthStoneInner;
import theultimatehose.elementalspirits.multiblock.structures.EarthStoneOuter;
import theultimatehose.elementalspirits.scroll.structure.pages.PageImageAndText;
import theultimatehose.elementalspirits.scroll.structure.pages.PageMultiBlockAndText;
import theultimatehose.elementalspirits.scroll.structure.pages.PageRecipeAndText;
import theultimatehose.elementalspirits.scroll.structure.pages.PageTextOnly;

import java.util.ArrayList;

public class Book {

    public static ArrayList<Chapter> chapters;

    public static void init() {
        chapters = new ArrayList<>();

        Chapter elementals_earth = new Chapter("earth");

        Entry earth_general = new Entry("general");
        earth_general.addPage(new PageTextOnly(1));
        earth_general.addPage(new PageImageAndText(2, "elemental_earth"));
        elementals_earth.addEntry(earth_general);

        Entry earth_rod = new Entry("earth_rod");
        earth_rod.addPage(new PageTextOnly(1));
        earth_rod.addPage(new PageRecipeAndText(2, ElementalSpirits.INSTANCE.itemEarthRodRecipe));
        earth_rod.addPage(new PageMultiBlockAndText(3, new EarthRodStructure()));
        elementals_earth.addEntry(earth_rod);

        Entry earth_actions = new Entry("actions");
        earth_actions.addPage(new PageTextOnly(1));
        earth_actions.addPage(new PageTextOnly(2));
        elementals_earth.addEntry(earth_actions);

        Entry earth_infused_stone = new Entry("infused_stone");
        earth_infused_stone.addPage(new PageTextOnly(1));
        earth_infused_stone.addPage(new PageMultiBlockAndText(2, new EarthStoneInner()));
        earth_infused_stone.addPage(new PageMultiBlockAndText(3, new EarthStoneOuter()));
        elementals_earth.addEntry(earth_infused_stone);

        Chapter getting_started = new Chapter("gettingStarted");

        Entry introduction = new Entry("introduction");
        introduction.addPage(new PageTextOnly(1));
        getting_started.addEntry(introduction);

        Entry scroll = new Entry("scroll");
        scroll.addPage(new PageTextOnly(1));
        scroll.addPage(new PageTextOnly(2));
        getting_started.addEntry(scroll);

        Entry the_eiw = new Entry("the_eiw");
        the_eiw.addPage(new PageTextOnly(1));
        getting_started.addEntry(the_eiw);

        chapters.add(elementals_earth);
        chapters.add(getting_started);
    }

    public static Chapter getChapterByIdentifier(String identifier) {
        for (Chapter c : chapters) {
            if (c.identifier.equals(identifier))
                return c;
        }
        return null;
    }

}
