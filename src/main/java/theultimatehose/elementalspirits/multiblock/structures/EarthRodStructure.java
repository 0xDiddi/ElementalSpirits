package theultimatehose.elementalspirits.multiblock.structures;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import theultimatehose.elementalspirits.multiblock.MultiBlockStructure;

public class EarthRodStructure extends MultiBlockStructure {

    public EarthRodStructure() {
        this.checkMatrix = new Block[13][][];

        this.checkMatrix[0] = new Block[][]{
                {null, Blocks.redstone_block, null},
                {Blocks.redstone_block, Blocks.redstone_block, Blocks.redstone_block},
                {null, Blocks.redstone_block, null}};
        this.checkMatrix[1] = new Block[][]{
                {null, null, null},
                {null, Blocks.redstone_block, null},
                {null, null, null}};
        this.checkMatrix[2] = new Block[][]{
                {null, null, null},
                {null, Blocks.mossy_cobblestone, null},
                {null, null, null}};
        this.checkMatrix[3] = new Block[][]{
                {null, null, null},
                {null, Blocks.iron_block, null},
                {null, null, null}};
        this.checkMatrix[4] = new Block[][]{
                {null, null, null},
                {null, Blocks.iron_block, null},
                {null, null, null}};
        this.checkMatrix[5] = new Block[][]{
                {null, null, null},
                {null, Blocks.iron_block, null},
                {null, null, null}};
        this.checkMatrix[6] = new Block[][]{
                {null, null, null},
                {null, Blocks.mossy_cobblestone, null},
                {null, null, null}};
        this.checkMatrix[7] = new Block[][]{
                {null, null, null},
                {null, Blocks.mossy_cobblestone, null},
                {null, null, null}};
        this.checkMatrix[8] = new Block[][]{
                {null, null, null},
                {null, Blocks.emerald_block, null},
                {null, null, null}};
        this.checkMatrix[9] = new Block[][]{
                {null, null, null},
                {null, Blocks.emerald_block, null},
                {null, null, null}};
        this.checkMatrix[10] = new Block[][]{
                {Blocks.diamond_block, Blocks.diamond_block, Blocks.diamond_block},
                {Blocks.diamond_block, Blocks.diamond_block, Blocks.diamond_block},
                {Blocks.diamond_block, Blocks.diamond_block, Blocks.diamond_block}};
        this.checkMatrix[11] = new Block[][]{
                {null, Blocks.diamond_block, null},
                {Blocks.diamond_block, Blocks.diamond_block, Blocks.diamond_block},
                {null, Blocks.diamond_block, null}};
        this.checkMatrix[12] = new Block[][]{
                {null, null, null},
                {null, Blocks.diamond_block, null},
                {null, null, null}};

        this.startPosCheck = new BlockPos(1, 12, 1);
    }

}
