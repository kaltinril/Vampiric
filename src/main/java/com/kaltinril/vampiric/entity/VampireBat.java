package com.kaltinril.vampiric.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

// Make an UndeadEntity ?  Somehow need to classify it as a vampire for the garlic potion

// TODO: Look at PhantomEntity for FlyingEntity
public class VampireBat extends MonsterEntity {

    @SuppressWarnings("unchecked")
    public VampireBat(final EntityType<? extends VampireBat> entityType, final World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals(){
        super.registerGoals();
        // TODO: create a new goal called "fearItemGoal" based on opposite direction as the TemptGoal class.  Everything should work the same except it should run AWAY from the mob, not toward.
        this.goalSelector.addGoal(0, new RestrictSunGoal(this));
        this.goalSelector.addGoal(1, new FleeSunGoal(this, 1.0D));
        //this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));

        // TODO: Understand why bats get stuck on a goal, and don't fleesungoal once daylight hits?
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

        // Vampire bats drink from cattle (Essentially any animal)
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, AnimalEntity.class, true));
        //this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, PigEntity.class, true));
        //this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, SheepEntity.class, true));
        //this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, HorseEntity.class, true));
    }


    // NOTE from Discord User: you can register an unregistered attribute by adding this line in your registerAttributes() method:
    //   this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTRIBUTE_TO_REGISTER);
    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(9.0d); // Max health is a bit more than a regular bat which is 6
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.45D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5d); // Possibly drain blood instead of dealing damage? or give possible rabies?
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0d); //
    }

    // Probably not appropriate for a normal vampire bat, but for a Vampire mob, yes, however, need a mob to test this ons
    // TODO: Remove this one we implement other entities
    // TODO: Create my own creature types called VAMPIRE, WEREWOLF, MUMMY
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    // Vampires burn in the sunlight
    public void livingTick() {
        boolean flag = this.isInDaylight();

        if (flag) {
            this.setFire(4); // Set on fire for n seconds in in sunlight
        }

        super.livingTick();
    }

    // SOUND

    protected float getSoundVolume() {
        return 0.1F;
    }

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.95F;
    }

    @Nullable
    public SoundEvent getAmbientSound() {
        return this.rand.nextInt(4) != 0 ? null : SoundEvents.ENTITY_BAT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BAT_DEATH;
    }


    // Prevent bat from interacting with enviornment by pushing or colliding or triggering things
    public boolean canBePushed() {
        return false;
    }

    protected void collideWithEntity(Entity p_82167_1_) {
    }

    protected void collideWithNearbyEntities() {
    }

    protected void updateFallState(double p_184231_1_, boolean p_184231_3_, BlockState p_184231_4_, BlockPos p_184231_5_) {
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }
}
