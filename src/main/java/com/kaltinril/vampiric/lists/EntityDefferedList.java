package com.kaltinril.vampiric.lists;

import com.kaltinril.vampiric.VampiricMod;
import com.kaltinril.vampiric.entity.projectile.SilverArrowEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityDefferedList {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, VampiricMod.modid);
    public static final String SILVER_ARROW_NAME = "silver_arrow";
    public static final RegistryObject<EntityType<SilverArrowEntity>> silver_arrow_entity = ENTITY_TYPES.register(SILVER_ARROW_NAME, () ->
            EntityType.Builder.<SilverArrowEntity>create(SilverArrowEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                    .build(new ResourceLocation(VampiricMod.modid, SILVER_ARROW_NAME).toString())
    );
}
