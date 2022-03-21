package io.github.itamardenkberg.allyoucaneat.client.guis.slots;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class GlassFurnaceResultSlot extends SlotItemHandler {

	public GlassFurnaceResultSlot(IItemHandler itemHandler, int index, int x, int y) {
		super(itemHandler, index, x, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}
}