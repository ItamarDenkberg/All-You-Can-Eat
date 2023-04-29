package io.github.itamardenkberg.allyoucaneat.core.util;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.client.render.entity.BoatEntityRenderer;
import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import io.github.itamardenkberg.allyoucaneat.core.init.EntityTypesInit;
import io.github.itamardenkberg.allyoucaneat.core.init.FluidInit;
import io.github.itamardenkberg.allyoucaneat.core.init.TileEntitiesInit;
import io.github.itamardenkberg.allyoucaneat.core.init.WoodTypesInit;
import io.github.itamardenkberg.allyoucaneat.world.FoliageColor;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AllYouCanEat.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(FluidInit.RED_WINE_FLUID_BLOCK.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(FluidInit.RED_WINE_FLUID.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(FluidInit.RED_WINE_FLOWING.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(FluidInit.WHITE_WINE_FLUID_BLOCK.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(FluidInit.WHITE_WINE_FLUID.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(FluidInit.WHITE_WINE_FLOWING.get(), RenderType.translucent());

		EntityRenderers.register(EntityTypesInit.BOAT_ENTITY.get(), BoatEntityRenderer::new);

		WoodType.register(WoodTypesInit.HAZEL);

		event.enqueueWork(() -> {
			Sheets.addWoodType(WoodTypesInit.HAZEL);
		});
	}

	@SubscribeEvent
	public static void registerTileEntityRenderers(RegisterRenderers event) {
		event.registerBlockEntityRenderer(TileEntitiesInit.SIGN_TILE_ENTITIES.get(), SignRenderer::new);
	}

	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.getBlockColors().register((p_92626_, tint, pos, p_92629_) -> {
			return tint != null && pos != null ? BiomeColors.getAverageFoliageColor(tint, pos)
					: FoliageColor.getHazelColor();
		}, BlockInit.HAZEL_LEAVES.get());
	}

	@SubscribeEvent
	public static void onItemColors(RegisterColorHandlersEvent.Item event) {
		event.getItemColors().register(new HazelLeaveColor(), BlockInit.HAZEL_LEAVES.get().asItem());
	}

	public static class HazelLeaveColor implements ItemColor {
		@Override
		public int getColor(ItemStack stack, int color) {
			return FoliageColor.getHazelColor();
		}
	}
}