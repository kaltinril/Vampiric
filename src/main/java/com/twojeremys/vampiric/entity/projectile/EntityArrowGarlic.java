package com.twojeremys.vampiric.entity.projectile;

import com.twojeremys.vampiric.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public class EntityArrowGarlic extends EntityTippedArrow implements IProjectile, IThrowableEntity {

    public EntityArrowGarlic(World worldIn){
        super(worldIn);
        setGarlicEffects();
    }

    public EntityArrowGarlic(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y ,z);
        setGarlicEffects();
    }

    public EntityArrowGarlic(World worldIn, EntityLivingBase entity){
        super(worldIn, entity);
        setGarlicEffects();
    }

    // Things to happen
    private void setGarlicEffects(){
        this.addEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 10));
        this.addEffect(new PotionEffect(MobEffects.POISON, 100, 10));
    }

    // TODO: Override the hit mechanics so that we can only hurt BATs or VAMPIRES with this.

    @Override
    protected ItemStack getArrowStack() {
        ItemStack itemstack = new ItemStack(ModItems.GARLIC_ARROW);
        itemstack.setItemDamage(0);
        return itemstack;
    }

    @Override
    public Entity getThrower() {
        return shootingEntity;
    }

    @Override
    public void setThrower(Entity entity) {
        this.shootingEntity = entity;
    }
}
