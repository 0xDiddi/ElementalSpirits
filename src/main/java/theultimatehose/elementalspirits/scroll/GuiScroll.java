package theultimatehose.elementalspirits.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import theultimatehose.elementalspirits.scroll.buttons.BookMarkButton;
import theultimatehose.elementalspirits.scroll.buttons.PageButton;
import theultimatehose.elementalspirits.scroll.buttons.TextButton;
import theultimatehose.elementalspirits.scroll.buttons.UtilButtons;
import theultimatehose.elementalspirits.scroll.structure.*;
import theultimatehose.elementalspirits.util.Util;

import java.io.IOException;
import java.util.ArrayList;

public class GuiScroll extends GuiScreen {

    public static final int TEXT_WRAP_WIDTH = 130;
    private static final String NBT_KEY_CHAPTER = "opened_chapter";
    private static final String NBT_KEY_ENTRY = "opened_entry";
    private static final String NBT_KEY_PAGE = "opened_page";

    public Chapter currentChapter;
    public Entry currentEntry;
    public Page currentPage;
    public EntityPlayer player;

    private ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/GuiAncientScroll.png");
    public ResourceLocation resLocCraftingOverlay = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/GuiAncientScrollCraftingOverlay.png");

    public int guiTop, guiLeft, guiWidth = 160, guiHeight = 205;

    public ArrayList<GuiButton> buttons;
    private ArrayList<BookMarkButton> bookMarkButtons;

    public boolean isPlayerShifting;

    public GuiScroll(EntityPlayer player) {
        super();
        this.player = player;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiTop = (this.height-this.guiHeight)/2;
        this.guiLeft = (this.width-this.guiWidth)/2;

        UtilButtons.init(this.guiLeft, this.guiTop);

        BookMark[] marks = new BookMark[7];

        if (this.player != null) {
            ItemStack stack = player.getCurrentEquippedItem();
            if (stack != null) {
                NBTTagCompound compound = stack.getTagCompound();
                if (compound != null) {
                    this.currentChapter = Book.getChapterByIdentifier(compound.getString(NBT_KEY_CHAPTER));
                    if (this.currentChapter != null) {
                        this.currentEntry = this.currentChapter.getEntryByIdentifier(compound.getString(NBT_KEY_ENTRY));
                        if (this.currentEntry != null)
                            this.currentPage = this.currentEntry.getPageByNumber(compound.getInteger(NBT_KEY_PAGE));
                    }

                    for (int i = 0; i < 7; i++) {
                        BookMark mark = new BookMark(i);
                        mark.readFromNBT(compound);
                        marks[i] = mark;
                    }
                } else {
                    currentChapter = Book.getChapterByIdentifier("gettingStarted");
                    if (currentChapter != null) {
                        currentEntry = currentChapter.getEntryByIdentifier("scroll");
                        currentPage = currentEntry.getPageByNumber(1);
                    }
                }
            }
        }

        this.bookMarkButtons = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            BookMarkButton btn = new BookMarkButton(i, this.guiLeft + 145, this.guiTop + (i * 27) + 7, this, (marks[i] != null ? (marks[i].targetChapter != null ? marks[i] : null) : null), i);
            this.bookMarkButtons.add(btn);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        //Draw bookmark buttons first as they are behind everything else
        for (BookMarkButton btn : this.bookMarkButtons) {
            btn.drawButtonForegroundLayer(mouseX, mouseY);
        }

        this.mc.getTextureManager().bindTexture(resLoc);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, guiWidth, guiHeight);
        super.drawScreen(mouseX, mouseY, partialTicks);
        boolean unicode = this.fontRendererObj.getUnicodeFlag();
        this.fontRendererObj.setUnicodeFlag(true);

        int y = this.guiTop + 24;
        int x = this.guiLeft + 15;
        this.buttons = null;
        PageButton btnBack;
        this.buttons = new ArrayList<>();

