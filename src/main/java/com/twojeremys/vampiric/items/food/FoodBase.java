package com.twojeremys.vampiric.items.food;

import com.twojeremys.vampiric.Main;
import com.twojeremys.vampiric.init.ModItems;
import com.twojeremys.vampiric.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class FoodBase extends ItemFood implements IHasModel {

    public FoodBase(String name, int amount, float saturation, boolean isAnimalFood){
        super(amount, saturation, isAnimalFood);

        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.FOOD);

        // Add this item to the list of items in minecraft
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "Inventory");
    }
}
