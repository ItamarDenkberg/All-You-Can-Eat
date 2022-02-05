package io.github.itamardenkberg.allyoucaneat.world.features;

import java.util.List;

import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ConfiguredFeaturesInit {

	public static final ConfiguredFeature<TreeConfiguration, ?> HAZEL_TREE = FeatureUtils.register("hazel",
			Feature.TREE.configured(
					new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.HAZEL_LOG.get()),
							new StraightTrunkPlacer(4, 0, 2), BlockStateProvider.simple(BlockInit.HAZEL_LEAVES.get()),
							new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
							new TwoLayersFeatureSize(1, 0, 2)).build()));

	public static final ConfiguredFeature<RandomFeatureConfiguration, ?> HAZEL_TREE_CHECKED = FeatureUtils
			.register("hazel_feature",
					Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(
							List.of(new WeightedPlacedFeature(
									HAZEL_TREE.filteredByBlockSurvival(BlockInit.HAZEL_SAPLING.get()), 0.1f)),
							HAZEL_TREE.filteredByBlockSurvival(BlockInit.HAZEL_SAPLING.get()))));
}
