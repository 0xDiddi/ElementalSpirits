package theultimatehose.elementalspirits.multiblock;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class MultiBlockStructure {

    /**
     * [][][] -> [y][x][z]
     */
    public Block[][][] matrix;
    public BlockPos startPos;

    /**
     * Checks the {@code world} for the defined matrix based on the given {@code startPos}
     * @param world The world where to check for a match
     * @param startPos The starting pos
     * @param removeOnMatch Whether to remove the blocks when matrix is matched
     * @return Whether the matrix was matched or not
     */
    public boolean checkMatrix(World world, BlockPos startPos, boolean removeOnMatch) {
        this.prepareMatrix();
        int offset_x = startPos.getX() - this.startPos.getX();
        int offset_y = startPos.getY() - this.startPos.getY();
        int offset_z = startPos.getZ() - this.startPos.getZ();

        boolean flag = true;

        base: for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                for (int z = 0; z < matrix[y][x].length; z++) {
                    BlockPos blockPos = new BlockPos(x, y, z);
                    BlockPos realPos = blockPos.add(offset_x, offset_y, offset_z);

                    Block block = world.getChunkFromBlockCoords(realPos).getBlock(realPos);
                    if (block != matrix[y][x][z]) {
                        flag = false;
                        break base;
                    }
                }
            }
        }

        if (removeOnMatch && flag) {
            for (int y = 0; y < matrix.length; y++) {
                for (int x = 0; x < matrix[y].length; x++) {
                    for (int z = 0; z < matrix[y][x].length; z++) {
                        BlockPos blockPos = new BlockPos(x, y, z);
                        BlockPos realPos = blockPos.add(offset_x, offset_y, offset_z);
                        world.setBlockToAir(realPos);
                    }
                }
            }
        }

        return flag;
    }

    /**
     * Replaces {@code null} blocks in the matrix with {@code Blocks.air}
     */
    public void prepareMatrix() {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                for (int z = 0; z < matrix[y][x].length; z++) {
                    if (matrix[y][x][z] == null) {
                        matrix[y][x][z] = Blocks.air;
                    }
                }
            }
        }
    }

}
