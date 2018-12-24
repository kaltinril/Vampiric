package com.twojeremys.vampiric.util.handlers;

import com.twojeremys.vampiric.Main;
import com.twojeremys.vampiric.blocks.container.RenderCoffinChest;
import com.twojeremys.vampiric.blocks.tileentity.TileEntityCoffinChest;
import com.twojeremys.vampiric.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntities(){
        GameRegistry.registerTileEntity(TileEntityCoffinChest.class, new ResourceLocation(Reference.MOD_ID + ":coffin_chest.json"));


    }
}
