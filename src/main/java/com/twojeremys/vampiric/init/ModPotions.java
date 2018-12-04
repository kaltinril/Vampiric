package com.twojeremys.vampiric.init;

import com.twojeremys.vampiric.potions.PotionCustom;
import com.twojeremys.vampiric.util.Reference;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModPotions {
    public static final List<Potion> POTIONS = new ArrayList<Potion>();
    public static final List<PotionType> POTION_TYPES = new ArrayList<PotionType>();

    // Create a Garlic Essense Potion
    public static final Potion GARLIC_ESSENCE = new PotionCustom("garlic_essence", true,13542519, 6, 0, 0.25D, 1.0f);

    // Create the effect that can be applied and passed around
    // durationIn N * 20 = N seconds.  Oddly some time goes missing
    // amplifierIn // 0 = Level 1, 1 = level 2 ("Strong" potion)
    public static final PotionType POTION_GARLIC_ESSENCE = new PotionType("garlic_essence", new PotionEffect(GARLIC_ESSENCE,30*20*2,0)).setRegistryName(new ResourceLocation(Reference.MOD_ID + ":" + "garlic_essence"));
    public static final PotionType LONG_POTION_GARLIC_ESSENCE = new PotionType("garlic_essence", new PotionEffect(GARLIC_ESSENCE,30*20*4,0)).setRegistryName(new ResourceLocation(Reference.MOD_ID + ":" + "long_garlic_essence"));
    public static final PotionType STRONG_GARLIC_ESSENCE = new PotionType("garlic_essence", new PotionEffect(GARLIC_ESSENCE,30*20,1)).setRegistryName(new ResourceLocation(Reference.MOD_ID + ":" + "strong_garlic_essence"));

    public static void registerPotions(){
        registerIndividualPotion(POTION_GARLIC_ESSENCE, LONG_POTION_GARLIC_ESSENCE, STRONG_GARLIC_ESSENCE, GARLIC_ESSENCE, PotionTypes.MUNDANE, ModItems.GARLIC_PASTE);
    }

    private static void registerIndividualPotion(PotionType defaultPotion, PotionType longPotion, PotionType strongPotion, Potion effect, PotionType basePotion, Item ingredient){
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);
        ForgeRegistries.POTION_TYPES.register(strongPotion);

        // Register mixes (recipes)
        PotionHelper.addMix(basePotion, ingredient, defaultPotion);             // Create the defaultPotion
        PotionHelper.addMix(defaultPotion, Items.REDSTONE, longPotion);         // Create the long version using standard redstone
        PotionHelper.addMix(defaultPotion, Items.GLOWSTONE_DUST, strongPotion); // Create the long version using standard redstone
    }
}
