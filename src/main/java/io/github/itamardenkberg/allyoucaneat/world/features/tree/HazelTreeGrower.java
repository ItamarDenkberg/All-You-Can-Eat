package io.github.itamardenkberg.allyoucaneat.world.features.tree;

import java.util.Random;

import io.github.itamardenkberg.allyoucaneat.world.features.ConfiguredFeaturesInit;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class HazelTreeGrower extends AbstractTreeGrower {

	@Override
	protected ConfiguredFeature<?, ?> getConfiguredFeature(Random random, boolean largeHive) {
		return ConfiguredFeaturesInit.HAZEL_TREE;
	}
}
