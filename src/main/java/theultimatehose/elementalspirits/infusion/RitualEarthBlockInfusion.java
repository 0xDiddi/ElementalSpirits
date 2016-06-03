package theultimatehose.elementalspirits.infusion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.item.ItemManager;
import theultimatehose.elementalspirits.item.ItemEarthRod;
import theultimatehose.elementalspirits.multiblock.structures.EarthStoneInner;
import theultimatehose.elementalspirits.multiblock.structures.EarthStoneOuter;
import theultimatehose.elementalspirits.particle.ParticleSystem;
import theultimatehose.elementalspirits.particle.ParticleSystemManager;
import theultimatehose.elementalspirits.util.DelayUtil;

import java.util.Random;

public class RitualEarthBlockInfusion extends InfusionRitual {

    @Override
    public boolean canExecuteRitual(EntityPlayer player, ItemStack stack, World world, BlockPos pos) {
        if (stack.getItem() == ItemManager.itemEarthRod) {
            if (world.getBlockState(pos).getBlock() == Blocks.EMERALD_ORE) {
                if (ItemEarthRod.isRodInfused(stack)) {
                    EarthStoneOuter outer = new EarthStoneOuter();
                    EarthStoneInner inner = new EarthStoneInner();

                    if (outer.checkMatrix(world, pos, false, true) && inner.checkMatrix(world, pos, false, true))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public void executeRitual(EntityPlayer player, ItemStack stack, final World world, final BlockPos pos) {
        if (stack.getItem() == ItemManager.itemEarthRod) {
            if (world.getBlockState(pos).getBlock() == Blocks.EMERALD_ORE) {
                if (ItemEarthRod.isRodInfused(stack)) {
                    final EarthStoneOuter outer = new EarthStoneOuter();
                    final EarthStoneInner inner = new EarthStoneInner();

                    ParticleSystem infuseSystem = createInfuseSystem();
                    final ParticleSystem finishSystem = createFinishSystem();

                    if (outer.checkMatrix(world, pos, false, true) && inner.checkMatrix(world, pos, false, true)) {
                        ParticleSystemManager.INSTANCE.addSpawnForTimeWithDelay(infuseSystem, 2000, world, pos, 20);
                        DelayUtil.INSTANCE.postDelayed(2000, new DelayUtil.DelayedAction() {
                            @Override
                            public void execute() {
                                if (outer.checkMatrix(world, pos, true, true) && inner.checkMatrix(world, pos, false, true)) {
                                    outer.placeBlocks(world, pos, false, true);
                                    ParticleSystemManager.INSTANCE.addSpawnForTime(finishSystem, 50, world, pos);
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    private ParticleSystem createInfuseSystem() {
        ParticleSystem system = new ParticleSystem();
        Random rand = new Random();
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-3, 0.5, 0), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-2, 0.5, 0), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-3, 0.5, 1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-2, 0.5, 1), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(4, 0.5, 0), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(3, 0.5, 0), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(3, 0.5, 1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(4, 0.5, 1), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(1, 0.5, -3), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(0, 0.5, -3), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(0, 0.5, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(1, 0.5, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(1, 0.5, 4), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(0, 0.5, 4), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(0, 0.5, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(1, 0.5, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-1, 0.5, -1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-2, 0.5, -1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-1, 0.5, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-2, 0.5, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(2, 0.5, 2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(3, 0.5, 2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(2, 0.5, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(3, 0.5, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(2, 0.5, -1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(3, 0.5, -1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(2, 0.5, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(3, 0.5, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-1, 0.5, 2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-2, 0.5, 2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-1, 0.5, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3d(-2, 0.5, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        return system;
    }

    private ParticleSystem createFinishSystem() {
        ParticleSystem system = new ParticleSystem();
        Random rand = new Random();
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-3, 1, 0), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-2, 1, 0), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-3, 1, 1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-2, 1, 1), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(4, 1, 0), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(3, 1, 0), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(3, 1, 1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(4, 1, 1), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(1, 1, -3), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(0, 1, -3), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(0, 1, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(1, 1, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(1, 1, 4), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(0, 1, 4), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(0, 1, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(1, 1, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-1, 1, -1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-2, 1, -1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-1, 1, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-2, 1, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(2, 1, 2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(3, 1, 2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(2, 1, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(3, 1, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(2, 1, -1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(3, 1, -1), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(2, 1, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(3, 1, -2), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-1, 1, 2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-2, 1, 2), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-1, 1, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.VILLAGER_HAPPY, new Vec3d(-2, 1, 3), new Vec3d(0, -1 * rand.nextGaussian(), 0));

        return system;
    }

}
