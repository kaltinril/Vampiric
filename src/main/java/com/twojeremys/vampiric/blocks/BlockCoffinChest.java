package com.twojeremys.vampiric.blocks;

import com.twojeremys.vampiric.Main;
import com.twojeremys.vampiric.blocks.tileentity.TileEntityCoffinChest;
import com.twojeremys.vampiric.init.ModBlocks;
import com.twojeremys.vampiric.init.ModItems;
import com.twojeremys.vampiric.util.IHasModel;
import com.twojeremys.vampiric.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCoffinChest extends BlockContainer implements IHasModel {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    protected static final AxisAlignedBB NORTH_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0D, 0.9375D, 0.875D, 0.9375D);
    protected static final AxisAlignedBB SOUTH_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 1.0D);
    protected static final AxisAlignedBB WEST_CHEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
    protected static final AxisAlignedBB EAST_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 1.0D, 0.875D, 0.9375D);
    protected static final AxisAlignedBB NOT_CONNECTED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);

    public BlockCoffinChest(String name){
        super(Material.WOOD);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MISC);

        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.EAST));

        this.fullBlock = false;

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote){
            playerIn.openGui(Main.instance, Reference.GUI_COFFIN_CHEST, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityCoffinChest tileEntity = (TileEntityCoffinChest)worldIn.getTileEntity(pos);

        if (tileEntity != null) {

            // Only drop items if this is the primary inventory
            if (tileEntity.isFirst)
                InventoryHelper.dropInventoryItems(worldIn, pos, tileEntity);

            // Always destroy the other block as well
            TileEntityCoffinChest otherTileEntity = tileEntity.getAdjacentPart();
            if (otherTileEntity != null) {
                // Check if the other is first
                if (otherTileEntity.isFirst)
                    InventoryHelper.dropInventoryItems(worldIn, pos, otherTileEntity);

                // Remove the link so we don't end up in a loop
                tileEntity.setAdjacentPart(null);

                // Remove the other block and entity
                BlockPos blockPos = otherTileEntity.getPos();   // Get position of other block/entity
                otherTileEntity.clear();                        // Remove all items (Should be none)
                otherTileEntity.setAdjacentPart(null);          // Remove reference
                worldIn.setBlockToAir(blockPos);                // Set block to air
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        //if (!worldIn.isRemote) {
            // Set this blocks direction to face, picking the opposite of the player direction
            EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
            state = state.withProperty(FACING, enumfacing);
            worldIn.setBlockState(pos, state, 3);

            // Pick the direction to place the other block
            // Pick "to the right" based on the facing direction
            BlockPos blockpos = pos.offset(EnumFacing.EAST);
            EnumFacing playerFacing = enumfacing.getOpposite();
            if (playerFacing == EnumFacing.NORTH)
                blockpos = pos.offset(EnumFacing.EAST);
            else if (playerFacing == EnumFacing.EAST)
                blockpos = pos.offset(EnumFacing.SOUTH);
            else if (playerFacing == EnumFacing.SOUTH)
                blockpos = pos.offset(EnumFacing.WEST);
            else if (playerFacing == EnumFacing.WEST)
                blockpos = pos.offset(EnumFacing.NORTH);

            // Get the current block and entity for this
            IBlockState iblockstate = worldIn.getBlockState(blockpos);
            TileEntity placedCoffin = worldIn.getTileEntity(pos);

            // If this was the first block placement, place a second one
            if (placedCoffin instanceof TileEntityCoffinChest) {
                if (((TileEntityCoffinChest) placedCoffin).isFirst) {

                    if (iblockstate.getBlock() == Blocks.AIR) // this)
                    {
                        TileEntity te1 = worldIn.getTileEntity(blockpos);
                        // Place the block, find the new tileEntity, change it to isFirst = false
                        worldIn.setBlockState(blockpos, ModBlocks.COFFIN_CHEST.getDefaultState(), 3);
                        TileEntity te = worldIn.getTileEntity(blockpos);
                        if (te != null) {
                            ((TileEntityCoffinChest) te).isFirst = false;

                            // Make sure the two blocks have reference to each-other
                            ((TileEntityCoffinChest) te).setAdjacentPart((TileEntityCoffinChest) placedCoffin);
                            ((TileEntityCoffinChest) placedCoffin).setAdjacentPart(((TileEntityCoffinChest) te));

                            worldIn.setTileEntity(blockpos, te);
                            TileEntity te2 = worldIn.getTileEntity(blockpos);
                            System.out.println(te2);
                        }
                    }
                }
            }

            if (stack.hasDisplayName()) {
                TileEntity tileEntity = worldIn.getTileEntity(pos);

                if (tileEntity instanceof TileEntityCoffinChest) {
                    ((TileEntityCoffinChest) tileEntity).setCustomName(stack.getDisplayName());
                }
            }
        //}
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCoffinChest();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    // Deprecated, need to figure out replacement for this when minecraft removes it
    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    // Deprecated, need to figure out replacement for this when minecraft removes it
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    // Deprecated, need to figure out replacement for this when minecraft removes it
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    // This makes it so the bounding box for the item is 2 blocks wide
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        if (source.getBlockState(pos.north()).getBlock() == this)
        {
            return NORTH_CHEST_AABB;
        }
        else if (source.getBlockState(pos.south()).getBlock() == this)
        {
            return SOUTH_CHEST_AABB;
        }
        else if (source.getBlockState(pos.west()).getBlock() == this)
        {
            return WEST_CHEST_AABB;
        }
        else
        {
            return source.getBlockState(pos.east()).getBlock() == this ? EAST_CHEST_AABB : NOT_CONNECTED_AABB;
        }
        //return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 2.0D, 1.0D, 1.0D);
    }

    //
    // Block States and directions
    //
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.EAST;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return super.getActualState(state, worldIn, pos);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {

    }

    public IBlockState checkForSurroundingChests(World worldIn, BlockPos pos, IBlockState state)
    {
        if (worldIn.isRemote)
        {
            return state;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (iblockstate.getBlock() != this && iblockstate1.getBlock() != this)
            {
                boolean flag = iblockstate.isFullBlock();
                boolean flag1 = iblockstate1.isFullBlock();

                if (iblockstate2.getBlock() == this || iblockstate3.getBlock() == this)
                {
                    BlockPos blockpos1 = iblockstate2.getBlock() == this ? pos.west() : pos.east();
                    IBlockState iblockstate7 = worldIn.getBlockState(blockpos1.north());
                    IBlockState iblockstate6 = worldIn.getBlockState(blockpos1.south());
                    enumfacing = EnumFacing.SOUTH;
                    EnumFacing enumfacing2;

                    if (iblockstate2.getBlock() == this)
                    {
                        enumfacing2 = (EnumFacing)iblockstate2.getValue(FACING);
                    }
                    else
                    {
                        enumfacing2 = (EnumFacing)iblockstate3.getValue(FACING);
                    }

                    if (enumfacing2 == EnumFacing.NORTH)
                    {
                        enumfacing = EnumFacing.NORTH;
                    }

                    if ((flag || iblockstate7.isFullBlock()) && !flag1 && !iblockstate6.isFullBlock())
                    {
                        enumfacing = EnumFacing.SOUTH;
                    }

                    if ((flag1 || iblockstate6.isFullBlock()) && !flag && !iblockstate7.isFullBlock())
                    {
                        enumfacing = EnumFacing.NORTH;
                    }
                }
            }
            else
            {
                BlockPos blockpos = iblockstate.getBlock() == this ? pos.north() : pos.south();
                IBlockState iblockstate4 = worldIn.getBlockState(blockpos.west());
                IBlockState iblockstate5 = worldIn.getBlockState(blockpos.east());
                enumfacing = EnumFacing.EAST;
                EnumFacing enumfacing1;

                if (iblockstate.getBlock() == this)
                {
                    enumfacing1 = (EnumFacing)iblockstate.getValue(FACING);
                }
                else
                {
                    enumfacing1 = (EnumFacing)iblockstate1.getValue(FACING);
                }

                if (enumfacing1 == EnumFacing.WEST)
                {
                    enumfacing = EnumFacing.WEST;
                }

                if ((iblockstate2.isFullBlock() || iblockstate4.isFullBlock()) && !iblockstate3.isFullBlock() && !iblockstate5.isFullBlock())
                {
                    enumfacing = EnumFacing.EAST;
                }

                if ((iblockstate3.isFullBlock() || iblockstate5.isFullBlock()) && !iblockstate2.isFullBlock() && !iblockstate4.isFullBlock())
                {
                    enumfacing = EnumFacing.WEST;
                }
            }

            state = state.withProperty(FACING, enumfacing);
            worldIn.setBlockState(pos, state, 3);
            return state;
        }
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
