package obscuron.mkin.util;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class KeyUtil {
    
    public static boolean isShiftHeld() {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                return true;
            }
        }
        return false;
    }

}
