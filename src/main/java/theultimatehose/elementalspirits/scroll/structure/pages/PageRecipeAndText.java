package theultimatehose.elementalspirits.scroll.structure.pages;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import theultimatehose.elementalspirits.scroll.GuiScroll;
import theultimatehose.elementalspirits.scroll.structure.Page;

import java.util.Collection;

public class PageRecipeAndText extends Page {

    public final ItemStack[] input;
    public final ItemStack result;

    public PageRecipeAndText(int number, ShapedOreRecipe recipe) {
        super(number);
        this.input = new ItemStack[9];

        for (int i = 0; i < recipe.getInput().length; i++) {
            Object o = recipe.getInput()[i];

            if (o instanceof ItemStack)
                this.input[i] = (ItemStack) o;
            else if (o instanceof Collection) {
                //noinspection unchecked
                this.input[i] = (ItemStack) ((Collection<ItemStack>)o).toArray()[0];
            }
        }

        this.result = recipe.getRecipeOutput();
    }

    @Override
    public void render(GuiScroll gui, int x, int y) {
        gui.mc.getTextureManager().bindTexture(gui.resLocCraftingOverlay);
        GlStateManager.enableBlend();
        Gui.drawScaledCustomSizeModalRect(gui.guiLeft + 15, gui.guiTop + 25, 0, 0, 140, 85, 140, 85, 140, 85);
        gui.getFontRendererObj().drawSplitString(gui.parseIdentifier(gui.currentChapter.identifier + "." + gui.currentEntry.subIdentifier + "." + gui.currentPage.number), x, gui.guiTop + gui.guiHeight - 85, GuiScroll.TEXT_WRAP_WIDTH, 0);
        theultimatehose.elementalspirits.scroll.structure.pages.PageRecipeAndText page = (theultimatehose.elementalspirits.scroll.structure.pages.PageRecipeAndText)gui.currentPage;
        int item_x = 0, item_y = 0;
        for (ItemStack stack : page.input) {
            if (stack != null) {
                if (stack.getMetadata() == OreDictionary.WILDCARD_VALUE)
                    stack.setItemDamage(0);
                RenderHelper.enableGUIStandardItemLighting();
                Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(stack, gui.guiLeft + item_x + 25, gui.guiTop + item_y + 35);
            }

            item_x += 25;
            if (item_x > 50) {
                item_x = 0;
                item_y += 25;
            }
        }
        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(page.result, gui.guiLeft + 120, gui.guiTop + 60);
    }
}
