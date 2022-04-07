package io.github.itamardenkberg.allyoucaneat.common.tileentities;

import org.jetbrains.annotations.NotNull;

import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import io.github.itamardenkberg.allyoucaneat.core.init.TileEntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MilkCauldronTileEntity extends BlockEntity {
	private int progress = 0;
	private int totalTime = 600;

	public MilkCauldronTileEntity(BlockPos pos, BlockState state) {
		super(TileEntitiesInit.MILK_CAULDRON_TILE_ENTITY.get(), pos, state);
	}

	public static void tick(Level world, BlockPos pos, BlockState state, MilkCauldronTileEntity tile) {
		boolean flag = false;

		if (state.getValue(LayeredCauldronBlock.LEVEL) > 0) {
			flag = true;
			tile.progress++;
			if (tile.progress >= tile.totalTime) {
				ItemStack cheese = new ItemStack(ItemInit.CHEESE.get());
				Containers.dropItemStack(world, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), cheese);
				LayeredCauldronBlock.lowerFillLevel(state, world, pos);
				world.sendBlockUpdated(pos, state, state, 3);
				tile.progress = 0;
				world.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 0);
			}
		}

		if (flag) {
			setChanged(world, pos, state);
		}
	}

	public float getCookingProgress() {
		return totalTime != 0 ? (float) this.progress / (float) totalTime : 0.0F;
	}

	@Override
	public void load(@NotNull CompoundTag tag) {
		super.load(tag);
		this.progress = tag.getInt("CookingTime");
		this.totalTime = tag.getInt("CookingTotalTime");
	}

	@Override
	protected void saveAdditional(@NotNull CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("time", this.progress);
		tag.putInt("totalTime", this.totalTime);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
}