package theultimatehose.elementalspirits.scroll;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.entity.ai.ElementalAIFollowMaster;
import theultimatehose.elementalspirits.multiblock.MultiBlockStructure;
import theultimatehose.elementalspirits.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Structure {

    public static class Book {

        public static ArrayList<Chapter> chapters;

        public static void init() {
            chapters = new ArrayList<>();

            Chapter elementals_earth = new Chapter("earth_elementals");

            Entry earth_general = new Entry("general");
            earth_general.addPage(new PageTextOnly(1));
            earth_general.addPage(new PageImageAndText(2, "elemental_earth"));
            elementals_earth.addEntry(earth_general);

            Entry earth_rod = new Entry("earth_rod");
            earth_rod.addPage(new PageTextOnly(1));
            earth_rod.addPage(new PageRecipeAndText(2, ElementalSpirits.instance.itemEarthRodRecipe));
            elementals_earth.addEntry(earth_rod);

            Entry earth_actions = new Entry("actions");
            earth_actions.addPage(new PageTextOnly(1));
            earth_actions.addPage(new PageTextOnly(2));
            elementals_earth.addEntry(earth_actions);

            Chapter getting_started = new Chapter("gettingStarted");

            Entry introduction = new Entry("introduction");
            introduction.addPage(new PageTextOnly(1));
            getting_started.addEntry(introduction);

            Entry the_eiw = new Entry("the_eiw");
            the_eiw.addPage(new PageTextOnly(1));
            getting_started.addEntry(the_eiw);

            chapters.add(elementals_earth);
            chapters.add(getting_started);
        }

        public Chapter getChapterByIdentifier(String identifier) {
            for (Chapter c : chapters) {
                if (c.identifier.equals(identifier))
                    return c;
            }
            return null;
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

        public Entry getEntryByIdentifier(String id) {
            for (Entry e : entries) {
                if (e.subIdentifier.equals(id))
                    return e;
            }
            return null;
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

        public Page getPageByNumber(int num) {
            if (num < pages.size() && num > 0)
                return pages.get(num - 1);
            return null;
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

    public static class PageRecipeAndText extends Page {

        public final ItemStack[] input;
        public final ItemStack result;

        public PageRecipeAndText(int number, ShapedOreRecipe recipe) {
            super(number);
            this.input = new ItemStack[9];

            for (int i = 0; i < recipe.getInput().length; i++) {
                Object o = recipe.getInput()[i];

                if (o instanceof ItemStack)
                    this.input[i] = (ItemStack) o;
                else if (o instanceof Collection) {
                    //noinspection unchecked
                    this.input[i] = (ItemStack) ((Collection<ItemStack>)o).toArray()[0];
                }
            }

            this.result = recipe.getRecipeOutput();
        }
    }

    public static class PageMultiBlockAndText extends Page {

        public final MultiBlockStructure structure;
        public int currentLayer = 0;

        public PageMultiBlockAndText(int number, MultiBlockStructure structure) {
            super(number);
            this.structure = structure;
        }
    }

}
