package com.twojeremys.vampiric.blocks;

import com.twojeremys.vampiric.Main;
import com.twojeremys.vampiric.blocks.tileentity.TileEntityCoffinChest;
import com.twojeremys.vampiric.init.ModBlocks;
import com.twojeremys.vampiric.init.ModItems;
import com.twojeremys.vampiric.util.IHasModel;
import com.twojeremys.vampiric.util.Reference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCoffinChest extends BlockContainer implements IHasModel {

    public BlockCoffinChest(String name){
        super(Material.WOOD);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MISC);

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
        InventoryHelper.dropInventoryItems(worldIn, pos, tileEntity);
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasDisplayName()){
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if (tileEntity instanceof TileEntityCoffinChest){
                ((TileEntityCoffinChest)tileEntity).setCustomName(stack.getDisplayName());
            }
        }
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

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
