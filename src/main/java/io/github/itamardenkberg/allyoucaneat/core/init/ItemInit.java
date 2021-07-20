package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.items.WineGlassItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			AllYouCanEat.MOD_ID);

	public static final RegistryObject<Item> WINE_GLASS = ITEMS.register("wine_glass",
			() -> new Item(new Item.Properties().maxStackSize(16).group(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> RED_WINE_GLASS = ITEMS.register("red_wine_glass",
			() -> new WineGlassItem(new Item.Properties().maxStackSize(1).group(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> WHITE_WINE_GLASS = ITEMS.register("white_wine_glass",
			() -> new WineGlassItem(new Item.Properties().maxStackSize(1).group(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> BLACK_GRAPE = ITEMS.register("black_grape",
			() -> new Item(new Item.Properties().food(FoodInit.BLACK_GRAPE).group(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> WHITE_GRAPE = ITEMS.register("white_grape",
			() -> new Item(new Item.Properties().food(FoodInit.WHITE_GRAPE).group(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> BLACK_GRAPE_SEEDS = ITEMS.register("black_grape_seeds",
			() -> new BlockNamedItem(BlockInit.BLACK_GRAPE_CROP.get(),
					new Item.Properties().group(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> WHITE_GRAPE_SEEDS = ITEMS.register("white_grape_seeds",
			() -> new BlockNamedItem(BlockInit.WHITE_GRAPE_CROP.get(),
					new Item.Properties().group(AllYouCanEat.TAB_AYCE)));

	// Blocks
//	public static final RegistryObject<BlockItem> MOLTEN_GLASS = ITEMS.register("molten_glass",
//			() -> new BlockItem(BlockInit.MOLTEN_GLASS.get(), new Item.Properties().group(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> WINE_BOTTLE = ITEMS.register("wine_bottle",
			() -> new BlockItem(BlockInit.WINE_BOTTLE.get(), new Item.Properties().group(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> RED_WINE_BOTTLE = ITEMS.register("red_wine_bottle",
			() -> new BlockItem(BlockInit.RED_WINE_BOTTLE.get(), new Item.Properties().group(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<BlockItem> WHITE_WINE_BOTTLE = ITEMS.register("white_wine_bottle",
			() -> new BlockItem(BlockInit.WHITE_WINE_BOTTLE.get(), new Item.Properties().group(AllYouCanEat.TAB_AYCE)));

//	public static final RegistryObject<BlockItem> WINEPRESS = ITEMS.register("winepress",
//			() -> new BlockItem(BlockInit.WINEPRESS.get(), new Item.Properties().group(AllYouCanEat.TAB_AYCE)));
}
