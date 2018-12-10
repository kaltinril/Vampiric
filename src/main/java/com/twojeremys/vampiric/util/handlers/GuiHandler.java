package com.twojeremys.vampiric.util.handlers;

import com.twojeremys.vampiric.blocks.container.ContainerCoffinChest;
import com.twojeremys.vampiric.blocks.gui.GuiCoffinChest;
import com.twojeremys.vampiric.blocks.tileentity.TileEntityCoffinChest;
import com.twojeremys.vampiric.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_COFFIN_CHEST) return new ContainerCoffinChest(player.inventory, (TileEntityCoffinChest)world.getTileEntity(new BlockPos(x, y, z)), player);
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_COFFIN_CHEST) return new GuiCoffinChest(player.inventory, (TileEntityCoffinChest)world.getTileEntity(new BlockPos(x, y, z)), player);
        return null;
    }
}
