package com.kaltinril.vampiric.entity.projectile;

import com.kaltinril.vampiric.lists.ItemList;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SilverArrowEntity extends AbstractArrowEntity {

    public SilverArrowEntity(final EntityType<? extends SilverArrowEntity> entityType, final World world) {
        super(entityType, world);
    }

    public SilverArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityType.ARROW, x, y, z, worldIn);
    }

    public SilverArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityType.ARROW, shooter, worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemList.silver_arrow);
    }
}
