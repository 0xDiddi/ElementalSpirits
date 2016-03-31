package theultimatehose.elementalspirits.scroll.structure;

import net.minecraft.nbt.NBTTagCompound;
import theultimatehose.elementalspirits.scroll.GuiScroll;

public class BookMark {

    public static final String NBT_KEY_CHAPTER = "chapter";
    public static final String NBT_KEY_ENTRY = "entry";
    public static final String NBT_KEY_PAGE = "page";
    public static final String NBT_NAME_PREFIX = "bookmark_";

    public Chapter targetChapter;
    public Entry targetEntry;
    public Page targetPage;
    /** Only used when saving/reading from/to NBT */
    public int id;

    public BookMark(int id, Chapter targetChapter, Entry targetEntry, Page targetPage) {
        this.id = id;
        this.targetChapter = targetChapter;
        this.targetEntry = targetEntry;
        this.targetPage = targetPage;
    }

    public BookMark(int id) {
        this(id, null, null, null);
    }

    public BookMark(Chapter targetChapter, Entry targetEntry, Page targetPage) {
        this(-1, targetChapter, targetEntry, targetPage);
    }

    public BookMark(Chapter targetChapter) {
        this(targetChapter, null, null);
    }

    public BookMark() {
        this(null, null, null);
    }

    public void jumpTo(GuiScroll gui) {
        gui.currentChapter = this.targetChapter;
        gui.currentEntry = this.targetEntry;
        gui.currentPage = this.targetPage;
    }

    public void writeToNBT(NBTTagCompound compound) {
        if (compound == null)
            return;

        NBTTagCompound subCompound = new NBTTagCompound();
        if (this.targetChapter != null) {
            subCompound.setString(NBT_KEY_CHAPTER, this.targetChapter.identifier);
            if (this.targetEntry != null) {
                subCompound.setString(NBT_KEY_ENTRY, this.targetEntry.subIdentifier);
                if (this.targetPage != null)
                    subCompound.setInteger(NBT_KEY_PAGE, this.targetPage.number);
            }
        }

        compound.setTag(NBT_NAME_PREFIX + this.id, subCompound);
    }

    public void readFromNBT(NBTTagCompound compound) {
        if (compound == null)
            return;

        NBTTagCompound subCompound = compound.getCompoundTag(NBT_NAME_PREFIX + this.id);

        if (subCompound == null)
            return;

        this.targetChapter = Book.getChapterByIdentifier(subCompound.getString(NBT_KEY_CHAPTER));
        if (this.targetChapter != null) {
            this.targetEntry = this.targetChapter.getEntryByIdentifier(subCompound.getString(NBT_KEY_ENTRY));
            if (this.targetEntry != null)
                this.targetPage = this.targetEntry.getPageByNumber(subCompound.getInteger(NBT_KEY_PAGE));
        }
    }

}
