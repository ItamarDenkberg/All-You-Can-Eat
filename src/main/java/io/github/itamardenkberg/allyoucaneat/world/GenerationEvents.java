package io.github.itamardenkberg.allyoucaneat.world;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.world.gen.TreeGeneration;
import io.github.itamardenkberg.allyoucaneat.world.gen.VegetationGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AllYouCanEat.MOD_ID)
public class GenerationEvents {

	@SubscribeEvent
	public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
		TreeGeneration.generateTrees(event);
		VegetationGeneration.generateBushes(event);
	}
}
