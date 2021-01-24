package dev.willthomas.desertsurvival;

import net.minecraft.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
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
    // Registered items - copied from similar items in `Items.java`
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
        LOGGER.info("Hello from DesertSurvival!");

        // Register the items registry
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
