package obscuron.mymod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
//import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkMod;
import obscuron.mymod.lib.Reference;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MyMod {
    
    @Instance(Reference.MOD_ID)
    public static MyMod instance;
    
//    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
//    public static CommonProxy proxy;

}
