package theultimatehose.elementalspirits.multiblock.structures;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import theultimatehose.elementalspirits.multiblock.MultiBlockStructure;

public class EarthRodStructure extends MultiBlockStructure {

    public EarthRodStructure() {
        this.checkMatrix = new Block[13][][];

        this.checkMatrix[0] = new Block[][]{
                {null, Blocks.REDSTONE_BLOCK, null},
                {Blocks.REDSTONE_BLOCK, Blocks.REDSTONE_BLOCK, Blocks.REDSTONE_BLOCK},
                {null, Blocks.REDSTONE_BLOCK, null}};
        this.checkMatrix[1] = new Block[][]{
                {null, null, null},
                {null, Blocks.REDSTONE_BLOCK, null},
                {null, null, null}};
        this.checkMatrix[2] = new Block[][]{
                {null, null, null},
                {null, Blocks.MOSSY_COBBLESTONE, null},
                {null, null, null}};
        this.checkMatrix[3] = new Block[][]{
                {null, null, null},
                {null, Blocks.IRON_BLOCK, null},
                {null, null, null}};
        this.checkMatrix[4] = new Block[][]{
                {null, null, null},
                {null, Blocks.IRON_BLOCK, null},
                {null, null, null}};
        this.checkMatrix[5] = new Block[][]{
                {null, null, null},
                {null, Blocks.IRON_BLOCK, null},
                {null, null, null}};
        this.checkMatrix[6] = new Block[][]{
                {null, null, null},
                {null, Blocks.MOSSY_COBBLESTONE, null},
                {null, null, null}};
        this.checkMatrix[7] = new Block[][]{
                {null, null, null},
                {null, Blocks.MOSSY_COBBLESTONE, null},
                {null, null, null}};
        this.checkMatrix[8] = new Block[][]{
                {null, null, null},
                {null, Blocks.EMERALD_BLOCK, null},
                {null, null, null}};
        this.checkMatrix[9] = new Block[][]{
                {null, null, null},
                {null, Blocks.EMERALD_BLOCK, null},
                {null, null, null}};
        this.checkMatrix[10] = new Block[][]{
                {Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK},
                {Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK},
                {Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK}};
        this.checkMatrix[11] = new Block[][]{
                {null, Blocks.DIAMOND_BLOCK, null},
                {Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_BLOCK},
                {null, Blocks.DIAMOND_BLOCK, null}};
        this.checkMatrix[12] = new Block[][]{
                {null, null, null},
                {null, Blocks.DIAMOND_BLOCK, null},
                {null, null, null}};

        this.startPosCheck = new BlockPos(1, 12, 1);
    }

}
