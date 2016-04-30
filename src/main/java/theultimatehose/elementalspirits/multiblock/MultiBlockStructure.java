package theultimatehose.elementalspirits.multiblock;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class MultiBlockStructure {

    /**
     * [][][] -> [y][x][z]
     */
    public Block[][][] checkMatrix;
    public BlockPos startPosCheck;
    /**
     * [][][] -> [y][x][z]
     */
    public Block[][][] placeMatrix;
    public BlockPos startPosPlace;

    /**
     * Checks the {@link World} for the defined matrix based on the given {@code startPos}.
     * @param world The world where to check for a match
     * @param startPos The starting pos
     * @param removeOnMatch Whether to remove the blocks when matrix is matched
     * @param ignoreEmpty Whether air blocks (in the matrix) are ignored or not
     * @return Whether the matrix was matched or not
     */
    public boolean checkMatrix(World world, BlockPos startPos, boolean removeOnMatch, boolean ignoreEmpty) {
        this.prepareMatrix();
        int offset_x = startPos.getX() - this.startPosCheck.getX();
        int offset_y = startPos.getY() - this.startPosCheck.getY();
        int offset_z = startPos.getZ() - this.startPosCheck.getZ();

        boolean flag = true;

        base: for (int y = 0; y < checkMatrix.length; y++) {
            for (int x = 0; x < checkMatrix[y].length; x++) {
                for (int z = 0; z < checkMatrix[y][x].length; z++) {
                    BlockPos blockPos = new BlockPos(x, y, z);
                    BlockPos realPos = blockPos.add(offset_x, offset_y, offset_z);

                    Block block = world.getChunkFromBlockCoords(realPos).getBlock(realPos);
                    if (block != checkMatrix[y][x][z]) {
                        if (!(ignoreEmpty && checkMatrix[y][x][z] == Blocks.air)) {
                            flag = false;
                            break base;
                        }
                    }
                }
            }
        }

        if (removeOnMatch && flag) {
            for (int y = 0; y < checkMatrix.length; y++) {
                for (int x = 0; x < checkMatrix[y].length; x++) {
                    for (int z = 0; z < checkMatrix[y][x].length; z++) {
                        BlockPos blockPos = new BlockPos(x, y, z);
                        BlockPos realPos = blockPos.add(offset_x, offset_y, offset_z);
                        if (checkMatrix[y][x][z] != Blocks.air)
                            world.setBlockToAir(realPos);
                    }
                }
            }
        }

        return flag;
    }

    /**
     * Replaces {@code null} blocks in the matrix with {@link Blocks#air}.
     */
    private void prepareMatrix() {
        for (int y = 0; y < checkMatrix.length; y++) {
            for (int x = 0; x < checkMatrix[y].length; x++) {
                for (int z = 0; z < checkMatrix[y][x].length; z++) {
                    if (checkMatrix[y][x][z] == null) {
                        checkMatrix[y][x][z] = Blocks.air;
                    }
                }
            }
        }
    }

    /**
     * Attempts to place the {@link #placeMatrix} in the {@code world} relative to the {@code startPos}.
     * @param world The world where to check for a match
     * @param startPos The starting pos
     * @param breakBlocking Whether blocks in the way will be broken (& dropped) or not
     * @param ignoreNull Whether null blocks (in the matrix) are ignored or not
     * @return Whether all blocks were placed successfully or not
     */
    public boolean placeBlocks(World world, BlockPos startPos, boolean breakBlocking, boolean ignoreNull) {
        this.prepareMatrix();
        int offset_x = startPos.getX() - this.startPosPlace.getX();
        int offset_y = startPos.getY() - this.startPosPlace.getY();
        int offset_z = startPos.getZ() - this.startPosPlace.getZ();

        boolean flag = true;

        for (int y = 0; y < checkMatrix.length; y++) {
            for (int x = 0; x < checkMatrix[y].length; x++) {
                for (int z = 0; z < checkMatrix[y][x].length; z++) {
                    BlockPos blockPos = new BlockPos(x, y, z);
                    BlockPos realPos = blockPos.add(offset_x, offset_y, offset_z);
                    Block block = placeMatrix[y][x][z];

                    if (ignoreNull || block != null) {
                        if (!world.isAirBlock(realPos) && block != Blocks.air)
                            if (breakBlocking)
                                world.getChunkFromBlockCoords(realPos).getBlock(realPos).dropBlockAsItem(world, realPos, world.getBlockState(realPos), 0);
                            else { flag = false; continue; }

                        if (block != null)
                            world.setBlockState(realPos, block.getDefaultState());
                    }

                }
            }
        }
        return flag;
    }

}
