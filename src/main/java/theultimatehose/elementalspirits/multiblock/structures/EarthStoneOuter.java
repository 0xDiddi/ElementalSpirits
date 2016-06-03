package theultimatehose.elementalspirits.multiblock.structures;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.block.BlockManager;
import theultimatehose.elementalspirits.multiblock.MultiBlockStructure;

public class EarthStoneOuter extends MultiBlockStructure {

    public EarthStoneOuter() {
        this.checkMatrix = new Block[1][][];

        this.checkMatrix[0] = new Block[][]{
                {null, null, null, Blocks.DOUBLE_STONE_SLAB, null, null, null},
                {null, Blocks.DOUBLE_STONE_SLAB, null, null, null, Blocks.DOUBLE_STONE_SLAB, null},
                {null, null, null, null, null, null, null},
                {Blocks.DOUBLE_STONE_SLAB, null, null, null, null, null, Blocks.DOUBLE_STONE_SLAB},
                {null, null, null, null, null, null, null},
                {null, Blocks.DOUBLE_STONE_SLAB, null, null, null, Blocks.DOUBLE_STONE_SLAB, null},
                {null, null, null, Blocks.DOUBLE_STONE_SLAB, null, null, null}};

        this.startPosCheck = new BlockPos(3, 0, 3);

        this.placeMatrix = new Block[1][][];

        this.placeMatrix[0] = new Block[][]{
                {null, null, null, BlockManager.blockEarthInfused, null, null, null},
                {null, BlockManager.blockEarthInfused, null, null, null, BlockManager.blockEarthInfused, null},
                {null, null, null, null, null, null, null},
                {BlockManager.blockEarthInfused, null, null, null, null, null, BlockManager.blockEarthInfused},
                {null, null, null, null, null, null, null},
                {null, BlockManager.blockEarthInfused, null, null, null, BlockManager.blockEarthInfused, null},
                {null, null, null, BlockManager.blockEarthInfused, null, null, null}};

        this.startPosPlace = new BlockPos(3, 0, 3);
    }
}
