package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.blocks.BlackGrapeCropBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.EmptyWineBottleBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.FlammableLeavesBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.FlammablePlankBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.FlammableRotatedPillarBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.FlammableSlabBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.FlammableStairBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.StandingSignBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.WallSignBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.WhiteGrapeCropBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.WineBottleBlock;
import io.github.itamardenkberg.allyoucaneat.world.features.tree.HazelTreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			AllYouCanEat.MOD_ID);

	public static final RegistryObject<Block> WINE_BOTTLE = BLOCKS.register("wine_bottle",
			() -> new EmptyWineBottleBlock(
					BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject<Block> RED_WINE_BOTTLE = BLOCKS.register("red_wine_bottle",
			() -> new WineBottleBlock(
					BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject<Block> WHITE_WINE_BOTTLE = BLOCKS.register("white_wine_bottle",
			() -> new WineBottleBlock(
					BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject<Block> BLACK_GRAPE_CROP = BLOCKS.register("black_grape_crop",
			() -> new BlackGrapeCropBlock(Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak()
					.sound(SoundType.CROP)));

	public static final RegistryObject<Block> WHITE_GRAPE_CROP = BLOCKS.register("white_grape_crop",
			() -> new WhiteGrapeCropBlock(Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak()
					.sound(SoundType.CROP)));

	public static final RegistryObject<Block> HAZEL_LOG = BLOCKS.register("hazel_log",
			() -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

	public static final RegistryObject<Block> HAZEL_WOOD = BLOCKS.register("hazel_wood",
			() -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

	public static final RegistryObject<Block> STRIPPED_HAZEL_LOG = BLOCKS.register("stripped_hazel_log",
			() -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

	public static final RegistryObject<Block> STRIPPED_HAZEL_WOOD = BLOCKS.register("stripped_hazel_wood",
			() -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

	public static final RegistryObject<Block> HAZEL_LEAVES = BLOCKS.register("hazel_leaves",
			() -> new FlammableLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

	public static final RegistryObject<Block> HAZEL_SAPLING = BLOCKS.register("hazel_sapling",
			() -> new SaplingBlock(new HazelTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject<Block> HAZEL_PLANKS = BLOCKS.register("hazel_planks",
			() -> new FlammablePlankBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

	public static final RegistryObject<Block> HAZEL_SLAB = BLOCKS.register("hazel_slab",
			() -> new FlammableSlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));

	public static final RegistryObject<Block> HAZEL_STAIRS = BLOCKS.register("hazel_stairs",
			() -> new FlammableStairBlock(() -> HAZEL_PLANKS.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));

	public static final RegistryObject<Block> HAZEL_BUTTON = BLOCKS.register("hazel_button",
			() -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));

	public static final RegistryObject<Block> HAZEL_PRESSURE_PLATE = BLOCKS.register("hazel_pressure_plate",
			() -> new PressurePlateBlock(Sensitivity.EVERYTHING,
					BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));

	public static final RegistryObject<Block> HAZEL_FENCE = BLOCKS.register("hazel_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));

	public static final RegistryObject<Block> HAZEL_FENCE_GATE = BLOCKS.register("hazel_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)));

	public static final RegistryObject<Block> HAZEL_DOOR = BLOCKS.register("hazel_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));

	public static final RegistryObject<Block> HAZEL_TRAPDOOR = BLOCKS.register("hazel_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)));

	public static final RegistryObject<Block> HAZEL_SIGN = BLOCKS.register("hazel_sign",
			() -> new StandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), WoodTypesInit.HAZEL));

	public static final RegistryObject<Block> HAZEL_WALL_SIGN = BLOCKS.register("hazel_wall_sign",
			() -> new WallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), WoodTypesInit.HAZEL));
}