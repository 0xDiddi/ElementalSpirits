package theultimatehose.elementalspirits.infusion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public abstract class InfusionRitual {

    public static ArrayList<InfusionRitual> registeredRituals;

    public static void init() {
        registeredRituals = new ArrayList<>();
        registeredRituals.add(new RitualEarthRodInfusion());
    }

    /**
     * Checks if this ritual can be executed at the given position by the given player holding the given itemstack in the given world.<br>
     *
     * If you want to avoid spam, please check {@code world.isRemote} before sending Chat messages
     * as this method is executed on client AND server because otherwise removing Blocks (especially MultiBlockStructures) causes sync-issues.
     * @param player The player
     * @param stack The ItemStack
     * @param world The world
     * @param pos The position in the world
     * @return Whether the ritual can be performed or not
     */
    public boolean canExecuteRitual(EntityPlayer player, ItemStack stack, World world, BlockPos pos) {
        return false;
    }

    /**
     * Executes the ritual. The parameters should be the same as in the last call of {@code canExecuteRitual}
     *
     * If you want to avoid spam, please check {@code world.isRemote} before sending Chat messages
     * as this method is executed on client AND server because otherwise removing Blocks (especially MultiBlockStructures) causes sync-issues.
     * @param player The player
     * @param stack The ItemStack
     * @param world The world
     * @param pos The position in the world
     */
    public abstract void executeRitual(EntityPlayer player, ItemStack stack, World world, BlockPos pos);

}