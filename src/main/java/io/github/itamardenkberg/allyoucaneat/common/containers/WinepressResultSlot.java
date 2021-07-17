package io.github.itamardenkberg.allyoucaneat.common.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.hooks.BasicEventHooks;

public class WinepressResultSlot extends Slot {
	private final PlayerEntity player;
	private int removeCount;

	public WinepressResultSlot(PlayerEntity player, IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
		this.player = player;
	}

	public boolean isItemValid(ItemStack stack) {
		return false;
	}

	public ItemStack decrStackSize(int i) {
		if (this.getHasStack()) {
			this.removeCount += Math.min(i, this.getStack().getCount());
		}

		return super.decrStackSize(i);
	}

	public ItemStack onTake(PlayerEntity player, ItemStack stack) {
		this.onCrafting(stack);
		super.onTake(player, stack);
		return stack;
	}

	protected void onCrafting(ItemStack stack, int i) {
		this.removeCount += i;
		this.onCrafting(stack);
	}

	protected void onCrafting(ItemStack stack) {
		stack.onCrafting(this.player.world, this.player, this.removeCount);
		//if (!this.player.world.isRemote && this.inventory instanceof WinepressTileEntity) {
			//((WinepressTileEntity) this.inventory).unlockRecipes(this.player);
		//}

		this.removeCount = 0;
		BasicEventHooks.firePlayerSmeltedEvent(this.player, stack);
	}
}
