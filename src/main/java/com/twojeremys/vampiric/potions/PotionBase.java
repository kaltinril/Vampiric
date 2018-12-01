package com.twojeremys.vampiric.potions;

import com.twojeremys.vampiric.Main;
import com.twojeremys.vampiric.init.ModItems;
import com.twojeremys.vampiric.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPotion;


public class PotionBase extends ItemPotion implements IHasModel {

    public PotionBase(String name){
        super();

        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.BREWING);

        // Add this item to the list of items in minecraft
        ModItems.ITEMS.add(this);

    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "Inventory");
    }
}
