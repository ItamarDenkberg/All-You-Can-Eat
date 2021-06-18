package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.blocks.WineBottleBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
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
			() -> new Block(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F)
					.sound(SoundType.GLASS).notSolid()));

	public static final RegistryObject<Block> RED_WINE_BOTTLE = BLOCKS.register("red_wine_bottle",
			() -> new WineBottleBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F)
					.sound(SoundType.GLASS).notSolid()));

	public static final RegistryObject<Block> WHITE_WINE_BOTTLE = BLOCKS.register("white_wine_bottle",
			() -> new WineBottleBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F)
					.sound(SoundType.GLASS).notSolid()));
}
