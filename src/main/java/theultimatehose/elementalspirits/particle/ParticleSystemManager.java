package theultimatehose.elementalspirits.particle;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class ParticleSystemManager {

    public static ParticleSystemManager INSTANCE = new ParticleSystemManager();

    private ArrayList<TimedSpawn> spawns;

    public ParticleSystemManager() {
        this.spawns = new ArrayList<>();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void tick(TickEvent event) {
        for (int i = 0; i < spawns.size(); i++) {
            TimedSpawn spawn = spawns.get(i);
            spawn.system.spawnSingle(spawn.world, spawn.pos);
            spawn.ticks--;
            if (spawn.ticks <= 0)
                spawns.remove(i);
            else
                spawns.set(i, spawn);
        }
    }

    public void addSpawnForTime(ParticleSystem system, int ticks, World world, BlockPos pos) {
        TimedSpawn spawn = new TimedSpawn(system, ticks, world, pos);
        this.spawns.add(spawn);
    }

    private class TimedSpawn {
        ParticleSystem system;
        int ticks;
        World world;
        BlockPos pos;

        TimedSpawn(ParticleSystem system, int ticks, World world, BlockPos pos) {
            this.system = system;
            this.ticks = ticks;
            this.world = world;
            this.pos = pos;
        }
    }

}
