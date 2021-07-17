package io.github.itamardenkberg.allyoucaneat.common.tileentities;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.containers.WinepressContainer;
import io.github.itamardenkberg.allyoucaneat.common.items.crafting.WinepressingRecipe;
import io.github.itamardenkberg.allyoucaneat.core.init.RecipeInit;
import io.github.itamardenkberg.allyoucaneat.core.init.TileEntityTypeInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class WinepressTileEntity extends LockableLootTileEntity implements ITickableTileEntity {
	public static int slots = 3;
	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
	private int pressingTime;
	private int pressingTimeTotal;
	protected final IIntArray cookingPotData = new IIntArray() {
		@Override
		public int get(int index) {
			switch (index) {
			case 0:
				return WinepressTileEntity.this.pressingTime;
			case 1:
				return WinepressTileEntity.this.pressingTimeTotal;
			default:
				return 0;
			}
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
			case 0:
				WinepressTileEntity.this.pressingTime = value;
				break;
			case 1:
				WinepressTileEntity.this.pressingTimeTotal = value;
				break;
			}
		}

		@Override
		public int size() {
			return 2;
		}
	};
	protected final IRecipeType<? extends WinepressingRecipe> recipeType;

	protected WinepressTileEntity(TileEntityType<?> type, IRecipeType<? extends WinepressingRecipe> recipeType) {
		super(type);
		this.recipeType = recipeType;
	}

	public WinepressTileEntity() {
		this(TileEntityTypeInit.WINEPRESS_TILE_ENTITY_TYPE.get(), RecipeInit.WINEPRESSING);
	}

	@Override
	public int getSizeInventory() {
		return slots;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	public ItemStack getItem() {
		return this.items.get(0);
	}

	@Override
	public void setItems(NonNullList<ItemStack> itemsIn) {
		this.items = itemsIn;
	}

	@Override
	protected Container createMenu(int id, PlayerInventory inventory) {
		return new WinepressContainer(id, inventory, this);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + AllYouCanEat.MOD_ID + ".winepress");
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		super.write(nbt);
		if (!this.checkLootAndWrite(nbt)) {
			ItemStackHelper.saveAllItems(nbt, this.items);
		}
		nbt.putInt("PressingTime", this.pressingTime);
		nbt.putInt("PressingTimeTotal", this.pressingTimeTotal);
		return nbt;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		this.items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(nbt)) {
			ItemStackHelper.loadAllItems(nbt, this.items);
		}
		this.pressingTime = nbt.getInt("PressingTime");
		this.pressingTimeTotal = nbt.getInt("PressingTimeTotal");
	}

	@Override
	public void tick() {
		boolean dirty = false;
		
		if (!this.world.isRemote) {
			if (this.hasItems()) {
				IRecipe<?> irecipe = (IRecipe)this.world.getRecipeManager().getRecipe(this.recipeType, this, this.world).orElse((Object)null);
			}
		}
	}
	
	public boolean hasItems() {
		if (!items.isEmpty()) {
			return true;
		}
		
		return false;
	}
}
