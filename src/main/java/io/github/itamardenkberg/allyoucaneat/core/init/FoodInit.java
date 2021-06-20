package io.github.itamardenkberg.allyoucaneat.core.init;

import net.minecraft.item.Food;
import net.minecraft.item.Food.Builder;

public class FoodInit {
	public static final Food BLACK_GRAPE = (new Builder()).hunger(4).saturation(0.3F).build();
	public static final Food WHITE_GRAPE = (new Builder()).hunger(4).saturation(0.3F).build();

}