        //If chapter is null, draw the chapter overview
        if (this.currentChapter == null) {

            this.fontRendererObj.drawSplitString(parseIdentifier("contents.name"), x, guiTop + 12, TEXT_WRAP_WIDTH, 0);
            for (Chapter chap : Book.chapters) {
                TextButton btn = new TextButton(y, x, y, parseIdentifier(chap.identifier + ".name"), this, new BookMark(chap));
                this.buttons.add(btn);
                y += 10;
            }

        //If entry is null, draw the entry overview
        } else if (this.currentEntry == null) {

            this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + ".title"), x, guiTop + 12, TEXT_WRAP_WIDTH, 0);
            this.buttons = new ArrayList<>();
            for (Entry entry : currentChapter.entries) {
                TextButton btn = new TextButton(y, x, y, parseIdentifier(currentChapter.identifier + "." + entry.subIdentifier + ".name"), this, new BookMark(currentChapter, entry, entry.pages.get(0)));
                this.buttons.add(btn);
                y += 10;
            }

            btnBack = new PageButton(guiLeft + guiWidth / 2 - 10, guiTop + guiHeight - 20, PageButton.Direction.back, this, new BookMark());
            this.buttons.add(btnBack);

        //Else, draw the current page
        } else {

            this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + "." + currentEntry.subIdentifier + ".title"), x, guiTop + 12, TEXT_WRAP_WIDTH, 0);
            this.buttons = new ArrayList<>();
            if (currentEntry.pages.size() > 1) {
                if (currentPage.number < currentEntry.pages.size()) {
                    PageButton btn = new PageButton(guiLeft + guiWidth - 25, guiTop + guiHeight - 20, PageButton.Direction.right, this, new BookMark(currentChapter, currentEntry, currentEntry.pages.get(currentPage.number)));
                    this.buttons.add(btn);
                }
                if (currentPage.number > 1) {
                    PageButton btn = new PageButton(guiLeft + 10, guiTop + guiHeight - 20, PageButton.Direction.left, this, new BookMark(currentChapter, currentEntry, currentEntry.pages.get(currentPage.number - 2)));
                    this.buttons.add(btn);
                }
            }

            btnBack = new PageButton(guiLeft + guiWidth / 2 - 10, guiTop + guiHeight - 20, PageButton.Direction.back, this, new BookMark(currentChapter));
            this.buttons.add(btnBack);

            currentPage.render(this, x, y);
        }

        for (GuiButton btn : this.buttons) {
            btn.drawButtonForegroundLayer(mouseX, mouseY);
        }

        UtilButtons.draw(mouseX, mouseY);

        this.fontRendererObj.setUnicodeFlag(unicode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.buttons != null) {
            for (GuiButton btn : this.buttons) {
                if (btn != null)
                    btn.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY);
            }
        }
        if (this.bookMarkButtons != null) {
            for (BookMarkButton btn : this.bookMarkButtons) {
                if (btn != null)
                    btn.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY);
            }
        }

        UtilButtons.checkClick(mc, mouseX, mouseY);
    }

    @Override
    public void handleKeyboardInput() throws IOException {
        super.handleKeyboardInput();
        isPlayerShifting = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onResize(Minecraft mcIn, int width, int height) {
        this.height = height;
        this.width = width;
        this.guiTop = (this.height-this.guiHeight)/2;
        this.guiLeft = (this.width-this.guiWidth)/2;

        UtilButtons.init(this.guiLeft, this.guiTop);

        int i = 0;
        for (BookMarkButton btn : this.bookMarkButtons) {
            btn.xPosition = this.guiLeft + 145;
            btn.yPosition = this.guiTop + (i * 27) + 7;
            i++;
        }
    }

    @Override
    public void onGuiClosed() {
        if (this.player != null) {
            ItemStack stack = player.getCurrentEquippedItem();
            if (stack != null) {
                NBTTagCompound compound = stack.getTagCompound();
                if (compound == null)
                    compound = new NBTTagCompound();

                if (currentChapter != null) {
                    compound.setString(NBT_KEY_CHAPTER, this.currentChapter.identifier);
                    if (currentEntry != null) {
                        compound.setString(NBT_KEY_ENTRY, this.currentEntry.subIdentifier);
                        if (currentPage != null)
                            compound.setInteger(NBT_KEY_PAGE, this.currentPage.number);
                    } else
                        compound.setString(NBT_KEY_ENTRY, "");
                } else
                    compound.setString(NBT_KEY_CHAPTER, "");

                for (BookMarkButton btn : this.bookMarkButtons) {
                    if (btn.bookMark != null)
                        btn.bookMark.writeToNBT(compound);
                    else {
                        BookMark mark = new BookMark(btn.bookMarkID);
                        mark.writeToNBT(compound);
                    }
                }

                stack.setTagCompound(compound);
            }
        }
    }

    public String parseIdentifier(String identifier) {
        identifier = "scroll." + Util.MOD_ID_LOWER + "." + identifier;
        String str = StatCollector.translateToLocal(identifier);

        str = str.replaceAll("<br>", "\n");

        str = str.replaceAll("<f>", EnumChatFormatting.BOLD+"");
        str = str.replaceAll("<i>", EnumChatFormatting.ITALIC+"");
        str = str.replaceAll("<u>", EnumChatFormatting.UNDERLINE+"");

        str = str.replaceAll("<r>", EnumChatFormatting.DARK_RED+"");
        str = str.replaceAll("<g>", EnumChatFormatting.DARK_GREEN+"");
        str = str.replaceAll("<b>", EnumChatFormatting.DARK_BLUE+"");

        str = str.replaceAll("<rs>", EnumChatFormatting.BLACK+"");

        return str + EnumChatFormatting.WHITE;
    }

    public FontRenderer getFontRendererObj() {
        return this.fontRendererObj;
    }

}
