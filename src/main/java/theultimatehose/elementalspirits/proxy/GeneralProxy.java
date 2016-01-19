package theultimatehose.elementalspirits.proxy;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public interface GeneralProxy {

    void init(FMLInitializationEvent event);

    void addSimpleRenderer(ItemStack stack, ResourceLocation resLoc);

}
