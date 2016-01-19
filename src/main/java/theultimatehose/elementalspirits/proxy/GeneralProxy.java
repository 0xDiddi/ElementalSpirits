package theultimatehose.elementalspirits.proxy;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public interface GeneralProxy {

    @Mod.EventHandler
    void init(FMLInitializationEvent event);

    void addSimpleRenderer(ItemStack stack, ResourceLocation resLoc);

}
