package com.twojeremys.vampiric.entity.projectile;

import com.twojeremys.vampiric.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public class EntityArrowGarlic extends EntityTippedArrow implements IProjectile, IThrowableEntity {

    public EntityArrowGarlic(World worldIn){
        super(worldIn);
    }

    public EntityArrowGarlic(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y ,z);
        this.setPosition(x, y, z);
    }

    public EntityArrowGarlic(World worldIn, EntityLivingBase entity){
        super(worldIn, entity);
    }


    @Override
    protected ItemStack getArrowStack() {
        ItemStack itemstack = new ItemStack(ModItems.GARLIC_ARROW);
        itemstack.setItemDamage(3);
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
