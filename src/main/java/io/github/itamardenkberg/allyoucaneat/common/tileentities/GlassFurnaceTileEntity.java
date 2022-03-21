package io.github.itamardenkberg.allyoucaneat.common.tileentities;

import java.util.Optional;

import io.github.itamardenkberg.allyoucaneat.client.guis.GlassFurnaceMenu;
import io.github.itamardenkberg.allyoucaneat.common.items.crafting.GlassFurnaceRecipe;
import io.github.itamardenkberg.allyoucaneat.core.init.TileEntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class GlassFurnaceTileEntity extends BlockEntity implements MenuProvider {
	private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};
	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

	protected final ContainerData data;
	private int progress = 0;
	private int maxProgress = 72;
	private int fuelTime = 0;
	private int maxFuelTime = 0;

	public GlassFurnaceTileEntity(BlockPos pos, BlockState state) {
		super(TileEntitiesInit.GLASS_FURNACE.get(), pos, state);
		this.data = new ContainerData() {
			public int get(int index) {
				switch (index) {
				case 0:
					return GlassFurnaceTileEntity.this.progress;
				case 1:
					return GlassFurnaceTileEntity.this.maxProgress;
				case 2:
					return GlassFurnaceTileEntity.this.fuelTime;
				case 3:
					return GlassFurnaceTileEntity.this.maxFuelTime;
				default:
					return 0;
				}
			}

			public void set(int index, int value) {
				switch (index) {
				case 0:
					GlassFurnaceTileEntity.this.progress = value;
					break;
				case 1:
					GlassFurnaceTileEntity.this.maxProgress = value;
					break;
				case 2:
					GlassFurnaceTileEntity.this.fuelTime = value;
					break;
				case 3:
					GlassFurnaceTileEntity.this.maxFuelTime = value;
					break;
				}
			}

			public int getCount() {
				return 4;
			}
		};
	}

	@Override
	public Component getDisplayName() {
		return new TranslatableComponent("container.allyoucaneat.glass_furnace");
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
		return new GlassFurnaceMenu(id, inventory, this, this.data);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @javax.annotation.Nullable Direction direction) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return lazyItemHandler.cast();
		}

		return super.getCapability(cap, direction);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		lazyItemHandler = LazyOptional.of(() -> itemHandler);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		lazyItemHandler.invalidate();
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		tag.put("inventory", itemHandler.serializeNBT());
		tag.putInt("blaster.progress", progress);
		tag.putInt("blaster.fuelTime", fuelTime);
		tag.putInt("blaster.maxFuelTime", maxFuelTime);
		super.saveAdditional(tag);
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		itemHandler.deserializeNBT(nbt.getCompound("inventory"));
		progress = nbt.getInt("blaster.progress");
		fuelTime = nbt.getInt("blaster.fuelTime");
		maxFuelTime = nbt.getInt("blaster.maxFuelTime");
	}

	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for (int i = 0; i < itemHandler.getSlots(); i++) {
			inventory.setItem(i, itemHandler.getStackInSlot(i));
		}

		Containers.dropContents(this.level, this.worldPosition, inventory);
	}

	private void consumeFuel() {
		if (!itemHandler.getStackInSlot(0).isEmpty()) {
			this.fuelTime = ForgeHooks.getBurnTime(this.itemHandler.extractItem(0, 1, false), RecipeType.SMELTING);
			this.maxFuelTime = this.fuelTime;
		}
	}

	public static void tick(Level pLevel, BlockPos pos, BlockState state, GlassFurnaceTileEntity entity) {
		if (isConsumingFuel(entity)) {
			entity.fuelTime--;
		}

		if (hasRecipe(entity)) {
			if (hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
				entity.consumeFuel();
				setChanged(pLevel, pos, state);
			}
			if (isConsumingFuel(entity)) {
				entity.progress++;
				setChanged(pLevel, pos, state);
				if (entity.progress > entity.maxProgress) {
					craftItem(entity);
				}
			}
		} else {
			entity.resetProgress();
			setChanged(pLevel, pos, state);
		}
	}

	private static boolean hasFuelInFuelSlot(GlassFurnaceTileEntity entity) {
		return !entity.itemHandler.getStackInSlot(0).isEmpty();
	}

	private static boolean isConsumingFuel(GlassFurnaceTileEntity entity) {
		return entity.fuelTime > 0;
	}

	private static boolean hasRecipe(GlassFurnaceTileEntity entity) {
		Level world = entity.level;
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}

		Optional<GlassFurnaceRecipe> match = world.getRecipeManager().getRecipeFor(GlassFurnaceRecipe.Type.INSTANCE,
				inventory, world);

		return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
	}

	private static void craftItem(GlassFurnaceTileEntity entity) {
		Level world = entity.level;
		SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
		for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
			inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
		}

		Optional<GlassFurnaceRecipe> match = world.getRecipeManager().getRecipeFor(GlassFurnaceRecipe.Type.INSTANCE,
				inventory, world);

		if (match.isPresent()) {
			entity.itemHandler.extractItem(1, 1, false);

			entity.itemHandler.setStackInSlot(2, new ItemStack(match.get().getResultItem().getItem(),
					entity.itemHandler.getStackInSlot(2).getCount() + 1));

			entity.resetProgress();
		}
	}

	private void resetProgress() {
		this.progress = 0;
	}

	private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
		return inventory.getItem(2).getItem() == output.getItem() || inventory.getItem(2).isEmpty();
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
		return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
	}
}