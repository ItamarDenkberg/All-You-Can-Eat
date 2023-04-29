package io.github.itamardenkberg.allyoucaneat.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FlamingEffect extends MobEffect {

	public FlamingEffect(MobEffectCategory category, int color) {
		super(MobEffectCategory.BENEFICIAL, 13244177);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (!entity.level.isClientSide()) {
			entity.setSpeed(amplifier);
		}
		super.applyEffectTick(entity, amplifier);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
