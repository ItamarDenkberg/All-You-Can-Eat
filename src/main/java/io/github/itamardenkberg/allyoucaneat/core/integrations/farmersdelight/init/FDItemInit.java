package io.github.itamardenkberg.allyoucaneat.core.integrations.farmersdelight.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.core.init.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.FoodValues;

public class FDItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			AllYouCanEat.MOD_ID);

	public static final RegistryObject<Item> PIZZA_SLICE = ITEMS.register("pizza_slice",
			() -> new Item(new Item.Properties().food(FoodInit.PIZZA_SLICE).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> CHOCOLATE_CAKE_SLICE = ITEMS.register("chocolate_cake_slice",
			() -> new Item(new Item.Properties().food(FoodValues.CAKE_SLICE).tab(AllYouCanEat.TAB_AYCE)));

	public static final RegistryObject<Item> STRAWBERRY_CAKE_SLICE = ITEMS.register("strawberry_cake_slice",
			() -> new Item(new Item.Properties().food(FoodValues.CAKE_SLICE).tab(AllYouCanEat.TAB_AYCE)));
}
