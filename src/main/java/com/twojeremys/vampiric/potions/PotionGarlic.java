package com.twojeremys.vampiric.potions;

import com.twojeremys.vampiric.init.ModPotions;
import com.twojeremys.vampiric.util.Reference;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionGarlic extends Potion {
    static final int potionColorBrown = 13542519;   // just to make the code more readable
    static final boolean badEffect = false;         // just to make the code more readable

    public PotionGarlic(){
        super(badEffect, potionColorBrown);
        setPotionName("effect.garlic_essence");
        setRegistryName(new ResourceLocation(Reference.MOD_ID + ":" + "garlic_essence"));

        ModPotions.POTIONS.add(this);
    }

}
