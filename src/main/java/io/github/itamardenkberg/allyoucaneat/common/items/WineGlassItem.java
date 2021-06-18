package io.github.itamardenkberg.allyoucaneat.common.items;

import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WineGlassItem extends Item {

	public WineGlassItem(Properties properties) {
		super(properties);
	}

	public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
		if (!world.isRemote) {
			entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 2));
		}
		if (entity instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entity;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.addStat(Stats.ITEM_USED.get(this));
		}
		if (entity instanceof PlayerEntity && !((PlayerEntity) entity).abilities.isCreativeMode) {
			stack.shrink(1);
		}
		return stack.isEmpty() ? new ItemStack(ItemInit.WINE_GLASS.get()) : stack;
	}

	public int getUseDuration(ItemStack stack) {
		return 16;
	}

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		return DrinkHelper.startDrinking(world, player, hand);
	}
}
