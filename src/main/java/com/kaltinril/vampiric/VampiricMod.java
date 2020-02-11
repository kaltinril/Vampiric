package com.kaltinril.vampiric;

import com.kaltinril.vampiric.client.renderer.RenderRegistry;
import com.kaltinril.vampiric.core.block.BlockCrop;
import com.kaltinril.vampiric.core.world.biome.GenerationUtil;
import com.kaltinril.vampiric.lists.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("vampiric")
public class VampiricMod
{
    public static final String modid = "vampiric";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static class Foods {
        public static final Food garlic = (new Food.Builder()).hunger(1).saturation(0.6F).build();
    }

    public VampiricMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register the doClientStuff method for modloading
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        GenerationUtil.addOres();   // Add all mod ores
        PotionList.addRecipes();    // Add all mod Potions

        LOGGER.info("HELLO FROM Pre Initialize");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    @SubscribeEvent
    //@OnlyIn(Dist.CLIENT)
    public static void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Starting Client Code.");
        // do something that can only be done on the client
        RenderTypeLookup.setRenderLayer(BlockList.garlic_plant, RenderType.func_228643_e_()); // .cutout()); / func_228643_e_

        RenderRegistry.registerEntityRenderers();

        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            // register a new block here
            LOGGER.info("Items Registered.");
            event.getRegistry().registerAll
                    (
                            // Items
                            new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(location("silver_ingot")),
                            new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(location("garlic_paste")),
                            // Tools and Weapons
                            new AxeItem(ItemTierList.SILVER, 6.5F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS)).setRegistryName(location("silver_axe")),
                            new PickaxeItem(ItemTierList.SILVER, 2, -2.8f, (new Item.Properties()).group(ItemGroup.TOOLS)).setRegistryName(location("silver_pickaxe")),
                            new HoeItem(ItemTierList.SILVER, -2.5F, (new Item.Properties()).group(ItemGroup.TOOLS)).setRegistryName(location("silver_hoe")),
                            new ShovelItem(ItemTierList.SILVER, 2F, -2.8F, (new Item.Properties()).group(ItemGroup.TOOLS)).setRegistryName("silver_shovel"),
                            new SwordItem(ItemTierList.SILVER, 3, -2.4F, (new Item.Properties()).group(ItemGroup.COMBAT)).setRegistryName("silver_sword"),
                            new SwordItem(ItemTier.WOOD, 2, -2.0F, (new Item.Properties()).group(ItemGroup.COMBAT)).setRegistryName("wooden_stake"),
                            // Armor
                            new ArmorItem(ArmorMaterialList.SILVER, EquipmentSlotType.CHEST, (new Item.Properties()).group(ItemGroup.COMBAT)).setRegistryName(location("silver_chestplate")),
                            new ArmorItem(ArmorMaterialList.SILVER, EquipmentSlotType.HEAD, (new Item.Properties()).group(ItemGroup.COMBAT)).setRegistryName(location("silver_helmet")),
                            new ArmorItem(ArmorMaterialList.SILVER, EquipmentSlotType.LEGS, (new Item.Properties()).group(ItemGroup.COMBAT)).setRegistryName(location("silver_leggings")),
                            new ArmorItem(ArmorMaterialList.SILVER, EquipmentSlotType.FEET, (new Item.Properties()).group(ItemGroup.COMBAT)).setRegistryName(location("silver_boots")),
                            // Block Items
                            new BlockItem(BlockList.silver_block, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.silver_block.getRegistryName()),
                            new BlockItem(BlockList.silver_ore, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.silver_ore.getRegistryName()),
                            new BlockItem(BlockList.garlic_plant, new Item.Properties().group(ItemGroup.MISC).food(Foods.garlic)).setRegistryName("garlic_plant")
                    );

            EntityList.registerEntitySpawnEggs(event);
        }

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            // register a new block here
            LOGGER.info("Blocks registered.");
            event.getRegistry().registerAll
                    (
                            //new ;
                            // Crops
                            new BlockCrop(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP).hardnessAndResistance(0.0F), "garlic_plant"),
                            // Blocks
                            new Block(Block.Properties.create(Material.IRON)
                                    .hardnessAndResistance(4.0f, 30.0f)
                                    .harvestLevel(1)
                                    .harvestTool(ToolType.PICKAXE)
                                    .sound(SoundType.METAL))
                                    .setRegistryName(location("silver_block")),
                            // Ore
                            new Block(Block.Properties.create(Material.ROCK)
                                    .hardnessAndResistance(4.0f, 15.0f)
                                    .harvestLevel(2)
                                    .harvestTool(ToolType.PICKAXE)
                                    .sound(SoundType.STONE))
                                    .setRegistryName(location("silver_ore"))
                    );
        }

        @SubscribeEvent
        public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
            // register a new block here
            LOGGER.info("Entities registered.");

            EntityList.registerAll(event, LOGGER);

            //EntityList.registerEntityWorldSpawns();
        }

    }

    // This appears to not be needed, not sure why harry talks added it.
    public static ResourceLocation location(String name){
        return new ResourceLocation(modid, name);
    }
}
