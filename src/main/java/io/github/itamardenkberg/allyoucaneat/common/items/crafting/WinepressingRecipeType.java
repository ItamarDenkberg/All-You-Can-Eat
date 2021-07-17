package io.github.itamardenkberg.allyoucaneat.common.items.crafting;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import net.minecraft.item.crafting.IRecipeType;

public class WinepressingRecipeType implements IRecipeType<WinepressingRecipe> {

	@Override
	public String toString() {
		return AllYouCanEat.MOD_ID + ":winepressing";
	}
}
