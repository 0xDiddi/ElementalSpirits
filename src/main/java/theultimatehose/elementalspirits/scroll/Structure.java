package theultimatehose.elementalspirits.scroll;

public class Structure {

    public enum ChapterList {
        elementals(0, "elemental"),
        gettingStarted(1, "gettingStarted");

        final int ID;
        final String identifier;

        ChapterList(int id, String identifier) {
            this.ID = id;
            this.identifier = identifier;
        }
    }

    public enum EntryType {
        textOnly
    }

    public enum EntryList {
        introduction(0, "introduction", ChapterList.gettingStarted, 1, EntryType.textOnly),
        earthElementals(1, "earth_elementals", ChapterList.elementals, 1, EntryType.textOnly);

        final int id;
        final String subIdentifier;
        final ChapterList parent;
        final int pages;
        final EntryType type;

        EntryList(int id, String subIdentifier, ChapterList parent, int pages, EntryType type) {
            this.id = id;
            this.subIdentifier = subIdentifier;
            this.parent = parent;
            this.pages = pages;
            this.type = type;
        }
    }

}
