package io.github.itamardenkberg.allyoucaneat.common.containers;

import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class WineBottleSlot extends Slot {
	@SuppressWarnings("unused")
	private final WinepressContainer winepress;

	public WineBottleSlot(WinepressContainer container, IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
		this.winepress = container;
	}

	public boolean isItemValid(ItemStack stack) {
		return isBottle(stack);
	}

	public int getItemStackLimit(ItemStack stack) {
		return isBottle(stack) ? 1 : super.getItemStackLimit(stack);
	}

	public static boolean isBottle(ItemStack stack) {
		return stack.getItem() == ItemInit.WINE_BOTTLE.get();
	}
}
