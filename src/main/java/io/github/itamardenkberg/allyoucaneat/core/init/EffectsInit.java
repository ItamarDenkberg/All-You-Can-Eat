package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.effects.FlamingEffect;
import io.github.itamardenkberg.allyoucaneat.common.effects.WitherResistanceEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectsInit {
	public static final DeferredRegister<MobEffect> EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
			AllYouCanEat.MOD_ID);

	public static final RegistryObject<MobEffect> WITHER_RESISTANCE = EFFECT.register("wither_resistance",
			() -> new WitherResistanceEffect(MobEffectCategory.BENEFICIAL, 6354426));

	public static final RegistryObject<MobEffect> FLAMING = EFFECT.register("flaming",
			() -> new FlamingEffect(MobEffectCategory.BENEFICIAL, 6354426));
}
