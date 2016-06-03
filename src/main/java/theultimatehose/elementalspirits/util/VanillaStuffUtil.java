package theultimatehose.elementalspirits.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class VanillaStuffUtil {

    public static void doEntityJump(Entity entity, float currentSpeed) {
        EntityPlayer entityplayer = (EntityPlayer) entity.getRidingEntity();
        EntityCreature entitycreature = (EntityCreature) entity;

        int i = MathHelper.floor_double(entity.posX);
        int j = MathHelper.floor_double(entity.posY);
        int k = MathHelper.floor_double(entity.posZ);

        float f2 = 0.91F;

        if (entity.onGround) {
            f2 = entity.worldObj.getBlockState(new BlockPos(MathHelper.floor_float((float) i), MathHelper.floor_float((float) j) - 1, MathHelper.floor_float((float) k))).getBlock().slipperiness * 0.91F;
        }

        float f3 = 0.16277136F / (f2 * f2 * f2);
        float f4 = MathHelper.sin(entitycreature.rotationYaw * (float) Math.PI / 180.0F);
        float f5 = MathHelper.cos(entitycreature.rotationYaw * (float) Math.PI / 180.0F);
        float f6 = entitycreature.getAIMoveSpeed() * f3;
        float f7 = Math.max(currentSpeed, 1.0F);
        f7 = f6 / f7;
        float f8 = currentSpeed * f7;
        float f9 = -(f8 * f4);
        float f10 = f8 * f5;

        if (MathHelper.abs(f9) > MathHelper.abs(f10)) {
            if (f9 < 0.0F) {
                f9 -= entity.width / 2.0F;
            }

            if (f9 > 0.0F) {
                f9 += entity.width / 2.0F;
            }

            f10 = 0.0F;
        } else {
            f9 = 0.0F;

            if (f10 < 0.0F) {
                f10 -= entity.width / 2.0F;
            }

            if (f10 > 0.0F) {
                f10 += entity.width / 2.0F;
            }
        }

        int l = MathHelper.floor_double(entity.posX + (double) f9);
        int i1 = MathHelper.floor_double(entity.posZ + (double) f10);
        int j1 = MathHelper.floor_float(entity.width + 1.0F);
        int k1 = MathHelper.floor_float(entity.height + entityplayer.height + 1.0F);
        int l1 = MathHelper.floor_float(entity.width + 1.0F);

        if (i != l || k != i1) {
            Block block = entity.worldObj.getBlockState(new BlockPos(i, j, k)).getBlock();
            boolean flag = !isStairOrSlab(block) && (block.getMaterial(block.getDefaultState()) != Material.AIR || !isStairOrSlab(entity.worldObj.getBlockState(new BlockPos(i, j - 1, k)).getBlock()));
/*TODO: Fix entity jumping
            if (flag && 0 == WalkNodeProcessor.func_176170_a(entity.worldObj, entity, l, j, i1, j1, k1, l1, false, false, true) && 1 == WalkNodeProcessor.func_176170_a(entity.worldObj, entity, i, j + 1, k, j1, k1, l1, false, false, true) && 1 == WalkNodeProcessor.func_176170_a(entity.worldObj, entity, l, j + 1, i1, j1, k1, l1, false, false, true)) {
                entitycreature.getJumpHelper().setJumping();
            }*/
        }
    }

    public static boolean isStairOrSlab(Block blockIn) {
        return blockIn instanceof BlockStairs || blockIn instanceof BlockSlab;
    }

}
