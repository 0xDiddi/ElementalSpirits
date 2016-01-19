package theultimatehose.elementalspirits;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import theultimatehose.elementalspirits.items.ItemAncientScroll;
import theultimatehose.elementalspirits.proxy.GeneralProxy;
import theultimatehose.elementalspirits.util.Util;

@Mod(modid = Util.MOD_ID, version = Util.VERSION)
public class ElementalSpirits {

    ItemAncientScroll itemAncientScroll;

    @SidedProxy(clientSide = "theultimatehose.elementalspirits.proxy.ClientProxy", serverSide = "theultimatehose.elementalspirits.proxy.ServerProxy")
    public static GeneralProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        itemAncientScroll = (ItemAncientScroll) new ItemAncientScroll().setUnlocalizedName(Util.MOD_ID_LOWER + '.' + Names.ITEM_SCROLL).setRegistryName(Names.ITEM_SCROLL);

        GameRegistry.registerItem(itemAncientScroll, Names.ITEM_SCROLL);

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
