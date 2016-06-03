package theultimatehose.elementalspirits.scroll.structure.pages;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import theultimatehose.elementalspirits.multiblock.MultiBlockStructure;
import theultimatehose.elementalspirits.scroll.GuiScroll;
import theultimatehose.elementalspirits.scroll.buttons.LayerChangeButon;
import theultimatehose.elementalspirits.scroll.structure.Page;
import theultimatehose.elementalspirits.util.ModUtil;
import theultimatehose.elementalspirits.util.RenderUtil;

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

        int offsetX = (gui.guiWidth - this.width * 20) / 2;
        int offsetY = 30;

        int blockX = 0, blockY = 0;
        for (Block[] row : layer) {
            for (Block b : row) {
                if (b != null && b != Blocks.AIR) {
                    ItemStack stack = RenderUtil.getRenderableItemstack(b);
                    RenderHelper.enableGUIStandardItemLighting();
                    Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(stack, gui.guiLeft + blockX + offsetX, gui.guiTop + blockY + offsetY);
                    if (stack.stackSize == 2) {
                        Minecraft.getMinecraft().getRenderItem().zLevel = 100;
                        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(stack, gui.guiLeft + blockX + offsetX, gui.guiTop + blockY + offsetY - 4);
                        Minecraft.getMinecraft().getRenderItem().zLevel = 0;
                    }
                }
                blockX += 20;
            }
            blockX = 0;
            blockY += 20;
        }

        //Using this dummy (a 1x1 black image), because Gui.drawRect(...) messes up everything
        ResourceLocation dummyLoc = new ResourceLocation(ModUtil.MOD_ID_LOWER, "textures/dummy.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(dummyLoc);

        for (int lineX = 0; lineX <= this.width; lineX ++) {
            Gui.drawScaledCustomSizeModalRect(gui.guiLeft + offsetX + (lineX * 20) - 2, gui.guiTop + offsetY - 2, 0, 0, 1, 1, 1, (this.height * 20) + 1, 1, 1);
        }
        for (int lineY = 0; lineY <= this.height; lineY ++) {
            Gui.drawScaledCustomSizeModalRect(gui.guiLeft + offsetX - 2, gui.guiTop + offsetY + (lineY * 20) - 2, 0, 0, 1, 1, (this.width * 20) + 1, 1, 1, 1);
        }

        if (this.maxLayer > 1)
            gui.getFontRendererObj().drawSplitString(gui.parseIdentifier("layer") + TextFormatting.BLACK + ": " + this.currentLayer, x, gui.guiTop + offsetY + (this.height * 20) + 5, GuiScroll.TEXT_WRAP_WIDTH, 0);

        gui.getFontRendererObj().drawSplitString(gui.parseIdentifier(gui.currentChapter.identifier + "." + gui.currentEntry.subIdentifier + "." + gui.currentPage.number), x, gui.guiTop + offsetY + (this.height * 20) + ((this.maxLayer > 1) ? 15 : 5), GuiScroll.TEXT_WRAP_WIDTH, 0);

    }

}
