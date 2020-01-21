package com.kaltinril.vampiric;

import com.kaltinril.vampiric.lists.BlockList;
import com.kaltinril.vampiric.lists.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("vampiric")
public class VampiricMod
{
    public static final String modid = "vampiric";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public VampiricMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM Pre Initialize");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
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
                        ItemList.silver_ingot = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("silver_ingot")),
                        // Block Items
                        ItemList.silver_block = new BlockItem(BlockList.silver_block, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.silver_block.getRegistryName()),
                        ItemList.silver_ore = new BlockItem(BlockList.silver_ore, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.silver_ore.getRegistryName())
                );
        }

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            // register a new block here
            LOGGER.info("Blocks registered.");
            event.getRegistry().registerAll
                (
                        BlockList.silver_block = new Block(Block.Properties.create(Material.IRON)
                                .hardnessAndResistance(4.0f, 30.0f)
                                .harvestLevel(1)
                                .harvestTool(ToolType.PICKAXE)
                                .sound(SoundType.METAL))
                                .setRegistryName(location("silver_block")),
                        BlockList.silver_ore = new Block(Block.Properties.create(Material.ROCK)
                                .hardnessAndResistance(4.0f, 15.0f)
                                .harvestLevel(2)
                                .harvestTool(ToolType.PICKAXE)
                                .sound(SoundType.STONE))
                                .setRegistryName(location("silver_ore"))
                );
        }
    }

    private static ResourceLocation location(String name){
        return new ResourceLocation(modid, name);
    }
}
