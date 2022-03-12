package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.entities.vehicle.BoatEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypesInit {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
			AllYouCanEat.MOD_ID);

	public static final RegistryObject<EntityType<BoatEntity>> BOAT_ENTITY = ENTITY_TYPES.register("boat",
			() -> EntityType.Builder.<BoatEntity>of(BoatEntity::new, MobCategory.MISC).fireImmune()
					.sized(1.375F, 0.5625F)
					.setCustomClientFactory((spawnEntity, world) -> new BoatEntity(world, 0, 0, 0)).build("boat"));
}
