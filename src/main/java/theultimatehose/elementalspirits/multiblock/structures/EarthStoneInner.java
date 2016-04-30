package theultimatehose.elementalspirits.multiblock.structures;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import theultimatehose.elementalspirits.multiblock.MultiBlockStructure;

public class EarthStoneInner extends MultiBlockStructure {

    public EarthStoneInner() {
        this.checkMatrix = new Block[1][][];

        this.checkMatrix[0] = new Block[][]{
                {null, Blocks.redstone_wire, Blocks.redstone_wire, Blocks.redstone_wire, null},
                {Blocks.redstone_wire, Blocks.redstone_wire, Blocks.stone_slab, Blocks.redstone_wire, Blocks.redstone_wire},
                {Blocks.redstone_wire, Blocks.stone_slab, Blocks.emerald_ore, Blocks.stone_slab, Blocks.redstone_wire},
                {Blocks.redstone_wire, Blocks.redstone_wire, Blocks.stone_slab, Blocks.redstone_wire, Blocks.redstone_wire},
                {null, Blocks.redstone_wire, Blocks.redstone_wire, Blocks.redstone_wire, null}};

        this.startPosCheck = new BlockPos(2, 0, 2);
    }

}
