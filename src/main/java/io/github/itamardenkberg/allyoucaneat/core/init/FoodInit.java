package io.github.itamardenkberg.allyoucaneat.core.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.FoodProperties.Builder;

public class FoodInit {
	public static final FoodProperties BLACK_GRAPE = (new Builder()).nutrition(4).saturationMod(0.3F).build();
	public static final FoodProperties WHITE_GRAPE = (new Builder()).nutrition(4).saturationMod(0.3F).build();

}
