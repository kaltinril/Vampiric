package com.twojeremys.vampiric.items.tools;

import com.twojeremys.vampiric.Main;
import com.twojeremys.vampiric.entity.projectile.EntityArrowGarlic;
import com.twojeremys.vampiric.init.ModItems;
import com.twojeremys.vampiric.util.IHasModel;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArrowGarlic extends ItemArrow implements IHasModel {

    public ItemArrowGarlic(String name){
        super();
        setUnlocalizedName(name);
        setRegistryName(name);

        // Add this item to the list of items in minecraft
        ModItems.ITEMS.add(this);
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        EntityArrowGarlic entityArrow = new EntityArrowGarlic(worldIn, shooter);
        entityArrow.setDamage(0); // On critical still has a chance to do damage apparently

        return entityArrow;
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "Inventory");
    }
}
