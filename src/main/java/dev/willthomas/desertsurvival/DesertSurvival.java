package dev.willthomas.desertsurvival;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("desertsurvival")
public class DesertSurvival {
    private static final String MODID = "desertsurvival";

    // Items registry
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Registered items
    public static final RegistryObject<Item> CACTUS_SLICE = ITEMS.register(
        "cactus_slice",
        () -> new Item((new Item.Properties()).group(ItemGroup.FOOD).food(Foods.MELON_SLICE))
    );
    public static final RegistryObject<Item> SANDSTONE_AXE = ITEMS.register(
        "sandstone_axe",
        () -> new AxeItem(ItemTier.STONE, 7.0F, -3.2F, new Item.Properties().group(ItemGroup.TOOLS))
    );
    public static final RegistryObject<Item> SANDSTONE_HOE = ITEMS.register(
        "sandstone_hoe",
        () -> new HoeItem(ItemTier.STONE, -1, -2.0F, (new Item.Properties()).group(ItemGroup.TOOLS))
    );
    public static final RegistryObject<Item> SANDSTONE_PICKAXE = ITEMS.register(
        "sandstone_pickaxe",
        () -> new PickaxeItem(ItemTier.STONE, 1, -2.8F, (new Item.Properties()).group(ItemGroup.TOOLS))
    );
    public static final RegistryObject<Item> SANDSTONE_SHOVEL = ITEMS.register(
        "sandstone_shovel",
        () -> new ShovelItem(ItemTier.STONE, 1.5F, -3.0F, (new Item.Properties()).group(ItemGroup.TOOLS))
    );
    public static final RegistryObject<Item> SANDSTONE_SWORD = ITEMS.register(
        "sandstone_sword",
        () -> new SwordItem(ItemTier.STONE, 3, -2.4F, (new Item.Properties()).group(ItemGroup.COMBAT))
    );

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public DesertSurvival() {
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

        // Register the items registry
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM SETUP");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("desertsurvival", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event) {
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
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
