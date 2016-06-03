package theultimatehose.elementalspirits.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import theultimatehose.elementalspirits.entity.elemental_earth.EntityElementalEarth;
import theultimatehose.elementalspirits.entity.elemental_earth.RenderElementalEarth;
import theultimatehose.elementalspirits.input.KeyBindManager;
import theultimatehose.elementalspirits.overlay.OverlayHandler;
import theultimatehose.elementalspirits.particle.ParticleSystemManager;
import theultimatehose.elementalspirits.util.Util;

import java.util.HashMap;
import java.util.Map;

public class ClientProxy implements GeneralProxy {

    private static Map<ItemStack, ResourceLocation> renderersForRegistering = new HashMap<>();

    @Override
    public void init(FMLInitializationEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(EntityElementalEarth.class, new RenderElementalEarth());

        for (Map.Entry<ItemStack, ResourceLocation> entry : renderersForRegistering.entrySet()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(entry.getKey().getItem(), entry.getKey().getItemDamage(), new ModelResourceLocation(entry.getValue(), "inventory"));
            //ModelLoader.setCustomModelResourceLocation(entry.getKey().getItem(), entry.getKey().getItemDamage(), new ModelResourceLocation(entry.getValue(), "inventory"));
        }

        FMLCommonHandler.instance().bus().register(new OverlayHandler());
        FMLCommonHandler.instance().bus().register(KeyBindManager.INSTANCE);
        FMLCommonHandler.instance().bus().register(ParticleSystemManager.INSTANCE);

    }

    @Override
    public void addSimpleRenderer(ItemStack stack, ResourceLocation resLoc) {
        renderersForRegistering.put(stack, resLoc);
    }
}
