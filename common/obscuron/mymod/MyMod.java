package obscuron.mymod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import obscuron.mymod.block.MyBlock;
import obscuron.mymod.block.MyOre;
import obscuron.mymod.block.MySpecialBlock;
import obscuron.mymod.item.MyItem;
import obscuron.mymod.lib.Reference;
import obscuron.mymod.network.PacketHandler;
import obscuron.mymod.proxy.CommonProxy;
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
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class MyMod {
    
    @Instance(Reference.MOD_ID)
    public static MyMod instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;
    
    private static final Item myItem = new MyItem(4800);
    private static final Item myIngot = new MyItem(4801).setMaxStackSize(16).setUnlocalizedName("myIngot");
    
    private static final Block myDirt = new MyBlock(500, Material.rock).setHardness(1.0F).setStepSound(Block.soundGravelFootstep).setUnlocalizedName("myDirt");
    private static final Block myOre = new MyOre(501, Material.iron);
    
    private static final Block mySpecialBlock = new MySpecialBlock(502);
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        
    }
    
    @Init
    public void load(FMLInitializationEvent event) {
        proxy.registerRenderers();
        
        ItemStack dirtStack = new ItemStack(Block.dirt);
        ItemStack myItemStack = new ItemStack(myItem);
        ItemStack myIngotStack = new ItemStack(myIngot);
        ItemStack myDirtStack = new ItemStack(myDirt);
        ItemStack myOreStack = new ItemStack(myOre);
        ItemStack mySpecialStack = new ItemStack(mySpecialBlock);
        
        GameRegistry.addShapelessRecipe(myItemStack, dirtStack, dirtStack, dirtStack);
        
        GameRegistry.addRecipe(myDirtStack, "1 1", "   ", "1 1", '1', dirtStack);
        GameRegistry.addRecipe(myOreStack, "1 1", " 1 ", "1 1", '1', dirtStack);
        GameRegistry.addRecipe(mySpecialStack, "111", "111", "111", '1', dirtStack);
        
        GameRegistry.addSmelting(myOre.blockID, myIngotStack, 10.0f);
        
        GameRegistry.registerBlock(myDirt, "myDirt");
        GameRegistry.registerBlock(myOre, "myOre");
        GameRegistry.registerBlock(mySpecialBlock, "mySpecialBlock");
        
        LanguageRegistry.addName(myDirt, "My Dirt");
        LanguageRegistry.addName(myOre, "My Ore");
        LanguageRegistry.addName(mySpecialBlock, "My Special Block");
        
        LanguageRegistry.addName(myItem, "My Item");
        LanguageRegistry.addName(myIngot, "My Ingot");
        
        MinecraftForge.setBlockHarvestLevel(myDirt, "shovel", 3);
        MinecraftForge.setBlockHarvestLevel(myOre, "pickaxe", 3);
        
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
        
    }

}
