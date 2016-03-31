package theultimatehose.elementalspirits.scroll.structure;

import java.util.ArrayList;

public class Chapter {
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
