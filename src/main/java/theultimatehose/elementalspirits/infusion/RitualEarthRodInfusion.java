package theultimatehose.elementalspirits.infusion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.items.ItemEarthRod;
import theultimatehose.elementalspirits.multiblock.structures.EarthRodStructure;
import theultimatehose.elementalspirits.util.Util;

public class RitualEarthRodInfusion extends InfusionRitual {

    @Override
    public boolean canExecuteRitual(EntityPlayer player, ItemStack stack, World world, BlockPos pos) {
        if (stack.getItem() == ElementalSpirits.instance.itemEarthRod) {
            if (world.getChunkFromBlockCoords(pos).getBlock(pos) == Blocks.diamond_block) {
                if (!ItemEarthRod.isRodInfused(stack)) {
                    EarthRodStructure struct = new EarthRodStructure();
                    if (struct.checkMatrix(world, pos, false, true)) {
                        return true;
                    }
                } else {
                    if (!world.isRemote)
                        player.addChatMessage(new ChatComponentTranslation("chat." + Util.MOD_ID_LOWER + ".infuse_denied.msg"));
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void executeRitual(EntityPlayer player, ItemStack stack, World world, BlockPos pos) {
        if (stack.getItem() == ElementalSpirits.instance.itemEarthRod) {
            if (world.getChunkFromBlockCoords(pos).getBlock(pos) == Blocks.diamond_block) {
                if (!ItemEarthRod.isRodInfused(stack)) {
                    EarthRodStructure struct = new EarthRodStructure();
                    if (struct.checkMatrix(world, pos, true, true)) {
                        if (!world.isRemote)
                            player.addChatMessage(new ChatComponentTranslation("chat." + Util.MOD_ID_LOWER + ".infuse_success.msg"));
                        NBTTagCompound compound = stack.getTagCompound();
                        compound.setBoolean(ItemEarthRod.KEY_INFUSED, true);
                        stack.setTagCompound(compound);
                    }
                }
            }
        }
    }
}
