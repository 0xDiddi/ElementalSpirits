package theultimatehose.elementalspirits.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import theultimatehose.elementalspirits.entity.EntityElementalEarth;
import theultimatehose.elementalspirits.entity.RenderElementalEarth;

import java.util.HashMap;
import java.util.Map;

public class ClientProxy implements GeneralProxy {

    private static Map<ItemStack, ResourceLocation> itemRenderersForRegistering = new HashMap<ItemStack, ResourceLocation>();

    @Override
    public void init(FMLInitializationEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(EntityElementalEarth.class, new RenderElementalEarth());

        for (Map.Entry<ItemStack, ResourceLocation> entry : itemRenderersForRegistering.entrySet()) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(entry.getKey().getItem(), entry.getKey().getItemDamage(), new ModelResourceLocation(entry.getValue(), "inventory"));
        }

    }

    @Override
    public void addSimpleRenderer(ItemStack stack, ResourceLocation resLoc) {
        itemRenderersForRegistering.put(stack, resLoc);
    }
}
