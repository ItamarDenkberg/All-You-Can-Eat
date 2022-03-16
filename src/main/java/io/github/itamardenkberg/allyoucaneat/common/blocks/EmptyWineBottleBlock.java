package io.github.itamardenkberg.allyoucaneat.common.blocks;

import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import io.github.itamardenkberg.allyoucaneat.core.init.StatInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EmptyWineBottleBlock extends Block implements SimpleWaterloggedBlock {
	private static final VoxelShape SHAPE = Block.box(6, 0, 6, 10, 16, 10);
	public static final BooleanProperty WATERLOGGED;

	public EmptyWineBottleBlock(Properties properties) {
		super(properties);
		this.registerDefaultState((BlockState) ((BlockState) this.stateDefinition.any()).setValue(WATERLOGGED, false));
	}

	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return (Boolean) state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.isEmpty()) {
			return InteractionResult.PASS;
		} else {
			Item item = stack.getItem();
			if (item == ItemInit.RED_WINE_GLASS.get() || item == ItemInit.WHITE_WINE_GLASS.get()) {
				if (item == ItemInit.RED_WINE_GLASS.get()) {
					player.setItemInHand(hand, new ItemStack(ItemInit.WINE_GLASS.get()));
					if (world.getBlockState(pos).getValue(WATERLOGGED)) {
						world.setBlock(pos, (BlockState) BlockInit.RED_WINE_BOTTLE.get().defaultBlockState()
								.setValue(WineBottleBlock.LEVEL, 1).setValue(WATERLOGGED, true), 11);
					} else {
						world.setBlock(pos, (BlockState) BlockInit.RED_WINE_BOTTLE.get().defaultBlockState()
								.setValue(WineBottleBlock.LEVEL, 1), 11);
					}
					world.playSound((Player) null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
				} else if (item == ItemInit.WHITE_WINE_GLASS.get()) {
					player.setItemInHand(hand, new ItemStack(ItemInit.WINE_GLASS.get()));
					if (world.getBlockState(pos).getValue(WATERLOGGED)) {
						world.setBlock(pos, (BlockState) BlockInit.WHITE_WINE_BOTTLE.get().defaultBlockState()
								.setValue(WineBottleBlock.LEVEL, 1).setValue(WATERLOGGED, true), 11);
					} else {
						world.setBlock(pos, (BlockState) BlockInit.WHITE_WINE_BOTTLE.get().defaultBlockState()
								.setValue(WineBottleBlock.LEVEL, 1), 11);
					}
					world.playSound((Player) null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
				}
				player.awardStat(StatInit.FILL_WINE_BOTTLE);
				return InteractionResult.sidedSuccess(world.isClientSide);
			} else if (item == ItemInit.RED_WINE_BUCKET.get() || item == ItemInit.WHITE_WINE_BUCKET.get()) {
				if (item == ItemInit.WHITE_WINE_BUCKET.get()) {
					player.setItemInHand(hand, new ItemStack(Items.BUCKET));
					if (world.getBlockState(pos).getValue(WATERLOGGED)) {
						world.setBlock(pos, (BlockState) BlockInit.WHITE_WINE_BOTTLE.get().defaultBlockState()
								.setValue(WineBottleBlock.LEVEL, 2).setValue(WATERLOGGED, true), 11);
					} else {
						world.setBlock(pos, (BlockState) BlockInit.WHITE_WINE_BOTTLE.get().defaultBlockState()
								.setValue(WineBottleBlock.LEVEL, 2), 11);
					}
					world.playSound((Player) null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
				} else if (item == ItemInit.RED_WINE_BUCKET.get()) {
					player.setItemInHand(hand, new ItemStack(Items.BUCKET));
					if (world.getBlockState(pos).getValue(WATERLOGGED)) {
						world.setBlock(pos, (BlockState) BlockInit.RED_WINE_BOTTLE.get().defaultBlockState()
								.setValue(WineBottleBlock.LEVEL, 2).setValue(WATERLOGGED, true), 11);
					} else {
						world.setBlock(pos, (BlockState) BlockInit.RED_WINE_BOTTLE.get().defaultBlockState()
								.setValue(WineBottleBlock.LEVEL, 2), 11);
					}
					world.playSound((Player) null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
				}
				player.awardStat(StatInit.FILL_WINE_BOTTLE);
				return InteractionResult.sidedSuccess(world.isClientSide);
			} else {
				return InteractionResult.PASS;
			}
		}

	}

	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(new Property[] { WATERLOGGED });
	}

	static {
		WATERLOGGED = BlockStateProperties.WATERLOGGED;
	}
}
