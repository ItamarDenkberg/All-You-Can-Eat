package io.github.itamardenkberg.allyoucaneat.common.items.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class GlassFurnaceRecipe implements Recipe<SimpleContainer> {
	private final ResourceLocation id;
	private final ItemStack output;
	private final NonNullList<Ingredient> recipeItems;

	public GlassFurnaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
		this.id = id;
		this.output = output;
		this.recipeItems = recipeItems;
	}

	@Override
	public boolean matches(SimpleContainer pContainer, net.minecraft.world.level.Level pLevel) {
		if (recipeItems.get(0).test(pContainer.getItem(1))) {
			return recipeItems.get(0).test(pContainer.getItem(1));
		}

		return false;
	}

	@Override
	public ItemStack assemble(SimpleContainer pContainer) {
		return output;
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		return output.copy();
	}

	public ItemStack resultItemNew(Container container) {
		return this.output.copy();
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	public static class Type implements RecipeType<GlassFurnaceRecipe> {
		private Type() {

		}

		public static final Type INSTANCE = new Type();
		public static final String ID = "glass_melting";
	}

	public static class Serializer implements RecipeSerializer<GlassFurnaceRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID = new ResourceLocation(AllYouCanEat.MOD_ID, "glass_melting");

		@Override
		public GlassFurnaceRecipe fromJson(ResourceLocation id, JsonObject json) {
			JsonArray ingredient = GsonHelper.getAsJsonArray(json, "ingredient");
			NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

			for (int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromJson(ingredient.get(i)));
			}

			ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

			return new GlassFurnaceRecipe(id, output, inputs);
		}

		@Override
		public GlassFurnaceRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
			NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

			for (int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromNetwork(buf));
			}

			ItemStack output = buf.readItem();
			return new GlassFurnaceRecipe(id, output, inputs);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buf, GlassFurnaceRecipe recipe) {
			buf.writeInt(recipe.getIngredients().size());
			for (Ingredient ing : recipe.getIngredients()) {
				ing.toNetwork(buf);
			}
			buf.writeItemStack(recipe.getResultItem(), false);
		}

		@Override
		public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
			return INSTANCE;
		}

		@Override
		public ResourceLocation getRegistryName() {
			return ID;
		}

		@Override
		public Class<RecipeSerializer<?>> getRegistryType() {
			return Serializer.castClass(RecipeSerializer.class);
		}

		@SuppressWarnings("unchecked")
		private static <G> Class<G> castClass(Class<?> cls) {
			return (Class<G>) cls;
		}
	}

	public ItemStack getToastSymbol() {
		return new ItemStack(BlockInit.GLASS_FURNACE.get());
	}
}