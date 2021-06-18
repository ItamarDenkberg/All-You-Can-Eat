package io.github.itamardenkberg.allyoucaneat.core.util;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AllYouCanEat.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(BlockInit.WINE_BOTTLE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BlockInit.RED_WINE_BOTTLE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BlockInit.WHITE_WINE_BOTTLE.get(), RenderType.getCutoutMipped());
	}
}
