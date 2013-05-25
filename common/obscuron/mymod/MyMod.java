package obscuron.mymod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
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
import cpw.mods.fml.common.registry.GameRegistry;
import obscuron.mymod.lib.Reference;
import obscuron.mymod.proxy.CommonProxy;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MyMod {
    
    @Instance(Reference.MOD_ID)
    public static MyMod instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        
    }
    
    @Init
    public void load(FMLInitializationEvent event) {
        proxy.registerRenderers();
        
        ItemStack dirtStack = new ItemStack(Block.dirt);
        ItemStack diamondStack = new ItemStack(Item.diamond);
        
        GameRegistry.addShapelessRecipe(diamondStack, dirtStack, dirtStack, dirtStack);
        
        GameRegistry.addRecipe(diamondStack, "1 1", "   ", "1 1", '1', dirtStack);
        
        GameRegistry.addSmelting(Block.dirt.blockID, diamondStack, 10.0f);
        
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
        
    }

}
