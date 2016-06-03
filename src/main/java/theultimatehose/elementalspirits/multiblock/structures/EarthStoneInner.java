package theultimatehose.elementalspirits.multiblock.structures;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import theultimatehose.elementalspirits.multiblock.MultiBlockStructure;

public class EarthStoneInner extends MultiBlockStructure {

    public EarthStoneInner() {
        this.checkMatrix = new Block[1][][];
        this.checkMatrix[0] = new Block[][]{
                {null, Blocks.REDSTONE_WIRE, Blocks.REDSTONE_WIRE, Blocks.REDSTONE_WIRE, null},
                {Blocks.REDSTONE_WIRE, Blocks.REDSTONE_WIRE, Blocks.STONE_SLAB, Blocks.REDSTONE_WIRE, Blocks.REDSTONE_WIRE},
                {Blocks.REDSTONE_WIRE, Blocks.STONE_SLAB, Blocks.EMERALD_ORE, Blocks.STONE_SLAB, Blocks.REDSTONE_WIRE},
                {Blocks.REDSTONE_WIRE, Blocks.REDSTONE_WIRE, Blocks.STONE_SLAB, Blocks.REDSTONE_WIRE, Blocks.REDSTONE_WIRE},
                {null, Blocks.REDSTONE_WIRE, Blocks.REDSTONE_WIRE, Blocks.REDSTONE_WIRE, null}};


        this.startPosCheck = new BlockPos(2, 0, 2);
    }

}
