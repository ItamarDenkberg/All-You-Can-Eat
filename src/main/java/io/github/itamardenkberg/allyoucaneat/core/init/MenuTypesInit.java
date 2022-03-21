package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.client.guis.GlassFurnaceMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypesInit {
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
			AllYouCanEat.MOD_ID);

	public static final RegistryObject<MenuType<GlassFurnaceMenu>> GLASS_FURNACE_MENU = registerMenuType(
			GlassFurnaceMenu::new, "glass_furnace_menu");

	private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(
			IContainerFactory<T> factory, String name) {
		return MENUS.register(name, () -> IForgeMenuType.create(factory));
	}
}
