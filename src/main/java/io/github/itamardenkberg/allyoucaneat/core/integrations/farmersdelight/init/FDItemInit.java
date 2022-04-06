package io.github.itamardenkberg.allyoucaneat.core.integrations.farmersdelight.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FDItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			AllYouCanEat.MOD_ID);

	public static void init() {
		if (ModList.get().isLoaded("farmersdelight")) {
			PIZZA_SLICE = ITEMS.register("pizza_slice",
					() -> new Item(new Item.Properties().food(FDFoodInit.PIZZA_SLICE).tab(AllYouCanEat.TAB_AYCE)));
		}
	}

	public static RegistryObject<Item> PIZZA_SLICE;
}
