package theultimatehose.elementalspirits;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import theultimatehose.elementalspirits.block.BlockEarthInfused;
import theultimatehose.elementalspirits.entity.EntityElementalBase;
import theultimatehose.elementalspirits.entity.elemental_earth.EntityElementalEarth;
import theultimatehose.elementalspirits.gui.GuiHandler;
import theultimatehose.elementalspirits.infusion.InfusionRitual;
import theultimatehose.elementalspirits.input.KeyBindManager;
import theultimatehose.elementalspirits.items.ItemAncientScroll;
import theultimatehose.elementalspirits.items.ItemEarthRod;
import theultimatehose.elementalspirits.network.Syncer;
import theultimatehose.elementalspirits.proxy.GeneralProxy;
import theultimatehose.elementalspirits.scroll.structure.Book;
import theultimatehose.elementalspirits.util.Util;

@Mod(modid = Util.MOD_ID, version = Util.VERSION)
public class ElementalSpirits {

    @Mod.Instance(Util.MOD_ID)
    public static ElementalSpirits instance;

    public ItemAncientScroll itemAncientScroll;
    public ItemEarthRod itemEarthRod;
    public BlockEarthInfused blockEarthInfused;
    public ShapedOreRecipe itemEarthRodRecipe;

    @SidedProxy(clientSide = "theultimatehose.elementalspirits.proxy.ClientProxy", serverSide = "theultimatehose.elementalspirits.proxy.ServerProxy")
    public static GeneralProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        itemAncientScroll = (ItemAncientScroll) new ItemAncientScroll().setUnlocalizedName(Util.MOD_ID_LOWER + '.' + Names.ITEM_SCROLL).setRegistryName(Names.ITEM_SCROLL);
        itemEarthRod = (ItemEarthRod) new ItemEarthRod().setUnlocalizedName(Util.MOD_ID_LOWER + "." + Names.ITEM_EARTH_ROD).setRegistryName(Names.ITEM_EARTH_ROD);

        blockEarthInfused = (BlockEarthInfused) new BlockEarthInfused().setUnlocalizedName(Util.MOD_ID_LOWER + '.' + Names.BLOCK_EARTH_INFUSED).setRegistryName(Names.BLOCK_EARTH_INFUSED);

        GameRegistry.registerItem(itemAncientScroll, Names.ITEM_SCROLL);
        GameRegistry.registerItem(itemEarthRod, Names.ITEM_EARTH_ROD);
        GameRegistry.registerBlock(blockEarthInfused);

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        GameRegistry.addRecipe(itemEarthRodRecipe = new ShapedOreRecipe(itemEarthRod, "VVD", "IIE", "RVV", 'V', Blocks.vine, 'D', "gemDiamond", 'I', "ingotIron", 'E', "gemEmerald", 'R', "dustRedstone"));

        GuiHandler.init();
        Syncer.init();
        KeyBindManager.INSTANCE.init();
        Book.init();
        InfusionRitual.init();
        proxy.init(event);

        EntityRegistry.registerModEntity(EntityElementalBase.class, "BaseElemental", 65536, ElementalSpirits.instance, 65536, 1, true);
        EntityRegistry.registerModEntity(EntityElementalEarth.class, "EarthElemental", 65537, ElementalSpirits.instance, 65536, 1, true, 0x009900, 0xAAAAAA);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        EntityRegistry.addSpawn(EntityElementalEarth.class, 1, 0, 1, EnumCreatureType.AMBIENT, BiomeGenBase.plains);
    }
}
