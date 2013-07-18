package obscuron.mkin.util;

import java.util.Vector;

public class TagInfoHandler {
    
    public NBTWrapper tag;
    public int count;
    public Vector<Integer> slotNum;
    
    public TagInfoHandler(NBTWrapper tag) {
        this.tag = tag;
        this.count = 0;
        this.slotNum = new Vector<Integer>();
    }

}
