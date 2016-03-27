package theultimatehose.elementalspirits;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import theultimatehose.elementalspirits.entity.EntityElementalBase;
import theultimatehose.elementalspirits.entity.EntityElementalEarth;
import theultimatehose.elementalspirits.gui.GuiHandler;
import theultimatehose.elementalspirits.input.KeyBindManager;
import theultimatehose.elementalspirits.items.ItemAncientScroll;
import theultimatehose.elementalspirits.network.Syncer;
import theultimatehose.elementalspirits.proxy.GeneralProxy;
import theultimatehose.elementalspirits.scroll.Structure;
import theultimatehose.elementalspirits.util.Util;

@Mod(modid = Util.MOD_ID, version = Util.VERSION)
public class ElementalSpirits {

    @Mod.Instance(Util.MOD_ID)
    public static ElementalSpirits instance;

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

        GuiHandler.init();
        proxy.init(event);
        Syncer.init();
        KeyBindManager.INSTANCE.init();
        Structure.Book.init();

        EntityRegistry.registerModEntity(EntityElementalBase.class, "BaseElemental", 65536, ElementalSpirits.instance, 65536, 1, true);
        EntityRegistry.registerModEntity(EntityElementalEarth.class, "EarthElemental", 65537, ElementalSpirits.instance, 65536, 1, true, 0x009900, 0xAAAAAA);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        EntityRegistry.addSpawn(EntityElementalEarth.class, 10, 1, 5, EnumCreatureType.AMBIENT, BiomeGenBase.plains);
    }
}
