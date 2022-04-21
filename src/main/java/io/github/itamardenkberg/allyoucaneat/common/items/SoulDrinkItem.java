package io.github.itamardenkberg.allyoucaneat.common.items;

import io.github.itamardenkberg.allyoucaneat.core.init.EffectsInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class SoulDrinkItem extends Item {

	public SoulDrinkItem(Properties properties) {
		super(properties);
	}

	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (entity instanceof ServerPlayer) {
			ServerPlayer serverplayerentity = (ServerPlayer) entity;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}
		if (entity instanceof Player && !((Player) entity).getAbilities().instabuild) {
			stack.shrink(1);
		}

		if (!world.isClientSide) {
			entity.addEffect(new MobEffectInstance(EffectsInit.WITHER_RESISTANCE.get(), 2400), entity);
		}

		return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
	}

	public int getUseDuration(ItemStack stack) {
		return 40;
	}

	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		return ItemUtils.startUsingInstantly(world, player, hand);
	}
}
