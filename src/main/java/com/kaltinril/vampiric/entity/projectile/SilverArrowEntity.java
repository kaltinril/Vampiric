package com.kaltinril.vampiric.entity.projectile;

import com.kaltinril.vampiric.entity.VampireBat;
import com.kaltinril.vampiric.lists.EntityList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SilverArrowEntity extends AbstractArrowEntity {

    /*public SilverArrowEntity(EntityType<? extends SilverArrowEntity> entityEntityType, World world) {
        super((EntityType<? extends SilverArrowEntity>) entityEntityType, world);
    }*/

    public SilverArrowEntity(final EntityType<? extends SilverArrowEntity> entityType, final World world) {
        super(entityType, world);
    }

    public SilverArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityList.silver_arrow_entity, x, y, z, worldIn);
    }

    public SilverArrowEntity(World worldIn, LivingEntity shooter) {
        super(EntityList.silver_arrow_entity, shooter, worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }
}
