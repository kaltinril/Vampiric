package com.twojeremys.vampiric;

import com.twojeremys.vampiric.init.ModItems;
import com.twojeremys.vampiric.init.ModPotions;
import com.twojeremys.vampiric.init.ModRecipes;
import com.twojeremys.vampiric.proxy.CommonProxy;
import com.twojeremys.vampiric.util.Reference;

import com.twojeremys.vampiric.world.ModWorldGen;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

    @Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public static void PreInit(FMLPreInitializationEvent event)
    {
        OreDictionary.registerOre("cutter", new ItemStack(ModItems.CUTTER, 1, OreDictionary.WILDCARD_VALUE));
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 13);
        ModPotions.registerPotions();

    }

    @EventHandler
    public static void init(FMLInitializationEvent event)
    {
        ModRecipes.init();
    }

    @EventHandler
    public static void PostInit(FMLPostInitializationEvent event)
    {

    }
}
