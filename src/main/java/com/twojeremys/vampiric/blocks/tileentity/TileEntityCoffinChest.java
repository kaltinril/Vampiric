package com.twojeremys.vampiric.blocks.tileentity;

import com.twojeremys.vampiric.blocks.container.ContainerCoffinChest;
import com.twojeremys.vampiric.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityCoffinChest extends TileEntityLockableLoot implements ITickable {
    private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
    public int numPlayersUsing;
    public int ticksSinceSync;
    public float lidAngle;
    public float prevLidAngle;

    // Variables used to keep track of the two blocks and associate them together
    public TileEntityCoffinChest adjacentPart;
    public boolean isFirst = true;
    public int adjacentX = 0;
    public int adjacentY = 0;
    public int adjacentZ = 0;

    public TileEntityCoffinChest getAdjacentPart(){
        return adjacentPart;
    }

    public void setAdjacentPart(TileEntityCoffinChest tileEntityCoffinChest){
        this.adjacentPart = tileEntityCoffinChest;
        this.markDirty();
    }

    @Override
    public int getSizeInventory(){
        return 4;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isEmpty() {
        if (this.isFirst) {
            for (ItemStack stack : this.chestContents) {
                if (!stack.isEmpty()) return false;
            }
        }else
            return this.getAdjacentPart().isEmpty();

        return true;
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.coffin_chest";
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        // DEBUG prints TODO: Remove
        System.out.println("readFromNBT");
        if (world != null) System.out.println("World is remove? " + world.isRemote);
        System.out.println(compound);

        super.readFromNBT(compound);
        this.chestContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(compound)) ItemStackHelper.loadAllItems(compound, chestContents);

        if (compound.hasKey("IsFirst"))
            this.isFirst = compound.getBoolean("IsFirst");

        if (compound.hasKey("AdjacentX")) {
            BlockPos pos = new BlockPos(
                    compound.getInteger("AdjacentX"),
                    compound.getInteger("AdjacentY"),
                    compound.getInteger("AdjacentZ"));
            this.adjacentX = pos.getX();
            this.adjacentY = pos.getY();
            this.adjacentZ = pos.getZ();

            // DEBUG prints TODO: Remove
            System.out.println("Key found!");
        }

        // DEBUG prints TODO: Remove
        System.out.println(adjacentX + " : " + adjacentY + " : " + adjacentZ);
        if (compound.hasKey("CustomName", 8)) this.customName = compound.getString("CustomName");

        this.markDirty(); // Make sure this gets updated
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        System.out.println("writeToNBT");
        System.out.println("World is remove? " + world.isRemote);
        System.out.println(compound);

        if(!this.checkLootAndWrite(compound)) ItemStackHelper.saveAllItems(compound, chestContents);

        compound.setBoolean("IsFirst", (boolean)this.isFirst);

        if (this.getAdjacentPart() != null) {
            compound.setInteger("AdjacentX", (Integer) this.getAdjacentPart().getPos().getX());
            compound.setInteger("AdjacentY", (Integer) this.getAdjacentPart().getPos().getY());
            compound.setInteger("AdjacentZ", (Integer) this.getAdjacentPart().getPos().getZ());

            System.out.println(this.getAdjacentPart().getPos().getX() + " : " + this.getAdjacentPart().getPos().getY() + " : " + this.getAdjacentPart().getPos().getZ());
        }

        if(compound.hasKey("CustomName", 8)) compound.setString("CustomName", this.customName);

        return super.writeToNBT(compound);
    }


    /**
     * These next three methods (getUpdatePacket, getUpdateTag, and onDataPacket)
     *  are required to syncronize the NBT data between the server and the client
     */
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), getBlockMetadata(), writeToNBT(new NBTTagCompound()));
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        super.getUpdateTag();

        return writeToNBT(new NBTTagCompound());
    }


    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        if (this.isFirst)
            return new ContainerCoffinChest(playerInventory, this, playerIn);
        else
            return new ContainerCoffinChest(playerInventory, this.getAdjacentPart(), playerIn);
    }

    @Override
    public String getGuiID()
    {
        return Reference.MOD_ID + ":coffin_chest.json";
    }

    @Override
    protected NonNullList<ItemStack> getItems()
    {
        if (this.isFirst)
            return this.chestContents;
        else
            return this.getAdjacentPart().chestContents;
    }

    /**
     * findAssociatedTileEntity
     *  This uses the saved XYZ values for the chest/block that is "Adjacent" (Next to this one)
     *  It tries to find the TileEntity at that location to associate it
     */
    private void findAssociatedTileEntity(){
        if (getAdjacentPart() == null && adjacentX != 0 && adjacentY != 0 && adjacentZ != 0){
            World world = this.getWorld();
            if (world != null){
                BlockPos pos = new BlockPos(adjacentX, adjacentY, adjacentZ);
                TileEntity otherCoffinTile = this.getWorld().getTileEntity(pos);
                if (otherCoffinTile != null) {
                    this.setAdjacentPart((TileEntityCoffinChest)otherCoffinTile);
                    this.markDirty();
                }
            }
        }
    }

    @Override
    public void update()
    {
        findAssociatedTileEntity();

        // Only run the other objects code for animating the lid and incrementing users of the chest
        if (!this.isFirst){
            return;
        }

        int posX = this.pos.getX();
        int posY = this.pos.getY();
        int posZ = this.pos.getZ();

        if (!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + posX + posY + posZ) % 200 == 0)
        {
            this.numPlayersUsing = 0;
            float f = 5.0F;

            for (EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((double)((float)posX - f), (double)((float)posY - f), (double)((float)posZ - f), (double)((float)(posX + 1) + f), (double)((float)(posY + 1) + f), (double)((float)(posZ + 1) + f))))
            {
                if (entityplayer.openContainer instanceof ContainerCoffinChest)
                {
                    if (((ContainerCoffinChest)entityplayer.openContainer).getChestInventory() == this)
                    {
                        ++this.numPlayersUsing;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        float f1 = 0.1F;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F)
        {
            double d1 = (double)posX + 0.5D;
            double d2 = (double)posZ + 0.5D;
            this.world.playSound((EntityPlayer)null, d1, (double)posY + 0.5D, d2, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f2 = this.lidAngle;

            if (this.numPlayersUsing > 0)
            {
                this.lidAngle += 0.1F;
            }
            else
            {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float f3 = 0.5F;

            if (this.lidAngle < 0.5F && f2 >= 0.5F)
            {
                double d3 = (double)posX + 0.5D;
                double d0 = (double)posZ + 0.5D;
                this.world.playSound((EntityPlayer)null, d3, (double)posY + 0.5D, d0, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }

    @Override
    public void openInventory(EntityPlayer player)
    {
        System.out.println(this.isFirst);
        System.out.println(this.getAdjacentPart());
        if (this.isFirst) {
            ++this.numPlayersUsing;
            this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
        }else
            if (this.getAdjacentPart() != null) this.getAdjacentPart().openInventory(player);
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
        if (this.isFirst) {
            --this.numPlayersUsing;
            this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
        }else
            if (this.getAdjacentPart() != null) this.getAdjacentPart().closeInventory(player);
    }

    // This sets the area that if this area is on the screen, we will still render the object.
    // Using the same values that CHEST is using.
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new net.minecraft.util.math.AxisAlignedBB(pos.add(-1, 0, -1), pos.add(2, 2, 2));
    }
}
