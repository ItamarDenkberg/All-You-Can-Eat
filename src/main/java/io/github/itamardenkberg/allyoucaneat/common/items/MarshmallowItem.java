package io.github.itamardenkberg.allyoucaneat.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class MarshmallowItem extends Item {

	public MarshmallowItem(Properties properties) {
		super(properties);
	}
	
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (entity instanceof ServerPlayer) {
			ServerPlayer serverplayerentity = (ServerPlayer) entity;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}
		
		if (stack.isEmpty()) {
			return new ItemStack(Items.STICK);
		} else {
			if (entity instanceof Player && !((Player) entity).getAbilities().instabuild) {
				stack.shrink(1);
				ItemStack itemstack = new ItemStack(Items.STICK);
				Player player = (Player) entity;
				if (!player.getInventory().add(itemstack)) {
					player.drop(itemstack, false);
				}
			}

			return stack;
		}
	}
	
}
