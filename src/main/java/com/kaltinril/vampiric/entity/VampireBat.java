package com.kaltinril.vampiric.entity;

import com.kaltinril.vampiric.VampiricMod;
import com.kaltinril.vampiric.lists.EntityList;
import com.kaltinril.vampiric.lists.ItemList;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

// Make an UndeadEntity ?  Somehow need to classify it as a vampire for the garlic potion
public class VampireBat extends CreatureEntity {

    @SuppressWarnings("unchecked")
    public VampireBat(EntityType<? extends CreatureEntity> type, World world) {
        // I think we should pass in the type just like mineraft does it
        super((EntityType<? extends CreatureEntity>) EntityList.vampire_bat, world);
    }

    @Override
    protected void registerGoals(){
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 2.5d));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(9.0d); // Max health is a bit more than a regular bat which is 6
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)1.0d);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D); // Only does 1 damage
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D); //
    }
}
