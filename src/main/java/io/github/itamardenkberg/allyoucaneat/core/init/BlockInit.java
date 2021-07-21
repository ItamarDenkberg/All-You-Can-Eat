package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.blocks.BlackGrapeCropBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.EmptyWineBottleBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.WhiteGrapeCropBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.WineBottleBlock;
import io.github.itamardenkberg.allyoucaneat.common.blocks.WinepressBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			AllYouCanEat.MOD_ID);

//	public static final RegistryObject<Block> MOLTEN_GLASS = BLOCKS.register("molten_glass",
//			() -> new MoltenGlassBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F)
//					.sound(SoundType.GLASS).notSolid()));

	public static final RegistryObject<Block> WINE_BOTTLE = BLOCKS.register("wine_bottle",
			() -> new EmptyWineBottleBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F)
					.sound(SoundType.GLASS).notSolid()));

	public static final RegistryObject<Block> RED_WINE_BOTTLE = BLOCKS.register("red_wine_bottle",
			() -> new WineBottleBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F)
					.sound(SoundType.GLASS).notSolid()));

	public static final RegistryObject<Block> WHITE_WINE_BOTTLE = BLOCKS.register("white_wine_bottle",
			() -> new WineBottleBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F)
					.sound(SoundType.GLASS).notSolid()));

	public static final RegistryObject<Block> WINEPRESS = BLOCKS.register("winepress",
			() -> new WinepressBlock(AbstractBlock.Properties.from(Blocks.BARREL)));

	public static final RegistryObject<Block> BLACK_GRAPE_CROP = BLOCKS.register("black_grape_crop",
			() -> new BlackGrapeCropBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly()
					.zeroHardnessAndResistance().sound(SoundType.CROP)));

	public static final RegistryObject<Block> WHITE_GRAPE_CROP = BLOCKS.register("white_grape_crop",
			() -> new WhiteGrapeCropBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly()
					.zeroHardnessAndResistance().sound(SoundType.CROP)));

}