package theultimatehose.elementalspirits.scroll.structure.pages;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import theultimatehose.elementalspirits.multiblock.MultiBlockStructure;
import theultimatehose.elementalspirits.scroll.GuiScroll;
import theultimatehose.elementalspirits.scroll.LayerChangeButon;
import theultimatehose.elementalspirits.scroll.structure.Page;
import theultimatehose.elementalspirits.util.Util;

public class PageMultiBlockAndText extends Page {

    public final MultiBlockStructure structure;
    public int currentLayer = 0;
    public int maxLayer = 0;
    public int width, height;

    public PageMultiBlockAndText(int number, MultiBlockStructure structure) {
        super(number);
        this.structure = structure;
        this.maxLayer = structure.checkMatrix.length - 1;
        this.width = structure.checkMatrix[0].length;
        this.height = structure.checkMatrix[0][0].length;
    }

    @Override
    public void render(GuiScroll gui, int x, int y) {
        if (currentLayer < maxLayer)
            gui.buttons.add(new LayerChangeButon(0, gui.guiLeft + gui.guiWidth - 22, gui.guiTop + 30, LayerChangeButon.Direction.up, this));
        if (currentLayer > 0)
            gui.buttons.add(new LayerChangeButon(0, gui.guiLeft + gui.guiWidth - 22, gui.guiTop + 50, LayerChangeButon.Direction.down, this));

        Block[][] layer = structure.checkMatrix[currentLayer];

        int offsetX = 20, offsetY = 30;

        int blockX = 0, blockY = 0;
        for (Block[] row : layer) {
            for (Block b : row) {
                if (b != null) {
                    ItemStack stack = new ItemStack(b);
                    RenderHelper.enableGUIStandardItemLighting();
                    Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(stack, gui.guiLeft + blockX + offsetX, gui.guiTop + blockY + offsetY);
                }
                blockX += 20;
            }
            blockX = 0;
            blockY += 20;
        }

        //Using this dummy (a 1x1 black image), because Gui.drawRect(...) messes up everything
        ResourceLocation dummyLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/dummy.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(dummyLoc);

        for (int lineX = 0; lineX <= this.width; lineX ++) {
            Gui.drawScaledCustomSizeModalRect(gui.guiLeft + offsetX + (lineX * 20) - 2, gui.guiTop + offsetY - 2, 0, 0, 1, 1, 1, (this.height * 20) + 1, 1, 1);
        }
        for (int lineY = 0; lineY <= this.height; lineY ++) {
            Gui.drawScaledCustomSizeModalRect(gui.guiLeft + offsetX - 2, gui.guiTop + offsetY + (lineY * 20) - 2, 0, 0, 1, 1, (this.width * 20) + 1, 1, 1, 1);
        }

        gui.getFontRendererObj().drawSplitString(gui.parseIdentifier("layer") + EnumChatFormatting.BLACK + ": " + this.currentLayer, x, gui.guiTop + offsetX + (this.height * 20) + 15, GuiScroll.TEXT_WRAP_WIDTH, 0);

        gui.getFontRendererObj().drawSplitString(gui.parseIdentifier(gui.currentChapter.identifier + "." + gui.currentEntry.subIdentifier + "." + gui.currentPage.number), x, gui.guiTop + offsetX + (this.height * 20) + 25, GuiScroll.TEXT_WRAP_WIDTH, 0);

    }

}
