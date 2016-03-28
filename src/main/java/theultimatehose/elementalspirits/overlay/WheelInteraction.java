package theultimatehose.elementalspirits.overlay;

import java.util.HashMap;

public class WheelInteraction {

    public interface IWheelInteractionProvider {
        HashMap<Overlay.Position, Action> getActions();
    }

    public interface Action {
        void perform();
    }

}
