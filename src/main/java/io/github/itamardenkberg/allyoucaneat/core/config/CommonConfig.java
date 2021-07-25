package io.github.itamardenkberg.allyoucaneat.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public final class CommonConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ConfigValue<Integer> black_grape_seeds_drop_rate;
	public static final ConfigValue<Integer> white_grape_seeds_drop_rate;

	static {
		BUILDER.push("Black Grape");
		BUILDER.comment("Chances of seeds droping from grass");
		black_grape_seeds_drop_rate = BUILDER.comment("Black Grape Seeds: Default is 7").define("Drop Rate", 7);
		BUILDER.pop();
				
		BUILDER.push("White Grape");
		BUILDER.comment("Chances of seeds droping from grass");
		white_grape_seeds_drop_rate = BUILDER.comment("White Grape Seeds: Default is 5").define("Drop Rate", 5);
		BUILDER.pop();
		
		SPEC = BUILDER.build();
	}
}
