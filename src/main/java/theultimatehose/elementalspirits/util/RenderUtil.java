package theultimatehose.elementalspirits.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RenderUtil {

    public static ItemStack getRenderableItemstack(Block block) {

        if (block == Blocks.REDSTONE_WIRE)
            return new ItemStack(Items.REDSTONE);
        if (block == Blocks.DOUBLE_STONE_SLAB)
            return new ItemStack(Blocks.STONE_SLAB, 2);

        return new ItemStack(block);
    }

}
