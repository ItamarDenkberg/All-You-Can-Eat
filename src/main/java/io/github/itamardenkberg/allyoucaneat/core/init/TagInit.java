package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class TagInit {
	public static class Blocks {

		// ACYE
		public static final TagKey<Block> CROPS = register("crops");
		public static final TagKey<Block> HAZEL_LOGS = register("hazel_logs");

		// Forge
		public static final TagKey<Block> BRICKS = registerForge("bricks");

		private static TagKey<Block> register(String name) {
			return BlockTags.create(new ResourceLocation(AllYouCanEat.MOD_ID, name));
		}

		private static TagKey<Block> registerForge(String name) {
			return BlockTags.create(new ResourceLocation("forge", name));
		}
	}

	public static class Items {
		// AYCE
		public static final TagKey<Item> HAZEL_LOGS = register("hazel_logs");

		// Forge
		public static final TagKey<Item> FRUITS_GRAPES = registerForge("fruits/grapes");
		public static final TagKey<Item> FRUITS_BLACK_GRAPES = registerForge("fruits/black_grapes");
		public static final TagKey<Item> FRUITS_WHITE_GRAPES = registerForge("fruits/white_grapes");
		public static final TagKey<Item> FRUITS_HAZELNUTS = registerForge("fruits/hazelnuts");
		public static final TagKey<Item> SEEDS_GRAPE = registerForge("seeds/grape");
		public static final TagKey<Item> FOODS = registerForge("foods");
		public static final TagKey<Item> FRUITS = registerForge("fruits");
		public static final TagKey<Item> FORGE_SEEDS = registerForge("seeds");
		public static final TagKey<Item> WINES = registerForge("wines");
		public static final TagKey<Item> MARSHMALLOWS = registerForge("marshmallows");
		public static final TagKey<Item> NUTS = registerForge("nuts");
		public static final TagKey<Item> SEEDS_TOMATO = registerForge("seeds/tomato");
		public static final TagKey<Item> VEGETABLES_TOMATO = registerForge("vegetables/tomato");
		public static final TagKey<Item> VEGETABLES = registerForge("vegetables");
		public static final TagKey<Item> SUGAR = registerForge("sugar");
		public static final TagKey<Item> EGGS = registerForge("eggs");
		public static final TagKey<Item> WHEAT = registerForge("wheat");
		public static final TagKey<Item> FRUITS_STRAWBERRIES = registerForge("fruits/strawberries");
		public static final TagKey<Item> BRICKS = registerForge("bricks");
		public static final TagKey<Item> INGOTS_BRICK = registerForge("ingots/brick");
		public static final TagKey<Item> CROPS_TOMATO = registerForge("crops/tomato");
		public static final TagKey<Item> HOT_CHOCOLATE = registerForge("hot_chocolate");
		public static final TagKey<Item> MILK = registerForge("milk");
		public static final TagKey<Item> COOKED_BEEF = registerForge("cooked_beef");
		public static final TagKey<Item> CHEESE = registerForge("cheese");
		public static final TagKey<Item> CHOCOLATE = registerForge("chocolate");
		public static final TagKey<Item> DESERTS_CHOCOLATE = registerForge("desert/chocolate");
		public static final TagKey<Item> DESERTS_WHITE_CHOCOLATE = registerForge("desert/white_chocolate");
		public static final TagKey<Item> DESERTS_CHORUS_CHOCOLATE = registerForge("desert/chorus_chocolate");
		public static final TagKey<Item> DESERTS_CHOCOLATE_WITH_NUTS = registerForge("desert/chocolate_with_nuts");
		public static final TagKey<Item> CROPS_WHEAT = registerForge("crops/wheat");
		public static final TagKey<Item> CROPS_BROWN_WHEAT = registerForge("crops/brown_wheat");
		public static final TagKey<Item> SEEDS_BROWN_WHEAT = registerForge("seeds/brown_wheat");
		public static final TagKey<Item> HAY_BLOCKS = registerForge("hay_blocks");
		
		private static TagKey<Item> register(String name) {
			return ItemTags.create(new ResourceLocation(AllYouCanEat.MOD_ID, name));
		}

		private static TagKey<Item> registerForge(String name) {
			return ItemTags.create(new ResourceLocation("forge", name));
		}
	}

	public static class Fluids {
		// AYCE

		// Forge
		public static final TagKey<Fluid> CHOCOLATE = registerForge("chocolate");
		public static final TagKey<Fluid> RED_WINE = registerForge("red_wine");
		public static final TagKey<Fluid> WHITE_WINE = registerForge("white_wine");

		@SuppressWarnings("unused")
		private static TagKey<Fluid> register(String name) {
			return FluidTags.create(new ResourceLocation(AllYouCanEat.MOD_ID, name));
		}

		private static TagKey<Fluid> registerForge(String name) {
			return FluidTags.create(new ResourceLocation("forge", name));
		}
	}
}
