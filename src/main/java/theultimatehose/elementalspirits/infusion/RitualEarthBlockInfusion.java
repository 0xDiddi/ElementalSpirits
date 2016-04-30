package theultimatehose.elementalspirits.infusion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.items.ItemEarthRod;
import theultimatehose.elementalspirits.multiblock.structures.EarthStoneInner;
import theultimatehose.elementalspirits.multiblock.structures.EarthStoneOuter;
import theultimatehose.elementalspirits.particle.ParticleSystem;
import theultimatehose.elementalspirits.particle.ParticleSystemManager;

import java.util.Random;

public class RitualEarthBlockInfusion extends InfusionRitual {

    @Override
    public boolean canExecuteRitual(EntityPlayer player, ItemStack stack, World world, BlockPos pos) {
        if (stack.getItem() == ElementalSpirits.instance.itemEarthRod) {
            if (world.getChunkFromBlockCoords(pos).getBlock(pos) == Blocks.emerald_ore) {
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
    public void executeRitual(EntityPlayer player, ItemStack stack, World world, BlockPos pos) {
        if (stack.getItem() == ElementalSpirits.instance.itemEarthRod) {
            if (world.getChunkFromBlockCoords(pos).getBlock(pos) == Blocks.emerald_ore) {
                if (ItemEarthRod.isRodInfused(stack)) {
                    EarthStoneOuter outer = new EarthStoneOuter();
                    EarthStoneInner inner = new EarthStoneInner();

                    ParticleSystem system = createInfuseSystem();

                    if (outer.checkMatrix(world, pos, true, true) && inner.checkMatrix(world, pos, false, true)) {
                        outer.placeBlocks(world, pos, false, true);
                        ParticleSystemManager.INSTANCE.addSpawnForTime(system, 5, world, pos);
                    }
                }
            }
        }
    }

    private ParticleSystem createInfuseSystem() {
        ParticleSystem system = new ParticleSystem();
        Random rand = new Random();
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-3, 0.5, 0), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-2, 0.5, 0), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-3, 0.5, 1), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-2, 0.5, 1), new Vec3(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(4, 0.5, 0), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(3, 0.5, 0), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(3, 0.5, 1), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(4, 0.5, 1), new Vec3(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(1, 0.5, -3), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(0, 0.5, -3), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(0, 0.5, -2), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(1, 0.5, -2), new Vec3(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(1, 0.5, 4), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(0, 0.5, 4), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(0, 0.5, 3), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(1, 0.5, 3), new Vec3(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-1, 0.5, -1), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-2, 0.5, -1), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-1, 0.5, -2), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-2, 0.5, -2), new Vec3(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(2, 0.5, 2), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(3, 0.5, 2), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(2, 0.5, 3), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(3, 0.5, 3), new Vec3(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(2, 0.5, -1), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(3, 0.5, -1), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(2, 0.5, -2), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(3, 0.5, -2), new Vec3(0, -1 * rand.nextGaussian(), 0));

        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-1, 0.5, 2), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-2, 0.5, 2), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-1, 0.5, 3), new Vec3(0, -1 * rand.nextGaussian(), 0));
        system.addParticle(EnumParticleTypes.SPELL_MOB, new Vec3(-2, 0.5, 3), new Vec3(0, -1 * rand.nextGaussian(), 0));

        return system;
    }

}
