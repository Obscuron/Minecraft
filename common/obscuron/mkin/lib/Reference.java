package obscuron.mkin.lib;

import java.io.File;

public class Reference {

    public static final String MOD_ID = "mKin";
    public static final String MOD_NAME = "Modular Kinetics";
    public static final String VERSION = "@VERSION@";
    public static final String SERVER_PROXY = "obscuron.mkin.proxy.CommonProxy";
    public static final String CLIENT_PROXY = "obscuron.mkin.proxy.ClientProxy";
    public static final String CONFIG_FILE_NAME = File.separator + MOD_NAME.replaceAll("\\s", "") + ".cfg";
    public static final String CHANNEL_NAME = MOD_ID;

}

