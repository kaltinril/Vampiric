package com.kaltinril.vampiric.core.potion;

import com.kaltinril.vampiric.VampiricMod;
import com.kaltinril.vampiric.lists.EffectList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import javax.annotation.Nullable;

// the entire point of this silly class is to expose the private constructor of the Effect class
public class ModEffect extends Effect {

    private float effectAmount; // How much this affect does

    public ModEffect(EffectType typeIn, int liquidColorIn, float effectAmountIn) {
        super(typeIn, liquidColorIn);
        this.effectAmount = effectAmountIn;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (this == EffectList.garlic_essence){
            // TODO: Add that it must be a vampire to have any impact
            //if (entityLivingBaseIn.getClass() == EntityCreeper.class){
            if (entityLivingBaseIn.getClassification(true) == EntityClassification.MONSTER) {
                // if the entity has a least 1 health left.
                if (entityLivingBaseIn.getHealth() > effectAmount) {
                    entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, effectAmount);
                }
            }
            else
            {
                // Remove it if it's not the correct monster
                entityLivingBaseIn.removePotionEffect(this);
            }
            // TODO: Add more elseif statements for each new potion
        } else if(this == EffectList.holy_burn){
            if (entityLivingBaseIn.isEntityUndead() && entityLivingBaseIn.getClassification(true) == EntityClassification.MONSTER)
                entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, effectAmount);
            else
                entityLivingBaseIn.removePotionEffect(this);  // Remove it if it's not the correct monster
        }
        else {
            super.performEffect(entityLivingBaseIn, amplifier);
        }
    }

    // When does this get called!?  Doesn't seem to get called when the player is using a potion
    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) {
        VampiricMod.LOGGER.info("Vampiric: Affect Entity method called.");

        // TODO: Add that it must be a vampire to have any impact
        if (this == EffectList.garlic_essence){
            if (entityLivingBaseIn.getClassification(true) == EntityClassification.MONSTER) {
                int j = (int) (health * (double) (6 << amplifier) + 0.5D);

                if (source == null) {
                    entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, (float) j);
                } else {
                    entityLivingBaseIn.attackEntityFrom(DamageSource.causeIndirectMagicDamage(source, indirectSource), (float) j);
                }
            }
            else
            {
                // Remove it if it's not the correct monster
                entityLivingBaseIn.removePotionEffect(this);
            }
        } else if (this == EffectList.holy_burn) {
            // Don't hurt undead if they are not monsters (like passive etc)
            if (entityLivingBaseIn.isEntityUndead() && entityLivingBaseIn.getClassification(true) == EntityClassification.MONSTER) {
                int j = (int) (health * (double) (6 << amplifier) + 0.5D);

                if (source == null) {
                    entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, (float) j);
                } else {
                    entityLivingBaseIn.attackEntityFrom(DamageSource.causeIndirectMagicDamage(source, indirectSource), (float) j);
                }
            }
            else {
                entityLivingBaseIn.removePotionEffect(this);
            }
        }
        else {
            super.performEffect(entityLivingBaseIn, amplifier);
        }
    }

    // This is needed to be overridden so that we can pass back TRUE
    // Otherwise the PotionEffect class won't call this potions performEffect method.
    @Override
    public boolean isReady(int duration, int amplifier) {
        int j = 100 >> amplifier;
        if (this == EffectList.garlic_essence) {
            if (j > 0) {
                return duration % j == 0;
            } else {
                return true;
            }
        } else if (this == EffectList.holy_burn) {
            j = 75 >> amplifier;
            if (j > 0) {
                return duration % j == 0;
            } else {
                return true;
            }
        }
        else {
            return true;
        }
    }
}
