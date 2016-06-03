package theultimatehose.elementalspirits.input;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import theultimatehose.elementalspirits.Names;
import theultimatehose.elementalspirits.util.ModUtil;

import java.util.HashMap;

public class KeyBindManager {

    public static final KeyBindManager INSTANCE = new KeyBindManager();

    public HashMap<String, KeyBind> bindings;
    private final String DEFAULT_CATEGORY = "keys." + ModUtil.MOD_ID_LOWER + ".category.name";

    public void init() {

        this.bindings = new HashMap<String, KeyBind>();

        this.bindings.put(Names.KEYBIND_WHEEL_TL, new KeyBind(parseKeyDescription(Names.KEYBIND_WHEEL_TL), Keyboard.KEY_NUMPAD7, DEFAULT_CATEGORY, 10));
        this.bindings.put(Names.KEYBIND_WHEEL_TC, new KeyBind(parseKeyDescription(Names.KEYBIND_WHEEL_TC), Keyboard.KEY_NUMPAD8, DEFAULT_CATEGORY, 10));
        this.bindings.put(Names.KEYBIND_WHEEL_TR, new KeyBind(parseKeyDescription(Names.KEYBIND_WHEEL_TR), Keyboard.KEY_NUMPAD9, DEFAULT_CATEGORY, 10));
        this.bindings.put(Names.KEYBIND_WHEEL_CL, new KeyBind(parseKeyDescription(Names.KEYBIND_WHEEL_CL), Keyboard.KEY_NUMPAD4, DEFAULT_CATEGORY, 10));
        this.bindings.put(Names.KEYBIND_WHEEL_CR, new KeyBind(parseKeyDescription(Names.KEYBIND_WHEEL_CR), Keyboard.KEY_NUMPAD5, DEFAULT_CATEGORY, 10));
        this.bindings.put(Names.KEYBIND_WHEEL_BL, new KeyBind(parseKeyDescription(Names.KEYBIND_WHEEL_BL), Keyboard.KEY_NUMPAD1, DEFAULT_CATEGORY, 10));
        this.bindings.put(Names.KEYBIND_WHEEL_BC, new KeyBind(parseKeyDescription(Names.KEYBIND_WHEEL_BC), Keyboard.KEY_NUMPAD2, DEFAULT_CATEGORY, 10));
        this.bindings.put(Names.KEYBIND_WHEEL_BR, new KeyBind(parseKeyDescription(Names.KEYBIND_WHEEL_BR), Keyboard.KEY_NUMPAD3, DEFAULT_CATEGORY, 10));

        for (KeyBind bind : this.bindings.values()) {
            ClientRegistry.registerKeyBinding(bind.binding);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void tick(TickEvent event) {
        for (KeyBind bind : this.bindings.values()) {
            bind.update();
        }
    }

    public static String parseKeyDescription(String s) {

        return I18n.translateToLocal("keys." + ModUtil.MOD_ID_LOWER + "." + s + ".description");
    }

    public static class KeyBind {

        /**
         *
         * @param description The description for the Keybind
         * @param keycode The default key
         * @param category The category
         * @param timeout The time in ticks (after key release) in which {@code pressed} is still true
         */
        public KeyBind(String description, int keycode, String category, int timeout) {
            this.binding = new KeyBinding(description, keycode, category);
            this.timeout = timeout;
            this.pressed = false;
            this.cont = false;
            this.ticks = 0;
        }

        public void update() {
            if (this.binding.isKeyDown()) {
                this.pressed = true;
                this.cont = true;
            } else if (this.ticks < this.timeout && cont) {
                ticks ++;
            } else {
                this.reset();
            }
        }

        public void reset() {
            this.pressed = false;
            this.cont = false;
            this.ticks = 0;
        }

        public KeyBinding binding;
        public boolean pressed;
        private boolean cont;
        private int timeout;
        private int ticks;
    }

}
