package io.github.itamardenkberg.allyoucaneat.core.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.FoodProperties.Builder;

public class FoodInit {
	public static final FoodProperties BLACK_GRAPE = (new Builder()).nutrition(4).saturationMod(0.3F).build();
	public static final FoodProperties WHITE_GRAPE = (new Builder()).nutrition(4).saturationMod(0.3F).build();
	public static final FoodProperties HAZELNUT = (new Builder()).nutrition(3).saturationMod(0.3F).build();
	public static final FoodProperties MARSHMALLOW_ON_A_STICK = (new Builder()).nutrition(3).saturationMod(0.2F)
			.build();
	public static final FoodProperties ROASTED_MARSHMALLOW_ON_A_STICK = (new Builder()).nutrition(4).saturationMod(0.3F)
			.build();
	public static final FoodProperties TOMATO = (new Builder()).nutrition(4).saturationMod(0.3F).build();
	public static final FoodProperties STRAWBERRY = (new Builder()).nutrition(3).saturationMod(0.4F).build();
	public static final FoodProperties SUGARED_STRAWBERRY = (new Builder()).nutrition(3).saturationMod(0.3F)
			.effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100), 1).build();

}
