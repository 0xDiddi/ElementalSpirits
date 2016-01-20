package theultimatehose.elementalspirits.scroll;

public class Structure {

    public enum ChapterList {
        elementals(0, "elemental"),
        misc(1, "misc");

        final int ID;
        final String identifier;

        ChapterList(int id, String identifier) {
            this.ID = id;
            this.identifier = identifier;
        }
    }

    public enum EntryList {
        introduction_1(0, "introduction", ChapterList.misc, 1);

        final int id;
        final String subIdentifier;
        final ChapterList parent;
        final int pages;

        EntryList(int id, String subIdentifier, ChapterList parent, int pages) {
            this.id = id;
            this.subIdentifier = subIdentifier;
            this.parent = parent;
            this.pages = pages;
        }
    }

}
