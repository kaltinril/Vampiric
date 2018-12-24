package com.twojeremys.vampiric;

import com.twojeremys.vampiric.blocks.container.RenderCoffinChest;
import com.twojeremys.vampiric.blocks.tileentity.TileEntityCoffinChest;
import com.twojeremys.vampiric.init.ModPotions;
import com.twojeremys.vampiric.init.ModRecipes;
import com.twojeremys.vampiric.proxy.CommonProxy;
import com.twojeremys.vampiric.util.Reference;
import com.twojeremys.vampiric.util.compat.OreDictionaryCompat;
import com.twojeremys.vampiric.util.handlers.RegistryHandler;
import com.twojeremys.vampiric.util.handlers.TileEntityHandler;
import com.twojeremys.vampiric.world.ModWorldGen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

    @Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public static void PreInit(FMLPreInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 13);
        ModPotions.registerPotions();

        TileEntityHandler.registerTileEntities();

        //Main.proxy.registerTileEntity(TileEntityCoffinChest.class, new RenderCoffinChest());
    }

    @EventHandler
    public static void init(FMLInitializationEvent event)
    {
        OreDictionaryCompat.registerOres();
        ModRecipes.init();
        RegistryHandler.initRegistries();
    }

    @EventHandler
    public static void PostInit(FMLPostInitializationEvent event)
    {

    }
}
