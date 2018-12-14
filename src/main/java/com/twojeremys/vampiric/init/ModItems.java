package com.twojeremys.vampiric.init;

import com.twojeremys.vampiric.items.ItemBase;
import com.twojeremys.vampiric.items.ItemTomatoSeeds;
import com.twojeremys.vampiric.items.armor.ArmorBase;
import com.twojeremys.vampiric.items.food.GarlicPlant;
import com.twojeremys.vampiric.items.food.ItemFoodTomato;
import com.twojeremys.vampiric.items.tools.*;
import com.twojeremys.vampiric.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    // Materials
    public static final ToolMaterial MATERIAL_STAKE = EnumHelper.addToolMaterial("material_stake", 0, 59, 12.0f, 6.0f, 0);
    public static final ToolMaterial MATERIAL_SILVER = EnumHelper.addToolMaterial("material_silver", 0, 45, 10.0F, 0.0F, 19);
    public static final ArmorMaterial ARMOR_MATERIAL_SILVER = EnumHelper.addArmorMaterial("armor_material_silver", Reference.MOD_ID + ":silver", 7,
            new int[]{2, 4, 5, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);

    // Items
    public static final Item SILVER_INGOT = new ItemBase("silver_ingot");
    public static final Item GARLIC_PASTE = new ItemBase("garlic_paste").setCreativeTab(CreativeTabs.FOOD);


    // Food
    // https://minecraft.gamepedia.com/Hunger
    public static final Item GARLIC = new GarlicPlant("garlic", 1, 0.6f, false);
    public static final Item TOMATO = new ItemFoodTomato("tomato", 1, 0.6f, false);

    // Seeds
    public static final Item TOMATO_SEEDS = new ItemTomatoSeeds("tomato_seeds");

    // Tools - Generic
    public static final ItemSword WOODEN_STAKE = new ToolStake("wooden_stake", MATERIAL_STAKE);
    public static final ItemArrow GARLIC_ARROW = new ItemArrowGarlic("garlic_arrow");

    // Tools - Silver
    public static final ItemSword SILVER_SWORD = new ToolSword("silver_sword", MATERIAL_SILVER);
    public static final ItemSpade SILVER_SHOVEL = new ToolSpade("silver_shovel", MATERIAL_SILVER);
    public static final ItemPickaxe SILVER_PICKAXE = new ToolPickaxe("silver_pickaxe", MATERIAL_SILVER);
    public static final ItemAxe SILVER_AXE = new ToolAxe("silver_axe", MATERIAL_SILVER, 6.5f, -3.0f);
    public static final ItemHoe SILVER_HOE = new ToolHoe("silver_hoe", MATERIAL_SILVER);

    // Armor
    // NOTE: renderIndexIn is passed in, but no code in minecraft actually uses it.
    public static final Item SILVER_HELMET = new ArmorBase("silver_helmet", ARMOR_MATERIAL_SILVER, 999, EntityEquipmentSlot.HEAD);
    public static final Item SILVER_CHESTPLATE= new ArmorBase("silver_chestplate", ARMOR_MATERIAL_SILVER, 999, EntityEquipmentSlot.CHEST);
    public static final Item SILVER_LEGGINGS = new ArmorBase("silver_leggings", ARMOR_MATERIAL_SILVER, 999, EntityEquipmentSlot.LEGS); // TODO: Why 2????
    public static final Item SILVER_BOOTS = new ArmorBase("silver_boots", ARMOR_MATERIAL_SILVER, 999, EntityEquipmentSlot.FEET);

}
