package io.github.itamardenkberg.allyoucaneat.core.init;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BlockStatePropertiesInit {
	public static final IntegerProperty LEVEL_0_3 = IntegerProperty.create("level", 0, 3);
	public static final IntegerProperty AGE_0_4 = IntegerProperty.create("age", 0, 4);
	public static final IntegerProperty AGE_0_3 = IntegerProperty.create("age", 0, 3);
	public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 3);
}
