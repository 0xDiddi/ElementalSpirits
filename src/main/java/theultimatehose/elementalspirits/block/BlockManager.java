package theultimatehose.elementalspirits.block;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.block.base.BlockESBase;
import theultimatehose.elementalspirits.block.base.ItemBlockESBase;
import theultimatehose.elementalspirits.util.Util;

import java.util.ArrayList;
import java.util.List;

public class BlockManager {

    public static BlockEarthInfused blockEarthInfused;
    public static BlockEarthAltarRing blockEarthAltarRing;

    private static List<BlockESBase> REGISTERED_BLOCKS = new ArrayList<>();

    public static void init() {
        blockEarthInfused = new BlockEarthInfused();        REGISTERED_BLOCKS.add(blockEarthInfused);
        blockEarthAltarRing = new BlockEarthAltarRing();    REGISTERED_BLOCKS.add(blockEarthAltarRing);

        for (BlockESBase block : REGISTERED_BLOCKS) {
            registerBlock(block, block.getItemBlock());
            registerRendering(block);
        }
    }

    private static void registerBlock(BlockESBase block, ItemBlockESBase itemBlock) {
        block.setUnlocalizedName(Util.MOD_ID_LOWER + '.' + block.getName());
        block.setRegistryName(Util.MOD_ID_LOWER, block.getName());
        GameRegistry.register(block);

        itemBlock.setRegistryName(block.getRegistryName());
        GameRegistry.register(itemBlock);
    }

    private static void registerRendering(BlockESBase block) {
        ElementalSpirits.proxy.addSimpleRenderer(new ItemStack(block), new ResourceLocation(Util.MOD_ID_LOWER, block.getName()));
    }

}
