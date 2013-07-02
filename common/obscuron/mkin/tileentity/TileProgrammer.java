package obscuron.mkin.tileentity;

import obscuron.mkin.lib.ContainerInfo;

public class TileProgrammer extends KineticInventoryTile {

    public static final int SIZE = 9*6;

    public TileProgrammer() {
        super();
    }
    
    protected int invSize() {
        return SIZE;
    }

    @Override
    public String getInvName() {
        return ContainerInfo.PROGRAMMER_CONTAINER;
    }
    
    @Override
    public void updateEntity() {
        super.updateEntity();
    }
    
}
