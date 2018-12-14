package com.twojeremys.vampiric.blocks;

import com.twojeremys.vampiric.init.ModBlocks;
import com.twojeremys.vampiric.init.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTomatoPlant extends BlockCrops {
    private static final AxisAlignedBB[] tomato = new AxisAlignedBB[]{
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D)
            ,new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D)
            ,new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D)
            ,new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D)
            ,new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D)
            ,new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D)
            ,new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D)
            ,new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)
    };

    public BlockTomatoPlant(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote){
            // How old the crop is (Max)
            if (this.isMaxAge(state)){
                // How many should drop at max age? (2 currently)
                worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.TOMATO, worldIn.rand.nextInt(4) + 1)));
                worldIn.setBlockState(pos, this.withAge(0));
                return true;
            }
        }
        return false;
    }

    @Override
    protected Item getSeed() {
        return ModItems.TOMATO_SEEDS;
    }

    @Override
    protected Item getCrop() {
        return ModItems.TOMATO;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return tomato[(Integer) state.getValue(this.getAgeProperty())];
        //return garlic[((Integer)state.getValue(this.getAgeProperty())).intValue()]; // Intelij Said I didn't need to do this.
    }
}
