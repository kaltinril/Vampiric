package com.twojeremys.vampiric.proxy;

import com.twojeremys.vampiric.Main;
import com.twojeremys.vampiric.blocks.container.RenderCoffinChest;
import com.twojeremys.vampiric.blocks.tileentity.TileEntityCoffinChest;
import com.twojeremys.vampiric.util.handlers.TileEntityHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{

    @Override
    public void registerItemRenderer(Item item, int meta, String id){
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerModel(Item item, int metadata)
    {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));

        Main.proxy.registerTileEntity(TileEntityCoffinChest.class, new RenderCoffinChest());
    }

    @Override
    public void registerTileEntity(java.lang.Class te, TileEntitySpecialRenderer tesr) {
        // Bind the mode and the render together
        ClientRegistry.bindTileEntitySpecialRenderer(te, tesr);
    }
}
