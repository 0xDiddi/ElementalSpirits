package theultimatehose.elementalspirits.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RenderUtil {

    public static ItemStack getRenderableItemstack(Block block) {

        if (block == Blocks.redstone_wire)
            return new ItemStack(Items.redstone);
        if (block == Blocks.double_stone_slab)
            return new ItemStack(Blocks.stone_slab, 2);

        return new ItemStack(block);
    }

}
