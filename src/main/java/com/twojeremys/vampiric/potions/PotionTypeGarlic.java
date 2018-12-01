package com.twojeremys.vampiric.potions;

import com.twojeremys.vampiric.init.ModPotions;
import com.twojeremys.vampiric.util.Reference;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;

public class PotionTypeGarlic extends PotionType {

    public PotionTypeGarlic(String name, PotionEffect potionEffect){
        super(name, potionEffect);
        setRegistryName(new ResourceLocation(Reference.MOD_ID + ":" + "type_garlic_essence"));

        ModPotions.POTION_TYPES.add(this);
    }

}
