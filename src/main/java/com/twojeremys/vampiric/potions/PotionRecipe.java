package com.twojeremys.vampiric.potions;

import com.twojeremys.vampiric.init.ModItems;
import com.twojeremys.vampiric.init.ModPotions;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionHelper;

// Simple class to contain list all all potion recipes
public class PotionRecipe {
    public PotionRecipe(){

    }

    public static void init(){
        PotionHelper.addMix(PotionTypes.MUNDANE, ModItems.GARLIC_PASTE, ModPotions.POTION_GARLIC_ESSENCE);
    }
}
