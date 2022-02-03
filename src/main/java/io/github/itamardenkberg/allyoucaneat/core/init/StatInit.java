package io.github.itamardenkberg.allyoucaneat.core.init;

import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public class StatInit {
	public static final ResourceLocation USE_WINE_BOTTLE = registerCustom("use_wine_bottle", StatFormatter.DEFAULT);
	public static final ResourceLocation FILL_WINE_BOTTLE = registerCustom("fill_wine_bottle", StatFormatter.DEFAULT);

	public static final ResourceLocation INTERACT_WITH_WINEPRESS = registerCustom("interact_with_winepress",
			StatFormatter.DEFAULT);

	private static ResourceLocation registerCustom(String string, StatFormatter formatter) {
		ResourceLocation location = new ResourceLocation(string);
		Registry.register(Registry.CUSTOM_STAT, string, location);
		Stats.CUSTOM.get(location, formatter);
		return location;
	}
}
