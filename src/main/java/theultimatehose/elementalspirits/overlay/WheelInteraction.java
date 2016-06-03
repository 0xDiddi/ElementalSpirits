package theultimatehose.elementalspirits.overlay;

import theultimatehose.elementalspirits.util.ModUtil;

import java.util.HashMap;

public class WheelInteraction {

    public interface IWheelInteractionProvider {
        HashMap<Overlay.Position, Action> getActions();
    }

    public static abstract class Action {

        String unlocalizedName;

        public Action(String unlocalizedName) {
            this.unlocalizedName = "wheel." + ModUtil.MOD_ID_LOWER + "." + unlocalizedName + ".name";
        }

        public abstract void perform();
    }

}
