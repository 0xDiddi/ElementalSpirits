package theultimatehose.elementalspirits.scroll;

public class Structure {

    public enum ChapterList {
        introduction(0, "introduction"),
        misc(1, "misc");

        final int ID;
        final String identifier;

        ChapterList(int id, String identifier) {
            this.ID = id;
            this.identifier = identifier;
        }
    }

    public enum EntryList {
        introduction_1(0, "");

        EntryList(int id, String subIdentifier) {

        }
    }

}
