package com.twojeremys.vampiric.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SilverBlock extends BlockBase {

    public SilverBlock(String name, Material material) {
        super(name, material);

        //https://www.youtube.com/watch?v=UzKHGCYMpy4

        setSoundType(SoundType.METAL);                  // Silver is a metal

        //http://minecraftmodcustomstuff.wikia.com/wiki/Hardness
        setHardness(4.0f);                              // Harder than gold [3], but softer than iron [5]

        //https://minecraft.gamepedia.com/Explosion
        setResistance(30.0f);                           // Matches iron/gold/emerald/coal

        //http://minecraftmodcustomstuff.wikia.com/wiki/HarvestLevel
        setHarvestLevel("pickaxe", 1);   // Seems legit

        //https://minecraft.gamepedia.com/Light
        //setLightLevel(1.0f);
        //setLightOpacity(1); //1 = all light passes through
    }
}
