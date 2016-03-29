package theultimatehose.elementalspirits.multiblock;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

public class EarthRodStructure extends MultiBlockStructure {

    public EarthRodStructure() {
        this.matrix = new Block[13][3][3];

        this.matrix[0] = new Block[][] {{null, Blocks.redstone_block, null},
                                        {Blocks.redstone_block, Blocks.redstone_block, Blocks.redstone_block},
                                        {null, Blocks.redstone_block, null}};
        this.matrix[1] = new Block[][] {{null, null, null},
                                        {null, Blocks.redstone_block, null},
                                        {null, null, null}};
        this.matrix[2] = new Block[][] {{null, null, null},
                                        {null, Blocks.mossy_cobblestone, null},
                                        {null, null, null}};
        this.matrix[3] = new Block[][] {{null, null, null},
                                        {null, Blocks.iron_block, null},
                                        {null, null, null}};
        this.matrix[4] = new Block[][] {{null, null, null},
                                        {null, Blocks.iron_block, null},
                                        {null, null, null}};
        this.matrix[5] = new Block[][] {{null, null, null},
                                        {null, Blocks.iron_block, null},
                                        {null, null, null}};
        this.matrix[6] = new Block[][] {{null, null, null},
                                        {null, Blocks.mossy_cobblestone, null},
                                        {null, null, null}};
        this.matrix[7] = new Block[][] {{null, null, null},
                                        {null, Blocks.mossy_cobblestone, null},
                                        {null, null, null}};
        this.matrix[8] = new Block[][] {{null, null, null},
                                        {null, Blocks.emerald_block, null},
                                        {null, null, null}};
        this.matrix[9] = new Block[][] {{null, null, null},
                                        {null, Blocks.emerald_block, null},
                                        {null, null, null}};
        this.matrix[10] = new Block[][] {{Blocks.diamond_block, Blocks.diamond_block, Blocks.diamond_block},
                                         {Blocks.diamond_block, Blocks.diamond_block, Blocks.diamond_block},
                                         {Blocks.diamond_block, Blocks.diamond_block, Blocks.diamond_block}};
        this.matrix[11] = new Block[][] {{null, Blocks.diamond_block, null},
                                         {Blocks.diamond_block, Blocks.diamond_block, Blocks.diamond_block},
                                         {null, Blocks.diamond_block, null}};
        this.matrix[12] = new Block[][] {{null, null, null},
                                         {null, Blocks.diamond_block, null},
                                         {null, null, null}};

        this.startPos = new BlockPos(1, 12, 1);
    }

}
