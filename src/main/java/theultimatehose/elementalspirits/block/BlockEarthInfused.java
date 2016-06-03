package theultimatehose.elementalspirits.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.block.base.BlockESBase;

public class BlockEarthInfused extends BlockESBase {

    public BlockEarthInfused() {
        super(Material.ROCK, MapColor.STONE, Names.BLOCK_EARTH_INFUSED);
        this.setHardness(4);
        this.setResistance(25);
        this.setHarvestLevel("pickaxe", 2);
    }

}
