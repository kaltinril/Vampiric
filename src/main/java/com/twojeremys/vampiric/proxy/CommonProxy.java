package com.twojeremys.vampiric.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;

public class CommonProxy {

    public void registerItemRenderer(Item item, int meta, String id){ }
    public void registerModel(Item item, int metadata) {}
    public void registerTileEntity(java.lang.Class te, TileEntitySpecialRenderer tesr){}
}
