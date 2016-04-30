package theultimatehose.elementalspirits.particle;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ParticleSystem {

    private ArrayList<Particle> particles;

    public ParticleSystem() {
        this.particles = new ArrayList<>();
    }

    public void spawnSingle(World world, BlockPos pos) {
        if (world.isRemote)
            for (Particle p : this.particles) {
                Vec3 actualPos = p.offset.addVector(pos.getX(), pos.getY(), pos.getZ());
                world.spawnParticle(p.type, actualPos.xCoord, actualPos.yCoord, actualPos.zCoord, p.motion.xCoord, p.motion.yCoord, p.motion.zCoord);
            }
    }

    public void addParticle(EnumParticleTypes type, Vec3 offset, Vec3 motion) {
        Particle p = new Particle(type, offset, motion);
        this.particles.add(p);
    }

    private class Particle {
        private EnumParticleTypes type;
        private Vec3 offset;
        private Vec3 motion;

        Particle(EnumParticleTypes type, Vec3 offset, Vec3 motion) {
            this.type = type;
            this.offset = offset;
            this.motion = motion;
        }
    }

}
