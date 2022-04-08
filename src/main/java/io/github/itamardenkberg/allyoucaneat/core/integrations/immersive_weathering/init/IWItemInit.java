package io.github.itamardenkberg.allyoucaneat.core.integrations.immersive_weathering.init;

import com.ordana.immersive_weathering.common.items.BurnableItem;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.items.LeafPileBlockItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IWItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			AllYouCanEat.MOD_ID);

	public static void init() {
		if (ModList.get().isLoaded("immersive_weathering")) {
			HAZEL_LEAF_PILE = ITEMS.register("hazel_leaf_pile",
					() -> new LeafPileBlockItem(IWBlockInit.HAZEL_LEAF_PILE.get(),
							new Item.Properties().tab(AllYouCanEat.TAB_AYCE)));

			HAZEL_BARK = ITEMS.register("hazel_bark",
					() -> new BurnableItem(new Item.Properties().tab(AllYouCanEat.TAB_AYCE), 200));
		}
	}

	public static RegistryObject<BlockItem> HAZEL_LEAF_PILE;
	public static RegistryObject<Item> HAZEL_BARK;
}
