package theultimatehose.elementalspirits.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.gui.GuiHandler;
import theultimatehose.elementalspirits.util.Util;

public class ItemAncientScroll extends Item {

    public ItemAncientScroll() {
        this.setMaxStackSize(1);
        ElementalSpirits.proxy.addSimpleRenderer(new ItemStack(this), new ResourceLocation(Util.MOD_ID_LOWER, Names.ITEM_SCROLL));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        playerIn.openGui(ElementalSpirits.instance, GuiHandler.SCROLL_ID, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
        return super.onItemRightClick(itemStackIn, worldIn, playerIn);
    }

}
