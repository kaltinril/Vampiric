package com.kaltinril.vampiric.core.world.biome;

import com.kaltinril.vampiric.lists.BlockList;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GenerationUtil {

    public static void generate(GenerationStage.Decoration stage, ConfiguredFeature<?, ?> feature, Biome... biomes){
        for(Biome biome : biomes){
            biome.addFeature(stage, feature);
        }
    }

    // Broke this out so that I can hopefully understand this better in the future
    public static void generateOre(OreFeatureConfig.FillerBlockType target, BlockState state, int veinSize, int chunkAmount, int bottomOffset, int topOffset, int max, Biome... biomes){
        OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(target, state, veinSize);
        ConfiguredFeature<?, ?> feature = Feature.ORE.func_225566_b_(oreFeatureConfig);
        CountRangeConfig countRangeConfig = new CountRangeConfig(chunkAmount, bottomOffset, topOffset, max);
        ConfiguredPlacement placement = Placement.COUNT_RANGE.func_227446_a_(countRangeConfig);
        feature = feature.func_227228_a_(placement);

        generate(GenerationStage.Decoration.UNDERGROUND_ORES, feature, biomes);
    }

    public static void generateOre(OreFeatureConfig.FillerBlockType target, BlockState state, int veinSize, int chunkAmount, int bottomOffset, int topOffset, int max){
        Biome[] biomes = {};
        generateOre(target, state, veinSize, chunkAmount, bottomOffset, topOffset, max, GameRegistry.findRegistry(Biome.class).getValues().toArray(biomes));
    }

    public static void addOres(){
        generateOre(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.silver_ore.getDefaultState(), 4, 4, 10, 5, 40);
    }
}
