package theultimatehose.elementalspirits.item.base;

import net.minecraft.item.Item;

public class ItemESBase extends Item {

    private final String name;

    public ItemESBase(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
