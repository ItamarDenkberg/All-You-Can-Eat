package io.github.itamardenkberg.allyoucaneat.world.features.tree;

import javax.annotation.Nullable;

import io.github.itamardenkberg.allyoucaneat.world.features.ConfiguredFeaturesInit;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class HazelTreeGrower extends AbstractTreeGrower {

	@Nullable
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom,
			boolean pHasFlowers) {
		return ConfiguredFeaturesInit.HAZEL.getHolder().get();
	}
}
