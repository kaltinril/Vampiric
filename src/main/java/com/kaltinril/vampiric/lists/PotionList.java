package com.kaltinril.vampiric.lists;

import com.kaltinril.vampiric.VampiricMod;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ObjectHolder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ObjectHolder(VampiricMod.modid)
public class PotionList {

    public static final Potion garlic_essence = null;
    //public static final Potion garlic_essence_short = null;
    public static final Potion garlic_essence_long = null;
    public static final Potion holy_water = null;
    public static final Potion holy_water_lvl2 = null;
    public static final Potion holy_water_long = null;

    // Using PotionBrewing which is private, requires using reflection to "use" and "inject" our recipes
    // Hopefully a better way to do this in the future
    public static Method brewing;

    @Mod.EventBusSubscriber(modid = VampiricMod.modid, bus = Bus.MOD)
    public static class Register {

        @SubscribeEvent
        public static void registerEffect(final RegistryEvent.Register<Potion> event) {
            final Potion[] potions = {
                    new Potion(new EffectInstance(EffectList.garlic_essence, 30*20*2)).setRegistryName("garlic_essence"),
                    new Potion(new EffectInstance(EffectList.garlic_essence, 30*20*4)).setRegistryName("garlic_essence_long"),
                    new Potion(new EffectInstance(EffectList.holy_burn, 30*20*2)).setRegistryName("holy_water"),
                    new Potion(new EffectInstance(EffectList.holy_burn, 30*20*4)).setRegistryName("holy_water_long"),
                    new Potion(new EffectInstance(EffectList.holy_burn, 30*20*2, 1)).setRegistryName("holy_water_lvl2"),
            };

            event.getRegistry().registerAll(potions);
        }
    }

    // Using PotionBrewing which is private, requires using reflection to "use" and "inject" our recipes
    private static void addMix(Potion base, Item ingredient, Potion result){
        if (brewing == null){
            brewing = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "func_193357_a", Potion.class, Item.class, Potion.class); //func_193357_a = addMix

            brewing.setAccessible(true); // Allow us to use it's methods

            try {
                brewing.invoke(null, base, ingredient, result);
            } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addRecipes(){
        addMix(Potions.MUNDANE,  ItemList.garlic_paste, PotionList.garlic_essence );
        addMix(PotionList.garlic_essence, Items.REDSTONE,  PotionList.garlic_essence_long);
        addMix(Potions.AWKWARD,  ItemList.holy_water_bucket, PotionList.holy_water );
        addMix(PotionList.holy_water, Items.REDSTONE,  PotionList.holy_water_long);
    }
}
