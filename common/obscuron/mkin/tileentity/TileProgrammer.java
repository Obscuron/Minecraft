package obscuron.mkin.tileentity;

import obscuron.mkin.lib.ContainerInfo;

public class TileProgrammer extends KineticInventoryTile {

    public static final int SIZE = 2;

    public TileProgrammer() {
        super();
    }
    
    @Override
    protected int invSize() {
        return SIZE;
    }

    @Override
    public String getInvName() {
        return ContainerInfo.PROGRAMMER_CONTAINER;
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 1;
    }
    
}
