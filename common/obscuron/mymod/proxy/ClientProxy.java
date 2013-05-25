package obscuron.mymod.proxy;

import obscuron.mymod.lib.Textures;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenderers() {
//        MinecraftForgeClient.preloadTexture(Textures.ITEM_TEXTURE_SHEET);
//        MinecraftForgeClient.preloadTexture(Textures.BLOCK_TEXTURE_SHEET);
    }

}
