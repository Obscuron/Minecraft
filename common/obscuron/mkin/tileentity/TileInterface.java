package obscuron.mkin.tileentity;

import obscuron.mkin.lib.ContainerInfo;

public class TileInterface extends KineticInventoryTile {

    public static final int SIZE = 3*3;

    public TileInterface() {
        super();
    }
    
    protected int invSize() {
        return SIZE;
    }

    @Override
    public String getInvName() {
        return ContainerInfo.INTERFACE_CONTAINER;
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

}
