package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.items.crafting.GlassFurnaceRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeSerializersInit {

	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
			.create(ForgeRegistries.RECIPE_SERIALIZERS, AllYouCanEat.MOD_ID);

	public static final RegistryObject<RecipeSerializer<GlassFurnaceRecipe>> GLASS_MELTING_SERIALIZER = SERIALIZERS
			.register("glass_melting", () -> GlassFurnaceRecipe.Serializer.INSTANCE);

	public static void register(IEventBus eventBus) {
		SERIALIZERS.register(eventBus);
	}
}