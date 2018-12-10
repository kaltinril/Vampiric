package com.twojeremys.vampiric.util.compat;

import com.twojeremys.vampiric.init.ModBlocks;
import com.twojeremys.vampiric.init.ModItems;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryCompat {

    public static void registerOres(){
        OreDictionary.registerOre("oreSilver", ModBlocks.SILVER_ORE);
        OreDictionary.registerOre("ingotSilver", ModItems.SILVER_INGOT);
        OreDictionary.registerOre("cropGarlic", ModItems.GARLIC);

    }
}
