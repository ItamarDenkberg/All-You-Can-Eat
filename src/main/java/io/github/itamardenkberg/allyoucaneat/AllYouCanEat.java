package io.github.itamardenkberg.allyoucaneat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import io.github.itamardenkberg.allyoucaneat.core.init.ContainerTypeInit;
import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import io.github.itamardenkberg.allyoucaneat.core.init.RecipeInit;
import io.github.itamardenkberg.allyoucaneat.core.init.TileEntityTypeInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AllYouCanEat.MOD_ID)
public class AllYouCanEat {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "allyoucaneat";

	public AllYouCanEat() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);
		bus.addGenericListener(IRecipeSerializer.class, RecipeInit::registerRecipes);
		
		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);
		TileEntityTypeInit.TILE_ENTITY_TYPE.register(bus);
		ContainerTypeInit.CONTAINER_TYPES.register(bus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {

	}

	public static final ItemGroup TAB_AYCE = new ItemGroup("allyoucaneat") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.RED_WINE_BOTTLE.get());
		}
	};
}
