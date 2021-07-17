package io.github.itamardenkberg.allyoucaneat.common.containers;

import java.util.Objects;

import io.github.itamardenkberg.allyoucaneat.common.tileentities.WinepressTileEntity;
import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import io.github.itamardenkberg.allyoucaneat.core.init.ContainerTypeInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class WinepressContainer extends Container {
	public final WinepressTileEntity tile;
	private final IWorldPosCallable canInteractWithCallable;

	public WinepressContainer(final int i, final PlayerInventory inventory, final WinepressTileEntity tile) {
		super(ContainerTypeInit.WINEPRESS_CONTAINER_TYPE.get(), i);
		this.tile = tile;
		this.canInteractWithCallable = IWorldPosCallable.of(tile.getWorld(), tile.getPos());

		this.addSlot(new Slot((IInventory) tile, 0, 30, 35));
		this.addSlot(new WineBottleSlot(this, (IInventory) tile, 1, 65, 35));
		this.addSlot(new WinepressResultSlot(inventory.player, (IInventory) tile, 2, 125, 35));

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
			}
		}

		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(inventory, col, 8 + col * 18, 142));
		}
	}

	public WinepressContainer(final int i, final PlayerInventory inventory, final PacketBuffer buffer) {
		this(i, inventory, getTileEntity(inventory, buffer));
	}

	private static WinepressTileEntity getTileEntity(final PlayerInventory inventory, final PacketBuffer buffer) {
		Objects.requireNonNull(inventory, "Player Inventory cannot be null.");
		Objects.requireNonNull(buffer, "Packet Buffer cannot be null.");
		final TileEntity tile = inventory.player.world.getTileEntity(buffer.readBlockPos());
		if (tile instanceof WinepressTileEntity) {
			return (WinepressTileEntity) tile;
		}
		throw new IllegalStateException("Tile Entity Is Not Correct");
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerEntity) {
		return isWithinUsableDistance(canInteractWithCallable, playerEntity, BlockInit.WINEPRESS.get());
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerEntity, int i) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			if (i < WinepressTileEntity.slots
					&& !this.mergeItemStack(stack1, WinepressTileEntity.slots, this.inventorySlots.size(), true)) {
				return ItemStack.EMPTY;
			}

			if (!this.mergeItemStack(stack1, 0, WinepressTileEntity.slots, false)) {
				return ItemStack.EMPTY;
			}

			if (stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

		}
		return stack;
	}
}
