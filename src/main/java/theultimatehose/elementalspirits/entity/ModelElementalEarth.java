package theultimatehose.elementalspirits.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * ElementalEarth - TheUltimateHose
 * Created using Tabula 5.1.0
 */
public class ModelElementalEarth extends ModelBase {
    public ModelRenderer leg_f_l;
    public ModelRenderer leg_f_r;
    public ModelRenderer leg_b_l;
    public ModelRenderer leg_b_r;
    public ModelRenderer body;
    public ModelRenderer head;

    public ModelElementalEarth() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leg_f_l = new ModelRenderer(this, 0, 0);
        this.leg_f_l.setRotationPoint(3.5F, 0.0F, -5.5F);
        this.leg_f_l.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, 0.0F);
        this.head = new ModelRenderer(this, 38, 0);
        this.head.setRotationPoint(-0.5F, -2.5F, -8.5F);
        this.head.addBox(-2.5F, -4.5F, -5.0F, 5, 5, 6, 0.0F);
        this.leg_b_r = new ModelRenderer(this, 0, 0);
        this.leg_b_r.setRotationPoint(-4.5F, 0.0F, 7.5F);
        this.leg_b_r.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, 0.0F);
        this.leg_b_l = new ModelRenderer(this, 0, 0);
        this.leg_b_l.setRotationPoint(3.5F, 0.0F, 7.5F);
        this.leg_b_l.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(-5.5F, -3.9F, -9.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 10, 5, 19, 0.0F);
        this.leg_f_r = new ModelRenderer(this, 0, 0);
        this.leg_f_r.setRotationPoint(-4.5F, 0.0F, -5.5F);
        this.leg_f_r.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.rotateAngleX = f4 / (180F / (float)Math.PI);
        this.head.rotateAngleY = f3 / (180F / (float)Math.PI);
        this.leg_b_l.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leg_b_r.rotateAngleX = -(MathHelper.cos(f * 0.6662F) * 1.4F * f1);
        this.leg_f_l.rotateAngleX = -(MathHelper.cos(f * 0.6662F) * 1.4F * f1);
        this.leg_f_r.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;

        this.body.render(f5);
        this.head.render(f5);
        this.leg_f_l.render(f5);
        this.leg_f_r.render(f5);
        this.leg_b_l.render(f5);
        this.leg_b_r.render(f5);
    }

}
