package io.github.itamardenkberg.allyoucaneat.client.render.entity;

import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.entities.vehicle.BoatEntity;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

public class BoatEntityRenderer extends BoatRenderer {
	private final Map<BoatEntity.Type, Pair<ResourceLocation, BoatModel>> boatResources;

	public BoatEntityRenderer(EntityRendererProvider.Context context) {
		super(context, false);
		this.shadowRadius = 0.8F;
		this.boatResources = Stream.of(BoatEntity.Type.values())
				.collect(ImmutableMap.toImmutableMap((p_173938_) -> p_173938_, (type) -> Pair.of(
						new ResourceLocation(AllYouCanEat.MOD_ID, "textures/entity/boat/" + type.getName() + ".png"),
						new BoatModel(context.bakeLayer(
								new ModelLayerLocation(new ResourceLocation("minecraft", "boat/oak"), "main")), false))));

	}

	@Override
	public ResourceLocation getTextureLocation(Boat pEntity) {
		if (pEntity instanceof BoatEntity modBoat) {
			return this.boatResources.get(modBoat.getModBoatType()).getFirst();
		}

		return new ResourceLocation("minecraft", "boat/oak");
	}

	public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
		if (boat instanceof BoatEntity modBoat) {
			return this.boatResources.get(modBoat.getModBoatType());
		}

		return null;
	}
}