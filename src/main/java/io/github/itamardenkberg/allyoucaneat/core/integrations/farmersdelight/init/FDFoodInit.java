package io.github.itamardenkberg.allyoucaneat.core.integrations.farmersdelight.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.FoodProperties.Builder;

public class FDFoodInit {
	public static final FoodProperties PIZZA_SLICE = (new Builder()).nutrition(4).saturationMod(0.3F).build();

}
