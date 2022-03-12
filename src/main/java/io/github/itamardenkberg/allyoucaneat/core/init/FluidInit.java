package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluidInit {
	public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
	public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
	public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS,
			AllYouCanEat.MOD_ID);

	// RED WINE
	public static final RegistryObject<FlowingFluid> RED_WINE_FLUID = FLUIDS.register("red_wine_fluid",
			() -> new ForgeFlowingFluid.Source(FluidInit.RED_WINE_PROPERTIES));

	public static final RegistryObject<FlowingFluid> RED_WINE_FLOWING = FLUIDS.register("red_wine_flowing",
			() -> new ForgeFlowingFluid.Flowing(FluidInit.RED_WINE_PROPERTIES));

	public final static ForgeFlowingFluid.Properties RED_WINE_PROPERTIES = new ForgeFlowingFluid.Properties(
			() -> RED_WINE_FLUID.get(), () -> RED_WINE_FLOWING.get(),
			FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).density(15).luminosity(2).viscosity(5)
					.sound(SoundEvents.WATER_AMBIENT).overlay(WATER_OVERLAY_RL).color(0xF2821225)).slopeFindDistance(2)
							.levelDecreasePerBlock(2).block(() -> FluidInit.RED_WINE_BLOCK.get())
							.bucket(() -> ItemInit.RED_WINE_BUCKET.get());

	public final static RegistryObject<LiquidBlock> RED_WINE_BLOCK = BlockInit.BLOCKS.register("red_wine",
			() -> new LiquidBlock(() -> FluidInit.RED_WINE_FLUID.get(), BlockBehaviour.Properties.copy(Blocks.WATER)));

	// WHITE WINE
	public static final RegistryObject<FlowingFluid> WHITE_WINE_FLUID = FLUIDS.register("white_wine_fluid",
			() -> new ForgeFlowingFluid.Source(FluidInit.WHITE_WINE_PROPERTIES));

	public static final RegistryObject<FlowingFluid> WHITE_WINE_FLOWING = FLUIDS.register("white_wine_flowing",
			() -> new ForgeFlowingFluid.Flowing(FluidInit.WHITE_WINE_PROPERTIES));

	public final static ForgeFlowingFluid.Properties WHITE_WINE_PROPERTIES = new ForgeFlowingFluid.Properties(
			() -> WHITE_WINE_FLUID.get(), () -> WHITE_WINE_FLOWING.get(),
			FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).density(15).luminosity(2).viscosity(5)
					.sound(SoundEvents.WATER_AMBIENT).overlay(WATER_OVERLAY_RL).color(0xF2cfbd5b)).slopeFindDistance(2)
							.levelDecreasePerBlock(2).block(() -> FluidInit.WHITE_WINE_BLOCK.get())
							.bucket(() -> ItemInit.WHITE_WINE_BUCKET.get());

	public final static RegistryObject<LiquidBlock> WHITE_WINE_BLOCK = BlockInit.BLOCKS.register("white_wine",
			() -> new LiquidBlock(() -> FluidInit.WHITE_WINE_FLUID.get(),
					BlockBehaviour.Properties.copy(Blocks.WATER)));
}
