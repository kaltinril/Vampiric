package com.twojeremys.vampiric.init;

import com.twojeremys.vampiric.potions.PotionGarlic;
import com.twojeremys.vampiric.potions.PotionTypeGarlic;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

public class ModPotions {
    public static final List<Potion> POTIONS = new ArrayList<Potion>();
    public static final List<PotionType> POTION_TYPES = new ArrayList<PotionType>();

    public static final Potion GARLIC_ESSENCE = new PotionGarlic();

    public static final PotionType POTION_GARLIC_ESSENCE =
            new PotionTypeGarlic("garlic_essence",
                    new PotionEffect(GARLIC_ESSENCE,
                            30*20,          // N * 20 = N seconds.  Oddly some time goes missing
                            0));            // 0 = Level 1, 1 = level 2, etc
}
