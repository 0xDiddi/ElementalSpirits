package theultimatehose.elementalspirits.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.block.base.BlockESBase;

import javax.annotation.Nullable;
import java.util.List;

public class BlockEarthAltarRing extends BlockESBase {

    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");

    public static final AxisAlignedBB CENTER_AABB = new AxisAlignedBB(0.25, 0.25, 0.25, 0.75, 0.75, 0.75);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.5, 0.375, 0.375, 1, 0.625, 0.625);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0, 0.375, 0.375, 0.5, 0.625, 0.625);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.375, 0.375, 0, 0.625, 0.625, 0.5);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.375, 0.375, 0.5, 0.625, 0.625, 1);
    public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.375, 0.5, 0.375, 0.625, 1, 0.625);
    public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.375, 0, 0.375, 0.625, 0.5, 0.625);

    public BlockEarthAltarRing() {
        super(Material.ROCK, MapColor.GRASS, Names.BLOCK_EARTH_ALTAR_RING);
    }

    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
        state = state.getActualState(worldIn, pos);
        boolean north = state.getValue(NORTH), south = state.getValue(SOUTH);
        boolean east = state.getValue(EAST), west = state.getValue(WEST);
        boolean up = state.getValue(UP), down = state.getValue(DOWN);

        if ((!north && !south && !east && !west && !up && !down) || (north ^ south) || (east ^ west) || (up ^ down)) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, CENTER_AABB);
        }

        if (north) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
        }
        if (south) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
        }
        if (east) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
        }
        if (west) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
        }
        if (up) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, UP_AABB);
        }
        if (down) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, DOWN_AABB);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        state = state.getActualState(source, pos);
        boolean north = state.getValue(NORTH), south = state.getValue(SOUTH);
        boolean east = state.getValue(EAST), west = state.getValue(WEST);
        boolean up = state.getValue(UP), down = state.getValue(DOWN);

        AxisAlignedBB aabb = new AxisAlignedBB(0.5, 0.5, 0.5, 0.5, 0.5, 0.5);

        if ((!north && !south && !east && !west && !up && !down) || (north ^ south) || (east ^ west) || (up ^ down)) {
            aabb = aabb.union(CENTER_AABB);
        }

        if (north) {
            aabb = aabb.union(NORTH_AABB);
        }
        if (south) {
            aabb = aabb.union(SOUTH_AABB);
        }
        if (east) {
            aabb = aabb.union(EAST_AABB);
        }
        if (west) {
            aabb = aabb.union(WEST_AABB);
        }
        if (up) {
            aabb = aabb.union(UP_AABB);
        }
        if (down) {
            aabb = aabb.union(DOWN_AABB);
        }

        return aabb;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }

    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos).getBlock();
        return block instanceof BlockEarthAltarRing || block instanceof BlockEarthInfused;
    }

    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(NORTH, this.canConnectTo(worldIn, pos.north())).withProperty(EAST, this.canConnectTo(worldIn, pos.east())).withProperty(SOUTH, this.canConnectTo(worldIn, pos.south())).withProperty(WEST, this.canConnectTo(worldIn, pos.west())).withProperty(UP, this.canConnectTo(worldIn, pos.up())).withProperty(DOWN, this.canConnectTo(worldIn, pos.down()));
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, NORTH, EAST, WEST, SOUTH, UP, DOWN);
    }

}
