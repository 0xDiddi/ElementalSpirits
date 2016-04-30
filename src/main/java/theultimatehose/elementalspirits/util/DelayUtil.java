package theultimatehose.elementalspirits.util;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;

public class DelayUtil {

    public static DelayUtil INSTANCE = new DelayUtil();

    private ArrayList<ActionSet> actions;

    private DelayUtil() {
        this.actions = new ArrayList<>();
    }

    @SubscribeEvent
    public void tick(TickEvent event) {
        for (int i = 0; i < actions.size(); i++) {
            DelayedAction action = actions.get(i).action;
            actions.get(i).delay--;
            if (actions.get(i).delay <= 0) {
                action.execute();
                actions.remove(i);
            }
        }
    }

    public void postDelayed(int delay, DelayedAction action) {
        this.actions.add(new ActionSet(delay, action));
    }

    public abstract static class DelayedAction {
        public abstract void execute();
    }

    class ActionSet {
        int delay;
        DelayedAction action;

        public ActionSet(int delay, DelayedAction action) {
            this.delay = delay;
            this.action = action;
        }
    }

}
