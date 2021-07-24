package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TagInit {
	public static class Blocks {
		public static final Tags.IOptionalNamedTag<Block> CROPS = register("crops");

		private static Tags.IOptionalNamedTag<Block> register(String name) {
			return BlockTags.createOptional(new ResourceLocation(AllYouCanEat.MOD_ID, name));
		}

		private static Tags.IOptionalNamedTag<Block> registerForge(String name) {
			return BlockTags.createOptional(new ResourceLocation("forge", name));
		}
	}

	public static class Items {
		// AYCE
		public static final Tags.IOptionalNamedTag<Item> GRAPES = register("grapes");
		public static final Tags.IOptionalNamedTag<Item> SEEDS = register("seeds");
		
		// Forge
		public static final Tags.IOptionalNamedTag<Item> FRUITS_GRAPES = registerForge("fruits/grapes");
		public static final Tags.IOptionalNamedTag<Item> FRUITS_BLACK_GRAPES = registerForge("fruits/black_grapes");
		public static final Tags.IOptionalNamedTag<Item> FRUITS_WHITE_GRAPES = registerForge("fruits/white_grapes");
		public static final Tags.IOptionalNamedTag<Item> SEEDS_GRAPE = registerForge("seeds/grape");
		public static final Tags.IOptionalNamedTag<Item> FOODS = registerForge("foods");
		public static final Tags.IOptionalNamedTag<Item> FRUITS = registerForge("fruits");
		public static final Tags.IOptionalNamedTag<Item> FORGE_SEEDS = registerForge("seeds");
		public static final Tags.IOptionalNamedTag<Item> WINES = registerForge("wines");

		
		private static Tags.IOptionalNamedTag<Item> register(String name) {
			return ItemTags.createOptional(new ResourceLocation(AllYouCanEat.MOD_ID, name));
		}

		private static Tags.IOptionalNamedTag<Item> registerForge(String name) {
			return ItemTags.createOptional(new ResourceLocation("forge", name));
		}
	}
}
