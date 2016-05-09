package theultimatehose.elementalspirits.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import theultimatehose.elementalspirits.entity.elemental_earth.EntityElementalEarth;
import theultimatehose.elementalspirits.entity.elemental_earth.RenderElementalEarth;
import theultimatehose.elementalspirits.input.KeyBindManager;
import theultimatehose.elementalspirits.overlay.OverlayHandler;
import theultimatehose.elementalspirits.particle.ParticleSystemManager;

import java.util.HashMap;
import java.util.Map;

public class ClientProxy implements GeneralProxy {

    private static Map<ItemStack, ResourceLocation> itemRenderersForRegistering = new HashMap<>();

    @Override
    public void init(FMLInitializationEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(EntityElementalEarth.class, new RenderElementalEarth());

        for (Map.Entry<ItemStack, ResourceLocation> entry : itemRenderersForRegistering.entrySet()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(entry.getKey().getItem(), 0, new ModelResourceLocation(entry.getValue(), "inventory"));
        }

        FMLCommonHandler.instance().bus().register(new OverlayHandler());
        FMLCommonHandler.instance().bus().register(KeyBindManager.INSTANCE);
        FMLCommonHandler.instance().bus().register(ParticleSystemManager.INSTANCE);

    }

    @Override
    public void addSimpleRenderer(ItemStack stack, ResourceLocation resLoc) {
        itemRenderersForRegistering.put(stack, resLoc);
    }
}
