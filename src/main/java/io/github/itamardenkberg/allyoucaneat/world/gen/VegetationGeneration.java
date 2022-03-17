package io.github.itamardenkberg.allyoucaneat.world.gen;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import io.github.itamardenkberg.allyoucaneat.world.features.PlacedFeaturesInit;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class VegetationGeneration {

	public static void generateBushes(final BiomeLoadingEvent event) {
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
		Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

		if (types.contains(BiomeDictionary.Type.FOREST)) {
			List<Supplier<PlacedFeature>> base = event.getGeneration()
					.getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

			base.add(() -> PlacedFeaturesInit.PATCH_STRAWBERRY_COMMON);
			base.add(() -> PlacedFeaturesInit.PATCH_STRAWBERRY_RARE);
		}
	}
}
