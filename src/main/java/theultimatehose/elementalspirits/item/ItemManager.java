package theultimatehose.elementalspirits.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import theultimatehose.elementalspirits.ElementalSpirits;
import theultimatehose.elementalspirits.item.base.ItemESBase;
import theultimatehose.elementalspirits.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemAncientScroll itemAncientScroll;
    public static ItemEarthRod itemEarthRod;

    private static List<ItemESBase> REGISTERED_ITEMS = new ArrayList<>();

    public static void init() {
        itemAncientScroll = new ItemAncientScroll();    REGISTERED_ITEMS.add(itemAncientScroll);
        itemEarthRod = new ItemEarthRod();              REGISTERED_ITEMS.add(itemEarthRod);

        for (ItemESBase item : REGISTERED_ITEMS) {
            registerItem(item);
            registerRendering(item);
        }
    }

    private static void registerItem(ItemESBase item) {
        item.setUnlocalizedName(Util.MOD_ID_LOWER + '.' + item.getName());
        item.setRegistryName(Util.MOD_ID_LOWER, item.getName());
        GameRegistry.register(item);
    }

    private static void registerRendering(ItemESBase item) {
        ElementalSpirits.proxy.addSimpleRenderer(new ItemStack(item), new ResourceLocation(Util.MOD_ID_LOWER, item.getName()));
    }

}
