package io.github.itamardenkberg.allyoucaneat.core.init;

import java.util.ArrayList;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.entities.vehicle.BoatEntity;
import io.github.itamardenkberg.allyoucaneat.common.items.BoatItem;
import io.github.itamardenkberg.allyoucaneat.common.items.MarshmallowItem;
import io.github.itamardenkberg.allyoucaneat.common.items.WineGlassItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			AllYouCanEat.MOD_ID);
	public static ArrayList<Item> seeds = new ArrayList<>();

	public static final RegistryObject<Item> WINE_GLASS = ITEMS.register("wine_glass",
			() -> new Item(new Item.Properties().stacksTo(16).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> RED_WINE_GLASS = ITEMS.register("red_wine_glass",
			() -> new WineGlassItem(new Item.Properties().stacksTo(1).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> WHITE_WINE_GLASS = ITEMS.register("white_wine_glass",
			() -> new WineGlassItem(new Item.Properties().stacksTo(1).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> BLACK_GRAPE = ITEMS.register("black_grape",
			() -> new Item(new Item.Properties().food(FoodInit.BLACK_GRAPE).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> WHITE_GRAPE = ITEMS.register("white_grape",
			() -> new Item(new Item.Properties().food(FoodInit.WHITE_GRAPE).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> BLACK_GRAPE_SEEDS = ITEMS.register("black_grape_seeds",
			() -> new ItemNameBlockItem(BlockInit.BLACK_GRAPE_CROP.get(),
					new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> WHITE_GRAPE_SEEDS = ITEMS.register("white_grape_seeds",
			() -> new ItemNameBlockItem(BlockInit.WHITE_GRAPE_CROP.get(),
					new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> HAZELNUT = ITEMS.register("hazelnut",
			() -> new Item(new Item.Properties().food(FoodInit.HAZELNUT).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> GELATIN = ITEMS.register("gelatin",
			() -> new Item(new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> MARSHMALLOW_ON_A_STICK = ITEMS.register("marshmallow_on_a_stick",
			() -> new MarshmallowItem(
					new Item.Properties().food(FoodInit.MARSHMALLOW_ON_A_STICK).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> ROASTED_MARSHMALLOW_ON_A_STICK = ITEMS
			.register("roasted_marshmallow_on_a_stick", () -> new MarshmallowItem(
					new Item.Properties().food(FoodInit.ROASTED_MARSHMALLOW_ON_A_STICK).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> HAZEL_SIGN_ITEM = ITEMS.register("hazel_sign",
			() -> new SignItem(new Item.Properties().stacksTo(16).tab(AllYouCanEat.TAB_AYCE),
					BlockInit.HAZEL_SIGN.get(), BlockInit.HAZEL_WALL_SIGN.get()));

	public static final RegistryObject<Item> HAZEL_BOAT = ITEMS.register("hazel_boat",
			() -> new BoatItem(new Item.Properties().stacksTo(16).tab(AllYouCanEat.TAB_AYCE), BoatEntity.Type.HAZEL));

	public static final RegistryObject<Item> RED_WINE_BUCKET = ITEMS.register("red_wine_bucket",
			() -> new BucketItem(FluidInit.RED_WINE_FLUID,
					new Item.Properties().stacksTo(1).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> WHITE_WINE_BUCKET = ITEMS.register("white_wine_bucket",
			() -> new BucketItem(FluidInit.WHITE_WINE_FLUID,
					new Item.Properties().stacksTo(1).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato",
			() -> new Item(new Item.Properties().food(FoodInit.TOMATO).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds",
			() -> new ItemNameBlockItem(BlockInit.TOMATO_CROP.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
			() -> new ItemNameBlockItem(BlockInit.STRAWBERRY_BUSH.get(),
					new Item.Properties().food(FoodInit.STRAWBERRY).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> SUGARED_STRAWBERRY = ITEMS.register("sugared_strawberry",
			() -> new Item(new Item.Properties().food(FoodInit.SUGARED_STRAWBERRY).tab(AllYouCanEat.TAB_AYCE)));

	// Blocks

	public static final RegistryObject<BlockItem> WINE_BOTTLE = ITEMS.register("wine_bottle",
			() -> new BlockItem(BlockInit.WINE_BOTTLE.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> RED_WINE_BOTTLE = ITEMS.register("red_wine_bottle",
			() -> new BlockItem(BlockInit.RED_WINE_BOTTLE.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> WHITE_WINE_BOTTLE = ITEMS.register("white_wine_bottle",
			() -> new BlockItem(BlockInit.WHITE_WINE_BOTTLE.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_LOG = ITEMS.register("hazel_log",
			() -> new BlockItem(BlockInit.HAZEL_LOG.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> STRIPPED_HAZEL_LOG = ITEMS.register("stripped_hazel_log",
			() -> new BlockItem(BlockInit.STRIPPED_HAZEL_LOG.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_WOOD = ITEMS.register("hazel_wood",
			() -> new BlockItem(BlockInit.HAZEL_WOOD.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> STRIPPED_HAZEL_WOOD = ITEMS.register("stripped_hazel_wood",
			() -> new BlockItem(BlockInit.STRIPPED_HAZEL_WOOD.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_PLANKS = ITEMS.register("hazel_planks",
			() -> new BlockItem(BlockInit.HAZEL_PLANKS.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_SLAB = ITEMS.register("hazel_slab",
			() -> new BlockItem(BlockInit.HAZEL_SLAB.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_STAIRS = ITEMS.register("hazel_stairs",
			() -> new BlockItem(BlockInit.HAZEL_STAIRS.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_LEAVES = ITEMS.register("hazel_leaves",
			() -> new BlockItem(BlockInit.HAZEL_LEAVES.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_SAPLING = ITEMS.register("hazel_sapling",
			() -> new BlockItem(BlockInit.HAZEL_SAPLING.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_BUTTON = ITEMS.register("hazel_button",
			() -> new BlockItem(BlockInit.HAZEL_BUTTON.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_PRESSURE_PLATE = ITEMS.register("hazel_pressure_plate",
			() -> new BlockItem(BlockInit.HAZEL_PRESSURE_PLATE.get(),
					new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_FENCE = ITEMS.register("hazel_fence",
			() -> new BlockItem(BlockInit.HAZEL_FENCE.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_FENCE_GATE = ITEMS.register("hazel_fence_gate",
			() -> new BlockItem(BlockInit.HAZEL_FENCE_GATE.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_DOOR = ITEMS.register("hazel_door",
			() -> new BlockItem(BlockInit.HAZEL_DOOR.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> HAZEL_TRAPDOOR = ITEMS.register("hazel_trapdoor",
			() -> new BlockItem(BlockInit.HAZEL_TRAPDOOR.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> CHOCOLATE_CAKE = ITEMS.register("chocolate_cake",
			() -> new BlockItem(BlockInit.CHOCOLATE_CAKE.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> STRAWBERRY_CAKE = ITEMS.register("strawberry_cake",
			() -> new BlockItem(BlockInit.STRAWBERRY_CAKE.get(), new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

	// Other

	public static void addSeeds() {
		seeds.add(BLACK_GRAPE_SEEDS.get());
		seeds.add(WHITE_GRAPE_SEEDS.get());
		seeds.add(TOMATO_SEEDS.get());
	}

	public static void compstables() {
		registerCompostable(0.3f, BLACK_GRAPE_SEEDS.get());
		registerCompostable(0.3f, WHITE_GRAPE_SEEDS.get());
		registerCompostable(0.3f, TOMATO_SEEDS.get());
		registerCompostable(0.3f, BLACK_GRAPE.get());
		registerCompostable(0.3f, WHITE_GRAPE.get());
		registerCompostable(0.3f, HAZELNUT.get());
		registerCompostable(0.3f, HAZEL_LEAVES.get());
		registerCompostable(0.3f, TOMATO.get());
		registerCompostable(0.3f, STRAWBERRY.get());
	}

	private static void registerCompostable(float chance, ItemLike itemProvider) {
		ComposterBlock.COMPOSTABLES.put(itemProvider.asItem(), chance);
	}
}