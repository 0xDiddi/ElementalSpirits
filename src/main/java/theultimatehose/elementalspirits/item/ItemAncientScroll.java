package theultimatehose.elementalspirits.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.gui.GuiHandler;
import theultimatehose.elementalspirits.item.base.ItemESBase;

public class ItemAncientScroll extends ItemESBase {

    public ItemAncientScroll() {
        super(Names.ITEM_SCROLL);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        playerIn.openGui(ElementalSpirits.INSTANCE, GuiHandler.SCROLL_ID, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }

}
