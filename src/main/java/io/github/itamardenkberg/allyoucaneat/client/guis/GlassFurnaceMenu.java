package io.github.itamardenkberg.allyoucaneat.client.guis;

import java.util.List;

import com.google.common.collect.Lists;

import io.github.itamardenkberg.allyoucaneat.client.guis.slots.GlassFurnaceFuelSlot;
import io.github.itamardenkberg.allyoucaneat.client.guis.slots.GlassFurnaceResultSlot;
import io.github.itamardenkberg.allyoucaneat.common.items.crafting.GlassFurnaceRecipe;
import io.github.itamardenkberg.allyoucaneat.common.tileentities.GlassFurnaceTileEntity;
import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import io.github.itamardenkberg.allyoucaneat.core.init.MenuTypesInit;
import io.github.itamardenkberg.allyoucaneat.core.init.RecipeTypesInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class GlassFurnaceMenu extends AbstractContainerMenu {
	final ResultContainer resultContainer = new ResultContainer();
	private final GlassFurnaceTileEntity entity;
	private final Level world;
	private final ContainerData data;
	private final DataSlot selectedRecipeIndex = DataSlot.standalone();
	private List<GlassFurnaceRecipe> recipes = Lists.newArrayList();
	private ItemStack input = ItemStack.EMPTY;
	public final Container container = new SimpleContainer(1) {
		@Override
		public void setChanged() {
			super.setChanged();
			GlassFurnaceMenu.this.slotsChanged(this);
			GlassFurnaceMenu.this.slotUpdateListener.run();
		}
	};
	Runnable slotUpdateListener = () -> {

	};

	public GlassFurnaceMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		this(id, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
	}

	public GlassFurnaceMenu(int id, Inventory inv, BlockEntity tileEntity, ContainerData data) {
		super(MenuTypesInit.GLASS_FURNACE_MENU.get(), id);
		checkContainerSize(inv, 4);
		entity = ((GlassFurnaceTileEntity) tileEntity);
		this.world = inv.player.level;
		this.data = data;

		addPlayerInventory(inv);
		addPlayerHotbar(inv);

		this.entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
			this.addSlot(new GlassFurnaceFuelSlot(handler, 0, 9, 52));
			this.addSlot(new SlotItemHandler(handler, 1, 9, 16));
			this.addSlot(new GlassFurnaceResultSlot(handler, 2, 146, 33));
		});

		this.addDataSlots(data);
	}

	public int getSelectedRecipeIndex() {
		return this.selectedRecipeIndex.get();
	}

	public List<GlassFurnaceRecipe> getRecipes() {
		return this.recipes;
	}

	public int getNumRecipes() {
		return this.recipes.size();
	}

	public boolean hasInputItem() {
		return this.slots.get(1).hasItem() && !this.recipes.isEmpty();
	}

	public boolean isCrafting() {
		return data.get(0) > 0;
	}

	public boolean hasFuel() {
		return data.get(2) > 0;
	}

	public int getScaledProgress() {
		int progress = this.data.get(0);
		int maxProgress = this.data.get(1);
		int progressArrowSize = 26;

		return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
	}

	public void registerUpdateListener(Runnable p_40324_) {
		this.slotUpdateListener = p_40324_;
	}

	public int getScaledFuelProgress() {
		int fuelProgress = this.data.get(2);
		int maxFuelProgress = this.data.get(3);
		int fuelProgressSize = 14;

		return maxFuelProgress != 0 ? (int) (((float) fuelProgress / (float) maxFuelProgress) * fuelProgressSize) : 0;
	}

	private boolean isValidRecipeIndex(int p_40335_) {
		return p_40335_ >= 0 && p_40335_ < this.recipes.size();
	}

	public void slotsChanged(Container inventory) {
		final ItemStack itemstack = this.slots.get(1).getItem();
		if (itemstack.getItem() != this.input.getItem()) {
			this.input = itemstack.copy();
			this.setupRecipeList(inventory, itemstack);
		}
	}

	private void setupRecipeList(Container inventory, ItemStack stack) {
		this.recipes.clear();
		this.selectedRecipeIndex.set(-1);
		this.slots.get(2).set(ItemStack.EMPTY);
		if (!stack.isEmpty()) {
			this.recipes = this.world.getRecipeManager().getAllRecipesFor(RecipeTypesInit.GLASS_MELTING);
		}
	}

	private void setupResultSlot() {
		if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get())) {
			GlassFurnaceRecipe recipe = this.recipes.get(this.selectedRecipeIndex.get());
			this.resultContainer.setRecipeUsed(recipe);
			this.slots.get(2).set(recipe.resultItemNew(this.container));
		} else {
			this.slots.get(2).set(ItemStack.EMPTY);
		}

		this.broadcastChanges();
	}

	@Override
	public MenuType<?> getType() {
		return MenuTypesInit.GLASS_FURNACE_MENU.get();
	}

	private static final int HOTBAR_SLOT_COUNT = 9;
	private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
	private static final int VANILLA_FIRST_SLOT_INDEX = 0;
	private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

	private static final int TE_INVENTORY_SLOT_COUNT = 3;

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		System.out.print(" Recipe Size: " + this.recipes.size() + " | Recipe method: " + this.recipes);

		Slot sourceSlot = slots.get(index);
		if (sourceSlot == null || !sourceSlot.hasItem())
			return ItemStack.EMPTY;
		ItemStack sourceStack = sourceSlot.getItem();
		ItemStack copyOfSourceStack = sourceStack.copy();

		if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
			if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX,
					TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)) {
				return ItemStack.EMPTY;
			}
		} else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
			if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT,
					false)) {
				return ItemStack.EMPTY;
			}
		} else {
			System.out.println("Invalid slotIndex:" + index);
			return ItemStack.EMPTY;
		}
		if (sourceStack.getCount() == 0) {
			sourceSlot.set(ItemStack.EMPTY);
		} else {
			sourceSlot.setChanged();
		}
		sourceSlot.onTake(playerIn, sourceStack);
		return copyOfSourceStack;
	}

	@Override
	public boolean stillValid(Player pPlayer) {
		return stillValid(ContainerLevelAccess.create(world, entity.getBlockPos()), pPlayer,
				BlockInit.GLASS_FURNACE.get());
	}

	@Override
	public boolean clickMenuButton(Player player, int id) {
		if (this.isIdInRange(id)) {
			this.selectedRecipeIndex.set(id);
			this.setupResultSlot();
		}
		return true;
	}

	private boolean isIdInRange(final int id) {
		return id >= 0 && id < this.recipes.size();
	}

	private void addPlayerInventory(Inventory playerInventory) {
		for (int i = 0; i < 3; ++i) {
			for (int l = 0; l < 9; ++l) {
				this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
			}
		}
	}

	private void addPlayerHotbar(Inventory playerInventory) {
		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
		}
	}
}