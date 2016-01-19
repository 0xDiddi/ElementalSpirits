package theultimatehose.elementalspirits.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.scroll.GuiScroll;

public class GuiHandler implements IGuiHandler {

    public static final int SCROLL_ID = 0;

    public static void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(ElementalSpirits.instance, new GuiHandler());
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case SCROLL_ID:
                return new GuiScroll();
            default:
                return null;
        }
    }
}
