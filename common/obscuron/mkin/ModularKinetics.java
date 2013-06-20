package obscuron.mkin;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import obscuron.mkin.core.BlocksHandler;
import obscuron.mkin.core.ConfigurationHandler;
import obscuron.mkin.core.CreativeTabKinetics;
import obscuron.mkin.core.ItemsHandler;
import obscuron.mkin.core.LanguageHandler;
import obscuron.mkin.lib.Reference;
import obscuron.mkin.network.PacketHandler;
import obscuron.mkin.proxy.CommonProxy;
import obscuron.mkin.tileentity.TileInterface;
import obscuron.mkin.tileentity.TileProgrammer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class ModularKinetics {
    
    @Instance(Reference.MOD_ID)
    public static ModularKinetics instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;
    
    public static CreativeTabs tabKinetics = new CreativeTabKinetics(CreativeTabs.getNextID(), Reference.MOD_ID);
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        // Loads the configuration file
        File cfgfile = new File(event.getModConfigurationDirectory().getAbsolutePath() + Reference.CONFIG_FILE_NAME);
        ConfigurationHandler.init(cfgfile);
        
        // Initializes blocks into the game registry
        BlocksHandler.init();
        
        // Initializes items into the game registry
        ItemsHandler.init();
        
        // Initializes the languages (namely en_US)
        LanguageHandler.init();
    }
    
    @Init
    public void load(FMLInitializationEvent event) {        
        // Registers the proxy to handle Guis
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        
        GameRegistry.registerTileEntity(TileProgrammer.class, "tileProgrammer");
        GameRegistry.registerTileEntity(TileInterface.class, "tileInterface");
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
        
    }

}
