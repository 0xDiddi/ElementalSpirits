package theultimatehose.elementalspirits.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import theultimatehose.elementalspirits.multiblock.MultiBlockStructure;
import theultimatehose.elementalspirits.scroll.structure.Book;
import theultimatehose.elementalspirits.scroll.structure.Chapter;
import theultimatehose.elementalspirits.scroll.structure.Entry;
import theultimatehose.elementalspirits.scroll.structure.Page;
import theultimatehose.elementalspirits.util.Util;

import java.io.IOException;
import java.util.ArrayList;

public class GuiScroll extends GuiScreen {

    public static final int TEXT_WRAP_WIDTH = 130;
    public static final String NBT_KEY_CHAPTER = "opened_chapter";
    public static final String NBT_KEY_ENTRY = "opened_entry";
    public static final String NBT_KEY_PAGE = "opened_page";

    public Chapter currentChapter;
    public Entry currentEntry;
    public Page currentPage;
    public EntityPlayer player;

    ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/GuiAncientScroll.png");
    public ResourceLocation resLocCraftingOverlay = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/GuiAncientScrollCraftingOverlay.png");

    public int guiTop, guiLeft, guiWidth = 160, guiHeight = 205;

    public ArrayList<GuiButton> buttons;

    public GuiScroll(EntityPlayer player) {
        super();
        this.player = player;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiTop = (this.height-this.guiHeight)/2;
        this.guiLeft = (this.width-this.guiWidth)/2;

        boolean chapter_read = false, entry_read= false, page_read= false;
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
                }
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(resLoc);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, guiWidth, guiHeight);
        super.drawScreen(mouseX, mouseY, partialTicks);
        boolean unicode = this.fontRendererObj.getUnicodeFlag();
        this.fontRendererObj.setUnicodeFlag(true);

        int y = guiTop + 24;
        int x = guiLeft + 15;
        int index = 0;
        buttons = null;
        PageButton btnBack;

        //If chapter is null, draw the chapter overview
        if (this.currentChapter == null) {

            this.fontRendererObj.drawSplitString(parseIdentifier("contents.name"), x, guiTop + 12, TEXT_WRAP_WIDTH, 0);
            buttons = new ArrayList<>();
            for (Chapter chap : Book.chapters) {
                TextButton btn = new TextButton(y, x, y, parseIdentifier(chap.identifier + ".name"), this, chap, null, null);
                buttons.add(btn);
                index++;
                y += 10;
            }

        //If entry is null, draw the entry overview
        } else if (this.currentEntry == null) {

            this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + ".title"), x, guiTop + 12, TEXT_WRAP_WIDTH, 0);
            buttons = new ArrayList<>();
            for (Entry entry : currentChapter.entries) {
                TextButton btn = new TextButton(y, x, y, parseIdentifier(currentChapter.identifier + "." + entry.subIdentifier + ".name"), this, currentChapter, entry, entry.pages.get(0));
                buttons.add(btn);
                index++;
                y += 10;
            }

            btnBack = new PageButton(guiLeft + guiWidth / 2 - 10, guiTop + guiHeight - 20, PageButton.Direction.back, this, null, null, null);
            buttons.add(btnBack);

        //Else, draw the current page
        } else {

            this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + "." + currentEntry.subIdentifier + ".title"), x, guiTop + 12, TEXT_WRAP_WIDTH, 0);
            buttons = new ArrayList<>();
            if (currentEntry.pages.size() > 1) {
                if (currentPage.number < currentEntry.pages.size()) {
                    PageButton btn = new PageButton(guiLeft + guiWidth - 25, guiTop + guiHeight - 20, PageButton.Direction.right, this, currentChapter, currentEntry, currentEntry.pages.get(currentPage.number));
                    buttons.add(btn);
                }
                if (currentPage.number > 1) {
                    PageButton btn = new PageButton(guiLeft + 10, guiTop + guiHeight - 20, PageButton.Direction.left, this, currentChapter, currentEntry, currentEntry.pages.get(currentPage.number - 2));
                    buttons.add(btn);
                }
            }

            btnBack = new PageButton(guiLeft + guiWidth / 2 - 10, guiTop + guiHeight - 20, PageButton.Direction.back, this, currentChapter, null, null);
            buttons.add(btnBack);

            currentPage.render(this, x, y);
        }

        for (GuiButton btn : this.buttons) {
            btn.drawButtonForegroundLayer(mouseX, mouseY);
        }

        this.fontRendererObj.setUnicodeFlag(unicode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (buttons != null) {
            for (GuiButton btn : buttons) {
                if (btn != null)
                    btn.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY);
            }
        }
    }

    @Override
    public void onResize(Minecraft mcIn, int width, int height) {
        this.height = height;
        this.width = width;
        this.guiTop = (this.height-this.guiHeight)/2;
        this.guiLeft = (this.width-this.guiWidth)/2;
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
