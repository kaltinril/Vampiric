package com.twojeremys.vampiric.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SilverOre extends BlockBase{

    public SilverOre(String name, Material material) {
        super(name, material);

        setSoundType(SoundType.STONE);                  // Silver Ore should sound like stone
        setHardness(4.0f);                              // Harder than gold [3], but softer than iron [5]
        setResistance(15.0f);                           // Matches iron/gold/emerald/coal
        setHarvestLevel("pickaxe", 2);   // Requires Iron pickaxe

    }
}
