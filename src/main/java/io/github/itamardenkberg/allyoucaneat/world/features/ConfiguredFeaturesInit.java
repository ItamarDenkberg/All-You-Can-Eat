package io.github.itamardenkberg.allyoucaneat.world.features;

import java.util.List;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ConfiguredFeaturesInit {
	public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister
			.create(Registry.CONFIGURED_FEATURE_REGISTRY, AllYouCanEat.MOD_ID);

//	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> HAZEL_TREE = FeatureUtils.register("hazel",
//			Feature.TREE,
//			new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.HAZEL_LOG.get()),
//					new StraightTrunkPlacer(4, 0, 2), BlockStateProvider.simple(BlockInit.HAZEL_LEAVES.get()),
//					new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 0, 2))
//					.build());
//
//	public static final Holder<PlacedFeature> HAZEL_TREE_CHECKED = PlacementUtils.register("hazel_checked", HAZEL_TREE,
//			PlacementUtils.filteredByBlockSurvival(BlockInit.HAZEL_SAPLING.get()));
//
//	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> HAZEL_TREE_SPAWN = FeatureUtils
//			.register("hazel_spawn", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
//					List.of(new WeightedPlacedFeature(HAZEL_TREE_CHECKED, 0.5F)), HAZEL_TREE_CHECKED));
//
//	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_STRAWBERRY_BUSH = FeatureUtils
//			.register("patch_strawberry_bush", Feature.RANDOM_PATCH,
//					FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
//							new SimpleBlockConfiguration(BlockStateProvider.simple(BlockInit.STRAWBERRY_BUSH.get()
//									.defaultBlockState().setValue(SweetBerryBushBlock.AGE, Integer.valueOf(3)))),
//							List.of(Blocks.GRASS_BLOCK)));

	public static final RegistryObject<ConfiguredFeature<?, ?>> HAZEL = CONFIGURED_FEATURES.register("hazel",
			() -> new ConfiguredFeature<>(Feature.TREE,
					new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.HAZEL_LOG.get()),
							new StraightTrunkPlacer(4, 0, 2), BlockStateProvider.simple(BlockInit.HAZEL_LEAVES.get()),
							new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
							new TwoLayersFeatureSize(1, 0, 2)).build()));

	public static final RegistryObject<ConfiguredFeature<?, ?>> HAZEL_SPAWN = CONFIGURED_FEATURES.register(
			"hazel_spawn",
			() -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
					new RandomFeatureConfiguration(List
							.of(new WeightedPlacedFeature(PlacedFeaturesInit.HAZEL_CHECKED.getHolder().get(), 0.5F)),
							PlacedFeaturesInit.HAZEL_CHECKED.getHolder().get())));

	public static final RegistryObject<ConfiguredFeature<?, ?>> SRAWBERRY_BUSH = CONFIGURED_FEATURES.register("strawberry_bush",
            () -> new ConfiguredFeature<>(Feature.FLOWER,
                    new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(BlockInit.STRAWBERRY_BUSH.get()
									.defaultBlockState().setValue(SweetBerryBushBlock.AGE, Integer.valueOf(3))))))));

	public static void register(IEventBus eventBus) {
		CONFIGURED_FEATURES.register(eventBus);
	}

}
