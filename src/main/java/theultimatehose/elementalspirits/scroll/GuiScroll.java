package theultimatehose.elementalspirits.scroll;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.model.pipeline.ForgeBlockModelRenderer;
import theultimatehose.elementalspirits.multiblock.MultiBlockStructure;
import theultimatehose.elementalspirits.util.Util;
import theultimatehose.elementalspirits.util.VanillaStuffUtil;

import java.io.IOException;

public class GuiScroll extends GuiScreen {

    public Structure.Chapter currentChapter;
    public Structure.Entry currentEntry;
    public Structure.Page currentPage;

    ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/GuiAncientScroll.png");
    ResourceLocation resLocCraftingOverlay = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/GuiAncientScrollCraftingOverlay.png");

    public int guiTop, guiLeft, guiWidth = 160, guiHeight = 205;

    public GuiButton[] buttons;

    public GuiScroll() {
        super();
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiTop = (this.height-this.guiHeight)/2;
        this.guiLeft = (this.width-this.guiWidth)/2;
        this.currentChapter = null;
        this.currentEntry = null;
        this.currentPage = null;
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

            this.fontRendererObj.drawSplitString(parseIdentifier("scroll." + Util.MOD_ID_LOWER + ".contents.name"), x, guiTop + 12, 130, 0);
            buttons = new TextButton[Structure.Book.chapters.size()];
            for (Structure.Chapter chap : Structure.Book.chapters) {
                TextButton btn = new TextButton(y, x, y, parseIdentifier(chap.identifier + ".name"), this, chap, null, null);
                btn.drawButtonForegroundLayer(mouseX, mouseY);
                buttons[index] = btn;
                index++;
                y += 10;
            }

        //If entry is null, draw the entry overview
        } else if (this.currentEntry == null) {

            this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + ".title"), x, guiTop + 12, 130, 0);
            buttons = new GuiButton[100];
            for (Structure.Entry entry : currentChapter.entries) {
                TextButton btn = new TextButton(y, x, y, parseIdentifier(currentChapter.identifier + "." + entry.subIdentifier + ".name"), this, currentChapter, entry, entry.pages.get(0));
                btn.drawButtonForegroundLayer(mouseX, mouseY);
                buttons[index] = btn;
                index++;
                y += 10;
            }

            btnBack = new PageButton(guiLeft + guiWidth / 2 - 10, guiTop + guiHeight - 20, PageButton.Direction.back, this, null, null, null);
            btnBack.drawButtonForegroundLayer(mouseX, mouseY);
            buttons[index] = btnBack;

        //Else, draw the current page
        } else {

            this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + "." + currentEntry.subIdentifier + ".title"), x, guiTop + 12, 130, 0);
            buttons = new PageButton[3];
            if (currentEntry.pages.size() > 1) {
                if (currentPage.number < currentEntry.pages.size()) {
                    PageButton btn = new PageButton(guiLeft + guiWidth - 25, guiTop + guiHeight - 20, PageButton.Direction.right, this, currentChapter, currentEntry, currentEntry.pages.get(currentPage.number));
                    btn.drawButtonForegroundLayer(mouseX, mouseY);
                    buttons[1] = btn;
                }
                if (currentPage.number > 1) {
                    PageButton btn = new PageButton(guiLeft + 10, guiTop + guiHeight - 20, PageButton.Direction.left, this, currentChapter, currentEntry, currentEntry.pages.get(currentPage.number - 2));
                    btn.drawButtonForegroundLayer(mouseX, mouseY);
                    buttons[0] = btn;
                }
            }

            btnBack = new PageButton(guiLeft + guiWidth / 2 - 10, guiTop + guiHeight - 20, PageButton.Direction.back, this, currentChapter, null, null);
            btnBack.drawButtonForegroundLayer(mouseX, mouseY);
            buttons[2] = btnBack;

            if (currentPage instanceof Structure.PageTextOnly)
                this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + "." + currentEntry.subIdentifier + "." + currentPage.number), x, y, 135, 0);
            else if (currentPage instanceof Structure.PageImageAndText) {
                this.mc.getTextureManager().bindTexture(((Structure.PageImageAndText)currentPage).resLoc);
                drawScaledCustomSizeModalRect(this.guiLeft + 24, this.guiTop + 24, 0, 0, 280, 320, 113, 130, 280, 320);
                this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + "." + currentEntry.subIdentifier + "." + currentPage.number), x, guiTop + guiHeight - 49, 130, 0);
            } else if (currentPage instanceof Structure.PageRecipeAndText) {
                this.mc.getTextureManager().bindTexture(this.resLocCraftingOverlay);
                GlStateManager.enableBlend();
                drawScaledCustomSizeModalRect(this.guiLeft + 15, this.guiTop + 25, 0, 0, 140, 85, 140, 85, 140, 85);
                this.fontRendererObj.drawSplitString(parseIdentifier(currentChapter.identifier + "." + currentEntry.subIdentifier + "." + currentPage.number), x, guiTop + guiHeight - 85, 130, 0);
                Structure.PageRecipeAndText page = (Structure.PageRecipeAndText) currentPage;
                int item_x = 0, item_y = 0;
                for (ItemStack stack : page.input) {
                    if (stack != null) {
                        stack.setItemDamage(0);
                        RenderHelper.enableGUIStandardItemLighting();
                        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(stack, this.guiLeft + item_x + 25, this.guiTop + item_y + 35);
                    }

                    item_x += 25;
                    if (item_x > 50) {
                        item_x = 0;
                        item_y += 25;
                    }
                }
                Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(page.result, this.guiLeft + 120, this.guiTop + 60);
            } else if (currentPage instanceof Structure.PageMultiBlockAndText) {
                MultiBlockStructure struct = ((Structure.PageMultiBlockAndText)currentPage).structure;
            }
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

    public String parseIdentifier(String identifier) {
        if (!identifier.endsWith(".contents.name"))
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

}
