package io.github.itamardenkberg.allyoucaneat.core.init;

import java.util.function.Consumer;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
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
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister
			.create(ForgeRegistries.Keys.FLUID_TYPES, AllYouCanEat.MOD_ID);

	// RED WINE
	public static final RegistryObject<FluidType> RED_WINE_TYPE = FLUID_TYPES.register("red_wine",
			() -> new FluidType(createRedWineFluidTypeProperties()) {
				@Override
				public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
					consumer.accept(new IClientFluidTypeExtensions() {

						@Override
						public ResourceLocation getStillTexture() {
							return WATER_STILL_RL;
						}

						@Override
						public ResourceLocation getFlowingTexture() {
							return WATER_FLOWING_RL;
						}

						@Override
						public int getTintColor() {
							return 0xF2821225;
						}
					});
				}
			});

	public static final RegistryObject<FlowingFluid> RED_WINE_FLUID = FLUIDS.register("red_wine_fluid",
			() -> new ForgeFlowingFluid.Flowing(createRedWineProperties()));

	public static final RegistryObject<FlowingFluid> RED_WINE_FLOWING = FLUIDS.register("red_wine_flowing",
			() -> new ForgeFlowingFluid.Flowing(createRedWineProperties()));

	public static final RegistryObject<LiquidBlock> RED_WINE_FLUID_BLOCK = BlockInit.BLOCKS.register("red_wine",
			() -> new LiquidBlock(RED_WINE_FLUID,
					Block.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable()));

	public static FluidType.Properties createRedWineFluidTypeProperties() {
		return FluidType.Properties.create().canSwim(true).canDrown(true).pathType(BlockPathTypes.WATER)
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY).rarity(Rarity.COMMON).density(15)
				.viscosity(5).lightLevel(0);
	}

	public static ForgeFlowingFluid.Properties createRedWineProperties() {
		return new ForgeFlowingFluid.Properties(RED_WINE_TYPE, RED_WINE_FLUID, RED_WINE_FLOWING)
				.bucket(() -> ItemInit.RED_WINE_BUCKET.get()).block(RED_WINE_FLUID_BLOCK);
	}

	// WHITE WINE
	public static final RegistryObject<FluidType> WHITE_WINE_TYPE = FLUID_TYPES.register("white_wine",
			() -> new FluidType(createWhiteWineFluidTypeProperties()) {
				@Override
				public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
					consumer.accept(new IClientFluidTypeExtensions() {

						@Override
						public ResourceLocation getStillTexture() {
							return WATER_STILL_RL;
						}

						@Override
						public ResourceLocation getFlowingTexture() {
							return WATER_FLOWING_RL;
						}

						@Override
						public int getTintColor() {
							return 0xF2821225;
						}
					});
				}
			});

	public static final RegistryObject<FlowingFluid> WHITE_WINE_FLUID = FLUIDS.register("white_wine_fluid",
			() -> new ForgeFlowingFluid.Flowing(createWhiteWineProperties()));

	public static final RegistryObject<FlowingFluid> WHITE_WINE_FLOWING = FLUIDS.register("white_wine_flowing",
			() -> new ForgeFlowingFluid.Flowing(createWhiteWineProperties()));

	public static final RegistryObject<LiquidBlock> WHITE_WINE_FLUID_BLOCK = BlockInit.BLOCKS.register("white_wine",
			() -> new LiquidBlock(WHITE_WINE_FLUID,
					Block.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable()));

	public static FluidType.Properties createWhiteWineFluidTypeProperties() {
		return FluidType.Properties.create().canSwim(true).canDrown(true).pathType(BlockPathTypes.WATER)
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY).rarity(Rarity.COMMON).density(15)
				.viscosity(5).lightLevel(0);
	}

	public static ForgeFlowingFluid.Properties createWhiteWineProperties() {
		return new ForgeFlowingFluid.Properties(RED_WINE_TYPE, RED_WINE_FLUID, RED_WINE_FLOWING)
				.bucket(() -> ItemInit.RED_WINE_BUCKET.get()).block(RED_WINE_FLUID_BLOCK);
	}
}
