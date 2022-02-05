package io.github.itamardenkberg.allyoucaneat.world.features;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeaturesInit {
	public static final PlacedFeature HAZEL = PlacementUtils.register("hazel",
			ConfiguredFeaturesInit.HAZEL_TREE
					.placed(VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1))));
}
