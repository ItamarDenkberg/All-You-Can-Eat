package io.github.itamardenkberg.allyoucaneat.client.guis.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class GlassFurnaceFuelSlot extends SlotItemHandler {

	public GlassFurnaceFuelSlot(IItemHandler itemHandler, int index, int x, int y) {
		super(itemHandler, index, x, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return AbstractFurnaceBlockEntity.isFuel(stack) || GlassFurnaceFuelSlot.isBucket(stack);
	}

	@Override
	public int getMaxStackSize(ItemStack pStack) {
		return GlassFurnaceFuelSlot.isBucket(pStack) ? 1 : super.getMaxStackSize(pStack);
	}

	public static boolean isBucket(ItemStack stack) {
		return stack.is(Items.BUCKET);
	}
}
