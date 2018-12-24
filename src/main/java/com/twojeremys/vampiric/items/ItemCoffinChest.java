package com.twojeremys.vampiric.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemCoffinChest extends ItemBlock {

    public ItemCoffinChest(Block block)
    {
        super(block);
    }

    /**
     * Called to actually place the block, after the location is determined
     * and all permission checks have been made.
     *
     * @param stack The item stack that was used to place the block. This can be changed inside the method.
     * @param player The player who is placing the block. Can be null if the block is not being placed by a player.
     * @param side The side the player (or machine) right-clicked on.
     */
    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
    {
        // Make sure the block to the RIGHT is placeable
        if (!this.canPlaceCoffinAt(world, player, pos)) {return false;}

        return super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
    }

    private boolean canPlaceCoffinAt(World world, EntityPlayer player, BlockPos pos){
        EnumFacing playerFacing = getPlayerFacingDirection(player);
        BlockPos blockPOS = pos.east();

        if (playerFacing == EnumFacing.NORTH)
            blockPOS = pos.east();
        else if (playerFacing == EnumFacing.EAST)
            blockPOS = pos.south();
        else if (playerFacing == EnumFacing.SOUTH)
            blockPOS = pos.west();
        else if (playerFacing == EnumFacing.WEST)
            blockPOS = pos.north();

        return world.getBlockState(blockPOS).getBlock().isReplaceable(world, blockPOS);
    }

    private EnumFacing getPlayerFacingDirection(EntityLivingBase player){
        // Set this blocks direction to face, picking the opposite of the player direction
        return EnumFacing.getHorizontal(MathHelper.floor((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3);
    }
}