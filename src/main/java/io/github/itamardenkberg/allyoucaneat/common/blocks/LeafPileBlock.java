package io.github.itamardenkberg.allyoucaneat.common.blocks;

import java.util.List;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.RegistryObject;

public class LeafPileBlock extends com.ordana.immersive_weathering.common.blocks.LeafPileBlock {

	public LeafPileBlock(Properties settings, boolean hasFlowers, boolean hasThorns,
			List<RegistryObject<SimpleParticleType>> particles) {
		super(settings, hasFlowers, hasThorns, particles);
	}

}
