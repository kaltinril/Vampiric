package com.kaltinril.vampiric.core.fluid;

import com.kaltinril.vampiric.VampiricMod;
import com.kaltinril.vampiric.lists.BlockList;
import com.kaltinril.vampiric.lists.ItemList;
import com.kaltinril.vampiric.lists.FluidList;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;

public abstract class FluidHolyWater extends FlowingFluid {
    @Override
    public Fluid getFlowingFluid() {
        return FluidList.flowing_holy_water;
    }

    @Override
    public Fluid getStillFluid() {
        return FluidList.holy_water;
    }

    @Override
    protected boolean canSourcesMultiply() {
        return false;
    }

    @Override
    protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
        /*
      TileEntity tileentity = state.getBlock().hasTileEntity() ? worldIn.getTileEntity(pos) : null;
      Block.spawnDrops(state, worldIn.getWorld(), pos, tileentity);
        */
    }

    @Override
    protected int getSlopeFindDistance(IWorldReader worldIn) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(IWorldReader worldIn) {
        return 1;
    }

    @Override
    public Item getFilledBucket() {
        return ItemList.holy_water_bucket;
    }

    // How the block flows
    @Override
    protected boolean func_215665_a(IFluidState state, IBlockReader world, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.isIn(FluidList.Tags.HOLY_WATER);
    }

    @Override
    public int getTickRate(IWorldReader p_205569_1_) {
        return 5;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    protected BlockState getBlockState(IFluidState state) {
        return BlockList.holy_water.getDefaultState().with(FlowingFluidBlock.LEVEL, Integer.valueOf(getLevelFromState(state)));
    }

    @Override
    public boolean isEquivalentTo(Fluid fluidIn) {
        // Can we use getTags instead?
        return fluidIn == FluidList.holy_water || fluidIn == FluidList.flowing_holy_water;
    }

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder(new ResourceLocation(VampiricMod.modid, "block/holy_water_still"), new ResourceLocation(VampiricMod.modid, "block/holy_water_flow"))
                .translationKey("block.vampiric.holy_water")
                .build(this);
    }

    public static class Flowing extends FluidHolyWater {

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        @Override
        public boolean isSource(IFluidState state) {
            return false;
        }

        @Override
        public int getLevel(IFluidState state) {
            return state.get(FluidHolyWater.LEVEL_1_8);
        }
    }

    public static class Source extends FluidHolyWater {

        @Override
        public boolean isSource(IFluidState state) {
            return true;
        }

        @Override
        public int getLevel(IFluidState state) {
            return 8;
        }
    }
}
