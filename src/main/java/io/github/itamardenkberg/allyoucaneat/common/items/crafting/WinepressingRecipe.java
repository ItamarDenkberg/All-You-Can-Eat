package io.github.itamardenkberg.allyoucaneat.common.items.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import io.github.itamardenkberg.allyoucaneat.core.init.RecipeInit;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class WinepressingRecipe implements IRecipe<IInventory> {
	public static final Serializer SERIALIZER = new Serializer();
	private final Ingredient input;
	private final ItemStack output;
	private ResourceLocation id;
	private final float experience;
	private final int pressingTime;

	public WinepressingRecipe(ResourceLocation id, Ingredient input, ItemStack output, float experience, int pressingTime) {
		this.id = id;
		this.input = input;
		this.output = output;
		this.experience = experience;
		this.pressingTime = pressingTime;
	}

	@Override
	public boolean matches(IInventory inventory, World world) {
		return this.input.test(inventory.getStackInSlot(0));
	}

	@Override
	public ItemStack getCraftingResult(IInventory inventory) {
		return this.output.copy();
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.output;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}
	
	public float getExperience() {
		return this.experience;
	}

	public int getPressingTime() {
		return this.pressingTime;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public IRecipeType<?> getType() {
		return RecipeInit.WINEPRESSING;
	}

	@Override
	public ItemStack getIcon() {
		return new ItemStack(ItemInit.WINEPRESS.get());
	}

	public boolean isValid(ItemStack input, ItemStack bottle) {
		return this.input.test(input);
	}

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<WinepressingRecipe> {

		Serializer() {
			this.setRegistryName(AllYouCanEat.MOD_ID, "winepressing");
		}

		@Override
		public WinepressingRecipe read(ResourceLocation location, JsonObject object) {
			final JsonElement inputElement = JSONUtils.isJsonArray(object, "input")
					? JSONUtils.getJsonArray(object, "input")
					: JSONUtils.getJsonObject(object, "input");
			final Ingredient input = Ingredient.deserialize(inputElement);
			final ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(object, "output"));
			final float experience = JSONUtils.getFloat(object, "experience", 0.0F);
			final int pressingTime = JSONUtils.getInt(object, "cookingtime", 200);
			
			return new WinepressingRecipe(location, input, output, experience, pressingTime);
		}

		@Override
		public WinepressingRecipe read(ResourceLocation location, PacketBuffer buffer) {
			final Ingredient input = Ingredient.read(buffer);
			final ItemStack output = buffer.readItemStack();
			float experience = buffer.readFloat();
			int pressingTime = buffer.readVarInt();

			return new WinepressingRecipe(location, input, output, experience, pressingTime);
		}

		@Override
		public void write(PacketBuffer buffer, WinepressingRecipe recipe) {
			recipe.input.write(buffer);
			buffer.writeItemStack(recipe.output);
			buffer.writeFloat(recipe.experience);
			buffer.writeVarInt(recipe.pressingTime);
		}
	}

	public boolean isValidAdditionItem(ItemStack stack) {
		return this.input.test(stack);
	}
}
