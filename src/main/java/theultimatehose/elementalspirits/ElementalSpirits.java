package theultimatehose.elementalspirits;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import theultimatehose.elementalspirits.block.BlockManager;
import theultimatehose.elementalspirits.entity.EntityElementalBase;
import theultimatehose.elementalspirits.entity.elemental_earth.EntityElementalEarth;
import theultimatehose.elementalspirits.gui.GuiHandler;
import theultimatehose.elementalspirits.infusion.InfusionRitual;
import theultimatehose.elementalspirits.input.KeyBindManager;
import theultimatehose.elementalspirits.item.ItemManager;
import theultimatehose.elementalspirits.network.Syncer;
import theultimatehose.elementalspirits.proxy.GeneralProxy;
import theultimatehose.elementalspirits.scroll.structure.Book;
import theultimatehose.elementalspirits.util.DelayUtil;
import theultimatehose.elementalspirits.util.Util;

@Mod(modid = Util.MOD_ID, version = Util.VERSION)
public class ElementalSpirits {

    @Mod.Instance(Util.MOD_ID)
    public static ElementalSpirits INSTANCE;

    public ShapedOreRecipe itemEarthRodRecipe;

    @SidedProxy(clientSide = "theultimatehose.elementalspirits.proxy.ClientProxy", serverSide = "theultimatehose.elementalspirits.proxy.ServerProxy")
    public static GeneralProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        BlockManager.init();
        ItemManager.init();

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        GameRegistry.addRecipe(itemEarthRodRecipe = new ShapedOreRecipe(ItemManager.itemEarthRod, "VVD", "IIE", "RVV", 'V', Blocks.VINE, 'D', "gemDiamond", 'I', "ingotIron", 'E', "gemEmerald", 'R', "dustRedstone"));

        GuiHandler.init();
        Syncer.init();
        KeyBindManager.INSTANCE.init();
        Book.init();
        InfusionRitual.init();
        proxy.init(event);

        FMLCommonHandler.instance().bus().register(DelayUtil.INSTANCE);

        EntityRegistry.registerModEntity(EntityElementalBase.class, "BaseElemental", 65536, ElementalSpirits.INSTANCE, 65536, 1, true);
        EntityRegistry.registerModEntity(EntityElementalEarth.class, "EarthElemental", 65537, ElementalSpirits.INSTANCE, 65536, 1, true, 0x009900, 0xAAAAAA);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //TODO: Fix spawn biomes
        //EntityRegistry.addSpawn(EntityElementalEarth.class, 1, 0, 1, EnumCreatureType.AMBIENT, BiomeGenBase.plains);
    }
}
