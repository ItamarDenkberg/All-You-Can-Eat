package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.common.items.crafting.GlassFurnaceRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface RecipeTypesInit<T extends Recipe<?>> {
	RecipeType<GlassFurnaceRecipe> GLASS_MELTING = RecipeType.register("glass_melting");
}
