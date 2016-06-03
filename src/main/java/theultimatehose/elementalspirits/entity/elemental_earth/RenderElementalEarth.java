package theultimatehose.elementalspirits.entity.elemental_earth;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import theultimatehose.elementalspirits.util.ModUtil;

public class RenderElementalEarth extends RenderLiving<EntityElementalEarth> {

    public RenderElementalEarth() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelElementalEarth(), 0.5f);
    }

    @Override
    public void doRender(EntityElementalEarth entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y - .85,z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityElementalEarth entity) {
        return new ResourceLocation(ModUtil.MOD_ID_LOWER, "textures/entity/EarthElemental.png");
    }

}
