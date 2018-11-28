package com.twojeremys.vampiric.init;

import com.twojeremys.vampiric.items.ItemBase;
import com.twojeremys.vampiric.items.food.GarlicPlant;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    // Items
    public static final Item SILVER_INGOT = new ItemBase("silver_ingot");

    // Food
    // https://minecraft.gamepedia.com/Hunger
    public static final Item GARLIC = new GarlicPlant("garlic", 1, 0.6f, false);
}
