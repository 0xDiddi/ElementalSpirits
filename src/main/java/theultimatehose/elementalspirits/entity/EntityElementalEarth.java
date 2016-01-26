package theultimatehose.elementalspirits.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import theultimatehose.elementalspirits.entity.ai.ElementalAIFollowMaster;

public class EntityElementalEarth extends  EntityElementalBase {

    public EntityElementalEarth(World worldIn) {
        super(worldIn);
        this.tasks.addTask(1, new EntityAIWander(this, 1));
        this.tasks.addTask(2, new ElementalAIFollowMaster(this));
        this.tasks.addTask(5, new EntityAITempt(this, 1, Items.emerald, false));
        this.tasks.addTask(10, new EntityAILookIdle(this));

    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
    }
}
