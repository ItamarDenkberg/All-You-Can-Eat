package io.github.itamardenkberg.allyoucaneat.core.integrations.immersive_weathering.init;

import com.ordana.immersive_weathering.common.ModParticles;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IWParticleInit {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister
			.create(ForgeRegistries.PARTICLE_TYPES, AllYouCanEat.MOD_ID);

	public static void init() {
		if (ModList.get().isLoaded("immersive_weathering")) {
			HAZEL_LEAF = ModParticles.regParticle("hazel_leaf");
		}
	}

	public static RegistryObject<SimpleParticleType> HAZEL_LEAF;

}
