package io.github.itamardenkberg.allyoucaneat.core.util;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.client.guis.WinepressGui;
import io.github.itamardenkberg.allyoucaneat.client.render.tileentity.WinepressTileEntityRenderer;
import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import io.github.itamardenkberg.allyoucaneat.core.init.ContainerTypeInit;
import io.github.itamardenkberg.allyoucaneat.core.init.TileEntityTypeInit;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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

		ScreenManager.registerFactory(ContainerTypeInit.WINEPRESS_CONTAINER_TYPE.get(), WinepressGui::new);

		ClientRegistry.bindTileEntityRenderer(TileEntityTypeInit.WINEPRESS_TILE_ENTITY_TYPE.get(),
				WinepressTileEntityRenderer::new);
	}
}
