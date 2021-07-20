package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, AllYouCanEat.MOD_ID);

	/*
	 * public static final RegistryObject<ContainerType<WinepressContainer>>
	 * WINEPRESS_CONTAINER_TYPE = CONTAINER_TYPES .register("winepress", () ->
	 * IForgeContainerType.create(WinepressContainer::new));
	 */
}
