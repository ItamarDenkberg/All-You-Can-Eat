package io.github.itamardenkberg.allyoucaneat.core.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.FoodProperties.Builder;

public class FoodInit {
	public static final FoodProperties BLACK_GRAPE = (new Builder()).nutrition(4).saturationMod(0.3F).build();
	public static final FoodProperties WHITE_GRAPE = (new Builder()).nutrition(4).saturationMod(0.3F).build();
	public static final FoodProperties HAZELNUT = (new Builder()).nutrition(3).saturationMod(0.3F).build();
	public static final FoodProperties MARSHMALLOW_ON_A_STICK = (new Builder()).nutrition(4).saturationMod(0.2F)
			.build();
	public static final FoodProperties ROASTED_MARSHMALLOW_ON_A_STICK = (new Builder()).nutrition(6).saturationMod(0.3F)
			.build();
	public static final FoodProperties TOMATO = (new Builder()).nutrition(4).saturationMod(0.3F).build();
	public static final FoodProperties STRAWBERRY = (new Builder()).nutrition(3).saturationMod(0.4F).fast().build();
	public static final FoodProperties SUGARED_STRAWBERRY = (new Builder()).nutrition(3).saturationMod(0.3F)
			.effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100), 1).build();
	public static final FoodProperties CHOCOLATE_BAR = (new Builder()).nutrition(5).saturationMod(0.5F).build();
	public static final FoodProperties WHITE_CHOCOLATE_BAR = (new Builder()).nutrition(5).saturationMod(0.5F).build();
	public static final FoodProperties CHOCOLATE_COVERED_STRAWBERRY = (new Builder()).nutrition(8).saturationMod(0.5F)
			.fast().build();
	public static final FoodProperties WHITE_CHOCOLATE_COVERED_STRAWBERRY = (new Builder()).nutrition(8).fast()
			.saturationMod(0.5F).build();
	public static final FoodProperties PIZZA_SLICE = (new Builder()).nutrition(5).saturationMod(0.1f).fast().build();
	public static final FoodProperties CHEESE = (new Builder()).nutrition(3).saturationMod(0.2f).fast().build();
	public static final FoodProperties CHORUS_CHOCOLATE_BAR = (new Builder()).nutrition(5).saturationMod(0.6F)
			.alwaysEat().build();
	public static final FoodProperties CHORUS_CHOCOLATE_COVERED_STRAWBERRY = (new Builder()).nutrition(8)
			.saturationMod(0.5F).fast().alwaysEat().build();
	public static final FoodProperties CHOCOLATE_BAR_WITH_NUTS = (new Builder()).nutrition(8).saturationMod(0.5F)
			.build();
	public static final FoodProperties RAISINS = (new Builder()).nutrition(5).saturationMod(0.1F).build();
	public static final FoodProperties RAISIN_COOKIE = (new Builder()).nutrition(6).saturationMod(0.4F).build();
	public static final FoodProperties CHILI_PEPPER = (new Builder()).nutrition(5).saturationMod(0.3F).alwaysEat()
			.build();

}
