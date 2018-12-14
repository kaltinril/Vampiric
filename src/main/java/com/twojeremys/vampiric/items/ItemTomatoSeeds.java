package com.twojeremys.vampiric.items;

import com.twojeremys.vampiric.Main;
import com.twojeremys.vampiric.init.ModBlocks;
import com.twojeremys.vampiric.init.ModItems;
import com.twojeremys.vampiric.util.IHasModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ItemTomatoSeeds extends Item implements IHasModel, IPlantable {

    public ItemTomatoSeeds(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MATERIALS);

        // Add this item to the list of items in minecraft
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "Inventory");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){

        ItemStack stack = player.getHeldItem(hand);
        IBlockState state = worldIn.getBlockState(pos);

        // If the block is facing up, and we can edit it, and it can support plants, and their is air there, plan it
        if(facing == EnumFacing.UP
                && player.canPlayerEdit(pos.offset(facing), facing, stack)
                && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this)
                && worldIn.isAirBlock(pos.up())){
            worldIn.setBlockState(pos.up(), ModBlocks.TOMATO_PLANT.getDefaultState());
            stack.shrink(1);

            return EnumActionResult.SUCCESS;
        }
        else
            return EnumActionResult.FAIL;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {

        return ModBlocks.TOMATO_PLANT.getDefaultState();
    }
}
