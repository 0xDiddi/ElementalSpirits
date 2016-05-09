package theultimatehose.elementalspirits.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.util.Util;

public class BlockEarthInfused extends Block {

    public BlockEarthInfused() {
        super(Material.rock, MapColor.stoneColor);
        this.setHardness(4);
        this.setResistance(25);
        this.setHarvestLevel("pickaxe", 2);
        ElementalSpirits.proxy.addSimpleRenderer(new ItemStack(this), new ResourceLocation(Util.MOD_ID_LOWER, Names.BLOCK_EARTH_INFUSED));
    }

}
