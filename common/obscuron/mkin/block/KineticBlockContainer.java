package obscuron.mkin.block;

import java.util.Random;

import obscuron.mkin.ModularKinetics;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class KineticBlockContainer extends BlockContainer {
    
    // Random number generator for dropping inventory contents    
    private Random rand = new Random();
    
    protected abstract String blockName();
    protected abstract int guiId();
    protected abstract boolean checkClass(TileEntity tile);

    public KineticBlockContainer(int blockId) {
        super(blockId, Material.iron);
        this.setHardness(2.0F);
        this.setCreativeTab(ModularKinetics.tabKinetics);
        this.setUnlocalizedName(blockName());
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i1, float f1, float f2, float f3) {
        if (player.isSneaking()) {
            return false;
        }
        
        else if (!world.isRemote) {
            TileEntity tile = world.getBlockTileEntity(x, y, z);
            if (tile != null && checkClass(tile)) {
                player.openGui(ModularKinetics.instance, guiId(), world, x, y, z);
            }
        }
        
        return true;
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile != null && tile instanceof IInventory) {
            dropInventory((IInventory) tile, world, x, y, z);
        }
        super.breakBlock(world, x, y, z, id, meta);
    }
    
    private void dropInventory(IInventory inventory, World world, int x, int y, int z) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            
            if (itemStack != null) {
                float dx = rand.nextFloat() * 0.8F + 0.1F;
                float dy = rand.nextFloat() * 0.8F + 0.1F;
                float dz = rand.nextFloat() * 0.8F + 0.1F;
                
                while (itemStack.stackSize > 0) {
                    int amount = rand.nextInt(21) + 10;
                    if (amount > itemStack.stackSize) {
                        amount = itemStack.stackSize;
                    }
                    
                    EntityItem entityItem = new EntityItem(world, x + dx, y + dy, z + dz, new ItemStack(itemStack.itemID, amount, itemStack.getItemDamage()));

                    if (itemStack.hasTagCompound()) {
                        entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                    }
                    
                    float f = 0.05F;
                    entityItem.motionX = rand.nextGaussian() * f;
                    entityItem.motionY = rand.nextGaussian() * f + 0.2F;
                    entityItem.motionZ = rand.nextGaussian() * f;
                    
                    world.spawnEntityInWorld(entityItem);
                    
                    itemStack.stackSize -= amount;
                }
            }
        }
    }
}
