package io.github.itamardenkberg.allyoucaneat.world.features;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class PlacedFeaturesInit {

	public static final Holder<PlacedFeature> HAZEL = PlacementUtils.register("hazel",
			ConfiguredFeaturesInit.HAZEL_TREE_SPAWN,
			VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1)));

	public static final Holder<PlacedFeature> PATCH_STRAWBERRY_COMMON = PlacementUtils.register(
			"patch_strawberry_common", ConfiguredFeaturesInit.PATCH_STRAWBERRY_BUSH,
			RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
			BiomeFilter.biome());

	public static final Holder<PlacedFeature> PATCH_STRAWBERRY_RARE = PlacementUtils
			.register("patch_strawberry_rare",
			ConfiguredFeaturesInit.PATCH_STRAWBERRY_BUSH, RarityFilter.onAverageOnceEvery(384),
			InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

}
