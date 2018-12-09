package com.twojeremys.vampiric.init;

import com.twojeremys.vampiric.items.ItemBase;
import com.twojeremys.vampiric.items.food.GarlicPlant;
import com.twojeremys.vampiric.items.tools.ItemArrowGarlic;
import com.twojeremys.vampiric.items.tools.ToolStake;
import com.twojeremys.vampiric.items.tools.ToolSword;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    // Materials
    public static final ToolMaterial MATERIAL_STAKE = EnumHelper.addToolMaterial("material_stake", 0, 59, 12.0f, 6.0f, 0);
    public static final ToolMaterial MATERIAL_SILVER = EnumHelper.addToolMaterial("material_silver", 0, 45, 10.0F, 0.0F, 19);

    // Items
    public static final Item SILVER_INGOT = new ItemBase("silver_ingot");
    public static final Item GARLIC_PASTE = new ItemBase("garlic_paste").setCreativeTab(CreativeTabs.FOOD);

    // Food
    // https://minecraft.gamepedia.com/Hunger
    public static final Item GARLIC = new GarlicPlant("garlic", 1, 0.6f, false);

    // Tools
    public static final ItemSword WOODEN_STAKE = new ToolStake("wooden_stake", MATERIAL_STAKE);
    public static final ItemArrow GARLIC_ARROW = new ItemArrowGarlic("garlic_arrow");

    public static final ItemSword SILVER_SWORD = new ToolSword("silver_sword", MATERIAL_SILVER);

    /*
    public static final ItemSpade SILVER_SHOVEL = new ToolSpade("silver_sword", MATERIAL_SILVER);
    public static final ItemPickaxe SILVER_PICKAXE = new ToolPickaxe("silver_sword", MATERIAL_SILVER);
    public static final ItemAxe SILVER_AXE = new ToolAxe("silver_sword", MATERIAL_SILVER);
    public static final ItemHoe SILVER_HOE = new ToolHoe("silver_sword", MATERIAL_SILVER);
    */


}
