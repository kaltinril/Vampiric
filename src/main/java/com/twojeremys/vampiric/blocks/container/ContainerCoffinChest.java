package com.twojeremys.vampiric.blocks.container;

import com.twojeremys.vampiric.blocks.tileentity.TileEntityCoffinChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCoffinChest extends Container {
    private final int numRows;
    private final TileEntityCoffinChest chestInventory;

    public ContainerCoffinChest(InventoryPlayer playerInv, TileEntityCoffinChest chestInventory, EntityPlayer player){
        this.chestInventory = chestInventory;
        this.numRows = 1; // chestInventory.getSizeInventory() / 9;
        chestInventory.openInventory(player);

        /*
        for(int row = 0; row < this.numRows; ++row){
            for(int col = 0; col < 9; ++col){
                this.addSlotToContainer(new Slot(chestInventory, col + row * 9, 8 + col * 18, 18 + row * 18));
            }
        }
        */
        int firstSlotX = 39;
        int firstSlotY = 47;
        for(int i = 0; i < this.chestInventory.getSizeInventory(); i++){
            this.addSlotToContainer(new Slot(chestInventory, i, firstSlotX + i * 28, firstSlotY));
        }

        // Sections below are to display the normal player inventory
        // inventory slots
        int inventorySlotX = 8;
        int inventorySlotY = 104;
        for(int y=0;y < 3; y++){
            for(int x = 0; x < 9; x++){
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, inventorySlotX + x * 18, inventorySlotY + y * 18));
            }
        }

        // hotbar
        int hotbarSlotX = 8;
        int hotbarSlotY = 162;
        for(int x = 0; x < 9; x++){
            this.addSlotToContainer(new Slot(playerInv, x, hotbarSlotX + x * 18, hotbarSlotY));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.chestInventory.isUsableByPlayer(playerIn);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        chestInventory.closeInventory(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    public TileEntityCoffinChest getChestInventory(){
        return this.chestInventory;
    }
}
