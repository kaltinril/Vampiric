package com.kaltinril.vampiric.core.block;

import com.kaltinril.vampiric.VampiricMod;
import com.kaltinril.vampiric.lists.EffectList;
import com.kaltinril.vampiric.lists.FluidList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHolyWater extends FlowingFluidBlock {

    public BlockHolyWater() {
        super(() -> FluidList.holy_water, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops());
        this.setRegistryName(VampiricMod.modid, "holy_water");
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        if (this.getFluid().isIn(FluidList.Tags.HOLY_WATER)){
            // TODO: Change MonsterEntity to VampireEntity or "UnHolyEntity"
            if(entityIn instanceof MonsterEntity){
                // TODO: Possible use a potion effect by creating new one called holy_water or holy_burn
                ((MonsterEntity) entityIn).setFire(4);
                //.addPotionEffect(new EffectInstance(EffectList.garlic_essence, 160, 2)); // Leaving this here to show how to use set a potion
            }
        }
    }
}
