package io.github.itamardenkberg.allyoucaneat.common.blocks;

import java.util.Map;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import io.github.itamardenkberg.allyoucaneat.common.tileentities.MilkCauldronTileEntity;
import io.github.itamardenkberg.allyoucaneat.core.init.TileEntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MilkCauldronBlock extends LayeredCauldronBlock implements EntityBlock {

	public MilkCauldronBlock(Properties properties, Predicate<Biome.Precipitation> predicate,
			Map<Item, CauldronInteraction> map) {
		super(properties, predicate, map);
	}

	@Override
	public @NotNull Item asItem() {
		return Items.CAULDRON;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return new MilkCauldronTileEntity(pos, state);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, @NotNull BlockState state,
			@NotNull BlockEntityType<T> type) {
		return world.isClientSide ? null
				: createTickerHelper(type, TileEntitiesInit.MILK_CAULDRON_TILE_ENTITY.get(),
						MilkCauldronTileEntity::tick);
	}

	@Nullable
	private static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(
			BlockEntityType<A> entityType, BlockEntityType<E> entityType2, BlockEntityTicker<? super E> ticker) {
		return entityType2 == entityType ? (BlockEntityTicker<A>) ticker : null;
	}

	public void entityInside(BlockState state, Level world, BlockPos pos, LivingEntity entity) {
		if (this.isEntityInsideContent(state, pos, entity)) {
			entity.curePotionEffects(null);
		}

	}
}