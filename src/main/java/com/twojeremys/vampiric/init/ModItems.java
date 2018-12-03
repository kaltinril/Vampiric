package com.twojeremys.vampiric.init;

import com.twojeremys.vampiric.items.ItemBase;
import com.twojeremys.vampiric.items.food.GarlicPlant;
import com.twojeremys.vampiric.items.tools.ItemArrowGarlic;
import com.twojeremys.vampiric.items.tools.ToolStake;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    // Materials
    public static final ToolMaterial MATERIAL_STAKE = EnumHelper.addToolMaterial("material_stake", 0, 59, 12.0f, 6.0f, 0);

    // Items
    public static final Item SILVER_INGOT = new ItemBase("silver_ingot");
    public static final Item GARLIC_PASTE = new ItemBase("garlic_paste").setCreativeTab(CreativeTabs.FOOD);

    // Food
    // https://minecraft.gamepedia.com/Hunger
    public static final Item GARLIC = new GarlicPlant("garlic", 1, 0.6f, false);

    // Tools
    public static final ItemSword WOODEN_STAKE = new ToolStake("wooden_stake", MATERIAL_STAKE);
    public static final ItemArrow GARLIC_ARROW = new ItemArrowGarlic("garlic_arrow");
}
