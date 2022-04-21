package io.github.itamardenkberg.allyoucaneat.common.effects;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class WitherResistanceEffect extends MobEffect {

	public WitherResistanceEffect(MobEffectCategory category, int color) {
		super(MobEffectCategory.BENEFICIAL, 6354426);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		DamageSource source = entity.getLastDamageSource();
		if (!entity.level.isClientSide()) {
			if (source == DamageSource.WITHER) {
				entity.removeEffect(MobEffects.WITHER);
			}
		}
		super.applyEffectTick(entity, amplifier);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
