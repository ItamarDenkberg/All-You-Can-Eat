package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.blocks.BlackGrapeCropBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.EmptyWineBottleBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.WhiteGrapeCropBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.WineBottleBlock;
import net.minecraft.world.level.block.Block;
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
			() -> new EmptyWineBottleBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F)
					.sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject<Block> RED_WINE_BOTTLE = BLOCKS.register("red_wine_bottle",
			() -> new WineBottleBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F)
					.sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject<Block> WHITE_WINE_BOTTLE = BLOCKS.register("white_wine_bottle",
			() -> new WineBottleBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F)
					.sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject<Block> BLACK_GRAPE_CROP = BLOCKS.register("black_grape_crop",
			() -> new BlackGrapeCropBlock(Block.Properties.of(Material.PLANT).noCollission().randomTicks()
					.instabreak().sound(SoundType.CROP)));

	public static final RegistryObject<Block> WHITE_GRAPE_CROP = BLOCKS.register("white_grape_crop",
			() -> new WhiteGrapeCropBlock(Block.Properties.of(Material.PLANT).noCollission().randomTicks()
					.instabreak().sound(SoundType.CROP)));

}