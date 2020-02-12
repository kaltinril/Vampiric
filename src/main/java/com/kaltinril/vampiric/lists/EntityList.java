package com.kaltinril.vampiric.lists;

import com.kaltinril.vampiric.VampiricMod;
import com.kaltinril.vampiric.entity.VampireBat;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.RegistryEvent;

import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.Logger;

@ObjectHolder(VampiricMod.modid)
public class EntityList {
     //public static EntityType<VampireBat> vampire_bat;
    // Changing the above to this line below will allow the eggs to spawn the entity but is "bad form" to do this
    public static EntityType<VampireBat> vampire_bat = (EntityType<VampireBat>) EntityType.Builder.create(VampireBat::new, EntityClassification.MONSTER).size(0.5F, 0.9F).build(VampiricMod.modid + ":vampire_bat").setRegistryName(VampiricMod.location("vampire_bat"));

    public static Item vampire_bat_egg;

    @SuppressWarnings("unchecked")
    public static void registerAll(RegistryEvent.Register<EntityType<?>> event, Logger logger){
        logger.info("Entity Registration begin.");
        event.getRegistry().registerAll
                (
                        vampire_bat// = (EntityType<VampireBat>) EntityType.Builder.create(VampireBat::new, EntityClassification.MONSTER).size(0.5F, 0.9F).build(VampiricMod.modid + ":vampire_bat").setRegistryName(VampiricMod.location("vampire_bat"))
                );
        logger.info("Entity Registration end.");

        registerEntityWorldSpawns();
        logger.info("Spawn Configuration Complete.");
    }

    public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                vampire_bat_egg = registerEntitySpawnEgg(vampire_bat, 0x654321, 0xa9a9a9, "vampire_bat_egg")
        );
    }

    public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name) {
        // TODO: Change this to Vampiric item group
        SpawnEggItem item = new SpawnEggItem(type, color1, color1, new Item.Properties().group(ItemGroup.MISC));
        item.setRegistryName(VampiricMod.location(name));
        return item;
    }

    public static void registerEntityWorldSpawns() {
        registerEntityWorldSpawn(vampire_bat, Biomes.PLAINS, Biomes.BEACH, Biomes.JUNGLE);
    }

    public static void registerEntityWorldSpawn(EntityType<?> entity, Biome... biomes) {
        for (Biome biome : biomes) {
            if (biome != null) {
                biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, 100, 4, 4));
            }
        }
    }
}
