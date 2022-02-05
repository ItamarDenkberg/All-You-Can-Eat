package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.blocks.BlackGrapeCropBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.EmptyWineBottleBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.FlammableLeavesBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.FlammablePlankBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.FlammableRotatedPillarBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.WhiteGrapeCropBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.WineBottleBlock;
import io.github.itamardenkberg.allyoucaneat.world.features.tree.HazelTreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
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
}