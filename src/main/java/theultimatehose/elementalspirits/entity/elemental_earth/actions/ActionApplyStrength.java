package theultimatehose.elementalspirits.entity.elemental_earth.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionAttackDamage;
import net.minecraft.potion.PotionEffect;
import theultimatehose.elementalspirits.overlay.WheelInteraction;

public class ActionApplyStrength extends WheelInteraction.Action {

    public EntityPlayer player;

    public ActionApplyStrength(EntityPlayer player) {
        super("apply_strength");
        this.player = player;
    }

    @Override
    public void perform() {
        this.player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 60, 1, true, false));
    }

}
