package theultimatehose.elementalspirits.multiblock.structures;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.block.BlockManager;
import theultimatehose.elementalspirits.multiblock.MultiBlockStructure;

public class EarthAltar extends MultiBlockStructure {

    public EarthAltar() {
        this.checkMatrix = new Block[4][][];

        this.checkMatrix[0] = new Block[][]{
                {null, null, null, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, null, null, null},
                {null , Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, BlockManager.blockEarthInfused, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, null},
                {null , Blocks.DOUBLE_STONE_SLAB, BlockManager.blockEarthInfused, Blocks.DOUBLE_STONE_SLAB, BlockManager.blockEarthInfused, Blocks.DOUBLE_STONE_SLAB, BlockManager.blockEarthInfused, Blocks.DOUBLE_STONE_SLAB, null},
                {Blocks.DOUBLE_STONE_SLAB , Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, BlockManager.blockEarthInfused, BlockManager.blockEarthInfused, BlockManager.blockEarthInfused, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB},
                {Blocks.DOUBLE_STONE_SLAB , BlockManager.blockEarthInfused, BlockManager.blockEarthInfused, BlockManager.blockEarthInfused, BlockManager.blockEarthInfused, BlockManager.blockEarthInfused, BlockManager.blockEarthInfused, BlockManager.blockEarthInfused, Blocks.DOUBLE_STONE_SLAB},
                {Blocks.DOUBLE_STONE_SLAB , Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, BlockManager.blockEarthInfused, BlockManager.blockEarthInfused, BlockManager.blockEarthInfused, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB},
                {null , Blocks.DOUBLE_STONE_SLAB, BlockManager.blockEarthInfused, Blocks.DOUBLE_STONE_SLAB, BlockManager.blockEarthInfused, Blocks.DOUBLE_STONE_SLAB, BlockManager.blockEarthInfused, Blocks.DOUBLE_STONE_SLAB, null},
                {null , Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, BlockManager.blockEarthInfused, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, null},
                {null, null, null, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, null, null, null}
        };
    }

}
