package com.kaltinril.vampiric.lists;

import com.kaltinril.vampiric.VampiricMod;
import com.kaltinril.vampiric.core.fluid.FluidHolyWater;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(VampiricMod.modid)
public class FluidList {

    public static final FluidHolyWater.Flowing flowing_holy_water = null;
    public static final FluidHolyWater.Source holy_water = null;

    public static class Tags {
        public static final Tag<Fluid> HOLY_WATER = new FluidTags.Wrapper(new ResourceLocation(VampiricMod.modid, "holy_water"));
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class Register {

        @SubscribeEvent
        public static void registerFluids(final RegistryEvent.Register<Fluid> event){
            final Fluid[] fluids = {
                    new FluidHolyWater.Flowing().setRegistryName(VampiricMod.modid, "flowing_holy_water"),
                    new FluidHolyWater.Source().setRegistryName(VampiricMod.modid, "holy_water")
            };

            event.getRegistry().registerAll(fluids);
        }
    }
}
