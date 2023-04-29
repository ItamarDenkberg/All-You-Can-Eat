package io.github.itamardenkberg.allyoucaneat.world.features;

import java.util.List;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PlacedFeaturesInit {
	public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister
			.create(Registry.PLACED_FEATURE_REGISTRY, AllYouCanEat.MOD_ID);

//	public static final Holder<PlacedFeature> HAZEL = PlacementUtils.register("hazel",
//			ConfiguredFeaturesInit.HAZEL_TREE_SPAWN,
//			VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1)));
//
//	public static final Holder<PlacedFeature> PATCH_STRAWBERRY_COMMON = PlacementUtils.register(
//			"patch_strawberry_common", ConfiguredFeaturesInit.PATCH_STRAWBERRY_BUSH,
//			RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
//			BiomeFilter.biome());
//
//	public static final Holder<PlacedFeature> PATCH_STRAWBERRY_RARE = PlacementUtils.register("patch_strawberry_rare",
//			ConfiguredFeaturesInit.PATCH_STRAWBERRY_BUSH, RarityFilter.onAverageOnceEvery(384),
//			InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> HAZEL_CHECKED = PLACED_FEATURES.register("red_maple_checked",
			() -> new PlacedFeature(ConfiguredFeaturesInit.HAZEL.getHolder().get(),
					List.of(PlacementUtils.filteredByBlockSurvival(BlockInit.HAZEL_SAPLING.get()))));

	public static final RegistryObject<PlacedFeature> HAZEL_PLACED = PLACED_FEATURES.register("red_maple_placed",
			() -> new PlacedFeature(ConfiguredFeaturesInit.HAZEL_SPAWN.getHolder().get(),
					VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 2))));

	public static final RegistryObject<PlacedFeature> PATCH_STRAWBERRY_BUSH_COMMON = PLACED_FEATURES.register(
			"patch_strawberry_bush_common",
			() -> new PlacedFeature(ConfiguredFeaturesInit.SRAWBERRY_BUSH.getHolder().get(),
					List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP,
							BiomeFilter.biome())));

	public static final RegistryObject<PlacedFeature> PATCH_STRAWBERRY_BUSH_RARE = PLACED_FEATURES.register(
			"patch_strawberry_bush_rare",
			() -> new PlacedFeature(ConfiguredFeaturesInit.SRAWBERRY_BUSH.getHolder().get(),
					List.of(RarityFilter.onAverageOnceEvery(384), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP,
							BiomeFilter.biome())));
}
