package com.twojeremys.vampiric.potions;

import com.twojeremys.vampiric.init.ModPotions;
import com.twojeremys.vampiric.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.potion.Potion;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class PotionGarlic extends Potion {
    static final int potionColorBrown = 13542519;   // just to make the code more readable
    static final boolean badEffect = true;         // just to make the code more readable

    public PotionGarlic(){
        super(badEffect, potionColorBrown);
        setPotionName("effect.garlic_essence");
        setRegistryName(new ResourceLocation(Reference.MOD_ID + ":" + "garlic_essence"));
        setEffectiveness(0.25D);
        setIconIndex(6, 0);

        ModPotions.POTIONS.add(this);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        // TODO: Add that it must be a vampire to have any impact
        //if (entityLivingBaseIn.getClass() == EntityCreeper.class){
        if (entityLivingBaseIn.isCreatureType(EnumCreatureType.MONSTER, true)) {
            if (entityLivingBaseIn.getHealth() > 1.0F) {
                entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, 1.0F);
            }
        }
        else
        {
            // Remove it if it's not the correct monster
            entityLivingBaseIn.removePotionEffect(this);
        }
    }

    // When does this get called!?  Doesn't seem to get called when the player is using a potion
    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health) {

        // TODO: Add that it must be a vampire to have any impact
        //if (entityLivingBaseIn.getClass() == EntityCreeper.class){
        if (entityLivingBaseIn.isCreatureType(EnumCreatureType.MONSTER, true)) {
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
    }

    // This is needed to be overridden so that we can pass back TRUE
    // Otherwise the PotionEffect class won't call this potions performEffect method.
    @Override
    public boolean isReady(int duration, int amplifier) {
        int j = 25 >> amplifier;

        if (j > 0)
        {
            return duration % j == 0;
        }
        else
        {
            return true;
        }
    }
}
