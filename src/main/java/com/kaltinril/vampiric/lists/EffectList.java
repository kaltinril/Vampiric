package com.kaltinril.vampiric.lists;


import com.kaltinril.vampiric.VampiricMod;
import com.kaltinril.vampiric.core.potion.ModEffect;
import com.kaltinril.vampiric.core.util.helpers.Constants;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(VampiricMod.modid)
public class EffectList {

    public static final ModEffect garlic_essence = null;
    public static final ModEffect holy_burn = null;

    @Mod.EventBusSubscriber(modid = VampiricMod.modid, bus = Bus.MOD)
    public static class Register {

        @SubscribeEvent
        public static void registerEffect(final RegistryEvent.Register<Effect> event) {
            final Effect[] effects = {
                    new ModEffect(EffectType.HARMFUL, 0xf2e9d2, 1.0f).setRegistryName("garlic_essence"), // whiteish
                    new ModEffect(EffectType.HARMFUL, Constants.Color.WHITE.getIntValue(), 1.0f).setRegistryName("holy_burn")
            };

            event.getRegistry().registerAll(effects);
        }
    }
}
