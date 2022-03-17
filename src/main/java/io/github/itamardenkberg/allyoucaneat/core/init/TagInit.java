package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.Tags;

public class TagInit {
	public static class Blocks {

		// ACYE
		public static final Tags.IOptionalNamedTag<Block> CROPS = register("crops");
		public static final Tags.IOptionalNamedTag<Block> HAZEL_LOGS = register("hazel_logs");

		private static Tags.IOptionalNamedTag<Block> register(String name) {
			return BlockTags.createOptional(new ResourceLocation(AllYouCanEat.MOD_ID, name));
		}

		private static Tags.IOptionalNamedTag<Block> registerForge(String name) {
			return BlockTags.createOptional(new ResourceLocation("forge", name));
		}
	}

	public static class Items {
		// AYCE
		public static final Tags.IOptionalNamedTag<Item> HAZEL_LOGS = register("hazel_logs");

		// Forge
		public static final Tags.IOptionalNamedTag<Item> FRUITS_GRAPES = registerForge("fruits/grapes");
		public static final Tags.IOptionalNamedTag<Item> FRUITS_BLACK_GRAPES = registerForge("fruits/black_grapes");
		public static final Tags.IOptionalNamedTag<Item> FRUITS_WHITE_GRAPES = registerForge("fruits/white_grapes");
		public static final Tags.IOptionalNamedTag<Item> FRUITS_HAZELNUTS = registerForge("fruits/hazelnuts");
		public static final Tags.IOptionalNamedTag<Item> SEEDS_GRAPE = registerForge("seeds/grape");
		public static final Tags.IOptionalNamedTag<Item> FOODS = registerForge("foods");
		public static final Tags.IOptionalNamedTag<Item> FRUITS = registerForge("fruits");
		public static final Tags.IOptionalNamedTag<Item> FORGE_SEEDS = registerForge("seeds");
		public static final Tags.IOptionalNamedTag<Item> WINES = registerForge("wines");
		public static final Tags.IOptionalNamedTag<Item> MARSHMALLOWS = registerForge("marshmallows");
		public static final Tags.IOptionalNamedTag<Item> NUTS = registerForge("nuts");
		public static final Tags.IOptionalNamedTag<Item> SEEDS_TOMATO = registerForge("seeds/tomato");
		public static final Tags.IOptionalNamedTag<Item> VEGETABLES_TOMATOES = registerForge("vegetables/tomatoes");
		public static final Tags.IOptionalNamedTag<Item> VEGETABLES = registerForge("vegetables");
		public static final Tags.IOptionalNamedTag<Item> SUGAR = registerForge("sugar");
		public static final Tags.IOptionalNamedTag<Item> EGGS = registerForge("eggs");
		public static final Tags.IOptionalNamedTag<Item> WHEAT = registerForge("wheat");
		public static final Tags.IOptionalNamedTag<Item> FRUITS_STRAWBERRIES = registerForge("fruits/strawberries");
		public static final Tags.IOptionalNamedTag<Item> SEEDS_STRAWBERRY = registerForge("seed/strawberry");

		private static Tags.IOptionalNamedTag<Item> register(String name) {
			return ItemTags.createOptional(new ResourceLocation(AllYouCanEat.MOD_ID, name));
		}

		private static Tags.IOptionalNamedTag<Item> registerForge(String name) {
			return ItemTags.createOptional(new ResourceLocation("forge", name));
		}
	}

	public static class Fluids {
		// AYCE

		// Forge
		public static final Tags.IOptionalNamedTag<Fluid> RED_WINE = registerForge("red_wine");
		public static final Tags.IOptionalNamedTag<Fluid> WHITE_WINE = registerForge("white_wine");

		private static Tags.IOptionalNamedTag<Fluid> register(String name) {
			return FluidTags.createOptional(new ResourceLocation(AllYouCanEat.MOD_ID, name));
		}

		private static Tags.IOptionalNamedTag<Fluid> registerForge(String name) {
			return FluidTags.createOptional(new ResourceLocation("forge", name));
		}
	}
}
