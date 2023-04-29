package io.github.itamardenkberg.allyoucaneat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.itamardenkberg.allyoucaneat.core.config.CommonConfig;
import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import io.github.itamardenkberg.allyoucaneat.core.init.EffectsInit;
import io.github.itamardenkberg.allyoucaneat.core.init.EntityTypesInit;
import io.github.itamardenkberg.allyoucaneat.core.init.FluidInit;
import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import io.github.itamardenkberg.allyoucaneat.core.init.TileEntitiesInit;
import io.github.itamardenkberg.allyoucaneat.core.integrations.farmersdelight.init.FDItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AllYouCanEat.MOD_ID)
public class AllYouCanEat {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "allyoucaneat";

	public AllYouCanEat() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);

		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);
		TileEntitiesInit.BLOCK_ENTITES.register(bus);
		EntityTypesInit.ENTITY_TYPES.register(bus);
		FluidInit.FLUIDS.register(bus);
		FluidInit.FLUID_TYPES.register(bus);
		EffectsInit.EFFECT.register(bus);
		if (ModList.get().isLoaded("farmersdelight")) {
			FDItemInit.ITEMS.register(bus);
		}

		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.SPEC, "ayce-common.toml");

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		ItemInit.addSeeds();

		event.enqueueWork(() -> {
			ItemInit.compstables();
		});
	}

	public static final CreativeModeTab TAB_AYCE = new CreativeModeTab("allyoucaneat") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemInit.RED_WINE_BOTTLE.get());
		}
	};
}
