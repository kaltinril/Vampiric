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

public class PotionCustom extends Potion {

    private float effectAmount;

    public PotionCustom(String name, boolean isBadEffect, int color, int iconIndexX, int iconIndexY, double effectiveness, float effectAmount){
        super(isBadEffect, color);

        setPotionName("effect." + name);
        setRegistryName(new ResourceLocation(Reference.MOD_ID + ":" + name));
        setEffectiveness(effectiveness);
        setIconIndex(iconIndexX, iconIndexY);

        this.effectAmount = effectAmount;

        ModPotions.POTIONS.add(this);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        if (this == ModPotions.GARLIC_ESSENCE){
            // TODO: Add that it must be a vampire to have any impact
            //if (entityLivingBaseIn.getClass() == EntityCreeper.class){
            if (entityLivingBaseIn.isCreatureType(EnumCreatureType.MONSTER, true)) {
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
        }else {
            super.performEffect(entityLivingBaseIn, amplifier);
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
