package theultimatehose.elementalspirits.infusion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.item.ItemManager;
import theultimatehose.elementalspirits.item.ItemEarthRod;
import theultimatehose.elementalspirits.multiblock.structures.EarthRodStructure;
import theultimatehose.elementalspirits.util.ModUtil;
import theultimatehose.elementalspirits.util.StringUtil;

public class RitualEarthRodInfusion extends InfusionRitual {

    @Override
    public boolean canExecuteRitual(EntityPlayer player, ItemStack stack, World world, BlockPos pos) {
        if (stack.getItem() == ItemManager.itemEarthRod) {
            if (world.getBlockState(pos).getBlock() == Blocks.DIAMOND_BLOCK) {
                if (!ItemEarthRod.isRodInfused(stack)) {
                    EarthRodStructure struct = new EarthRodStructure();
                    if (struct.checkMatrix(world, pos, false, true)) {
                        return true;
                    }
                } else {
                    if (!world.isRemote)
                        player.addChatMessage(new TextComponentTranslation(StringUtil.getUnlocalizedChat("infuse_denied")));
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void executeRitual(EntityPlayer player, ItemStack stack, World world, BlockPos pos) {
        if (stack.getItem() == ItemManager.itemEarthRod) {
            if (world.getBlockState(pos).getBlock() == Blocks.DIAMOND_BLOCK) {
                if (!ItemEarthRod.isRodInfused(stack)) {
                    EarthRodStructure struct = new EarthRodStructure();
                    if (struct.checkMatrix(world, pos, true, true)) {
                        if (!world.isRemote)
                            player.addChatMessage(new TextComponentTranslation(StringUtil.getUnlocalizedChat("infuse_success")));
                        NBTTagCompound compound = stack.getTagCompound();
                        compound.setBoolean(ItemEarthRod.KEY_INFUSED, true);
                        stack.setTagCompound(compound);
                    }
                }
            }
        }
    }
}
