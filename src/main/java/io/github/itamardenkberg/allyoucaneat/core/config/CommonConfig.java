package io.github.itamardenkberg.allyoucaneat.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public final class CommonConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ConfigValue<Integer> black_grape_seeds_drop_rate;
	public static final ConfigValue<Integer> white_grape_seeds_drop_rate;
	public static final ConfigValue<Integer> tomato_seeds_drop_rate;
	public static final ConfigValue<Integer> brown_wheat_seeds_drop_rate;

	static {
		BUILDER.push("Black Grape");
		BUILDER.comment("Chances of seeds droping from grass");
		BUILDER.comment("Range: 0 - 100");
		black_grape_seeds_drop_rate = BUILDER.comment("Black Grape Seeds: Default is 3").define("Drop Rate", 3);
		BUILDER.pop();

		BUILDER.push("White Grape");
		BUILDER.comment("Chances of seeds droping from grass");
		BUILDER.comment("Range: 0 - 100");
		white_grape_seeds_drop_rate = BUILDER.comment("White Grape Seeds: Default is 2").define("Drop Rate", 2);
		BUILDER.pop();

		BUILDER.push("Tomato");
		BUILDER.comment("Chances of seeds droping from grass");
		BUILDER.comment("Range: 0 - 100");
		tomato_seeds_drop_rate = BUILDER.comment("Tomato Seeds: Default is 2").define("Drop Rate", 2);
		BUILDER.pop();
		
		BUILDER.push("Brown Wheat");
		BUILDER.comment("Chances of seeds droping from grass");
		BUILDER.comment("Range: 0 - 100");
		brown_wheat_seeds_drop_rate = BUILDER.comment("Brown Wheat Seeds: Default is 2").define("Drop Rate", 2);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}
}
