package com.kaltinril.vampiric.core.item;

import com.kaltinril.vampiric.entity.projectile.SilverArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;

public class SilverArrowItem extends ArrowItem {
    public SilverArrowItem(Properties builder) {
        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
            return new SilverArrowEntity(worldIn, shooter);
    }
}
