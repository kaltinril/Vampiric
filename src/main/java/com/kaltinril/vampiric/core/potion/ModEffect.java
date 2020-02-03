package com.kaltinril.vampiric.core.potion;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

// the entire point of this silly class is to expose the private constructor of the Effect class
public class ModEffect extends Effect {

    public ModEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }
}
