package theultimatehose.elementalspirits.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import theultimatehose.elementalspirits.util.Util;

import java.io.IOException;

public class GuiScroll extends GuiScreen {

    public enum Types {
        content, chapter, entry;
    }

    public Types currentType;
    public Structure.ChapterList currentChapter;
    public Structure.EntryList currentEntry;

    ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/ancient_scroll_gui.png");

    public int guiTop, guiLeft, guiWidth = 160, guiHeight = 205;

    public TextButton[] buttons;

    public GuiScroll() {
        super();
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiTop = (this.height-this.guiHeight)/2;
        this.guiLeft = (this.width-this.guiWidth)/2;
        currentType = Types.content;
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
        int index = 0;
        buttons = null;

        switch (currentType) {
            case content:
                this.fontRendererObj.drawSplitString(parseIdentifier("scroll." + Util.MOD_ID_LOWER + ".contents.name"), guiLeft + 15, guiTop + 12, 150, 0);
                buttons = new TextButton[Structure.ChapterList.values().length];
                for (Structure.ChapterList chap : Structure.ChapterList.values()) {
                    TextButton btn = new TextButton(y, guiLeft + 15, y, parseIdentifier(chap.identifier + ".name"), this, Types.chapter, chap, null);
                    btn.drawButtonForegroundLayer(mouseX, mouseY);
                    buttons[index] = btn;
                    index++;
                    y += 10;
                }
                break;
            case chapter:
                this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + ".title"), guiLeft + 15, guiTop + 12, 150, 0);
                buttons = new TextButton[100];
                for (Structure.EntryList entry : Structure.EntryList.values()) {
                    if (entry.parent == currentChapter) {
                        TextButton btn = new TextButton(y, guiLeft + 15, y, parseIdentifier(currentChapter.identifier + "." + entry.subIdentifier + ".name"), this, Types.entry, currentChapter, entry);
                        btn.drawButtonForegroundLayer(mouseX, mouseY);
                        buttons[index] = btn;
                        index++;
                        y += 10;
                    }
                }
                break;
            case entry:
                this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + "." + currentEntry.subIdentifier + ".title"), guiLeft + 15, guiTop + 12, 150, 0);

                //TODO: Do pages and stuff
                this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + "." + currentEntry.subIdentifier + ".1"), guiLeft + 15, y, 140, 0);
                break;
        }

        this.fontRendererObj.setUnicodeFlag(unicode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (buttons != null) {
            for (TextButton btn : buttons) {
                if (btn != null)
                    btn.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY);
            }
        }
    }

    @Override
    public void onResize(Minecraft mcIn, int par1, int par2) {
        super.onResize(mcIn, par1, par2);
        this.guiTop = (this.height-this.guiHeight)/2;
        this.guiLeft = (this.width-this.guiWidth)/2;
    }

    public String parseIdentifier(String identifier) {
        if (!identifier.endsWith(".contents.name"))
            identifier = "scroll." + Util.MOD_ID_LOWER + ".chapter." + identifier;
        String str = StatCollector.translateToLocal(identifier);

        str = str.replaceAll("<nl>", "\n");

        str = str.replaceAll("<f>", EnumChatFormatting.BOLD+"");
        str = str.replaceAll("<i>", EnumChatFormatting.ITALIC+"");
        str = str.replaceAll("<u>", EnumChatFormatting.UNDERLINE+"");

        str = str.replaceAll("<r>", EnumChatFormatting.DARK_RED+"");
        str = str.replaceAll("<g>", EnumChatFormatting.DARK_GREEN+"");
        str = str.replaceAll("<b>", EnumChatFormatting.DARK_BLUE+"");

        str = str.replaceAll("<rs>", EnumChatFormatting.BLACK+"");

        return str;
    }

}
