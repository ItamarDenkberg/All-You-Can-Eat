package io.github.itamardenkberg.allyoucaneat.common.blocks;

import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import io.github.itamardenkberg.allyoucaneat.core.init.StatInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EmptyWineBottleBlock extends Block {
	private static final VoxelShape SHAPE = Block.makeCuboidShape(6, 0, 6, 10, 16, 10);
	public static final BooleanProperty WATERLOGGED;

	public EmptyWineBottleBlock(Properties properties) {
		super(properties);
		this.setDefaultState((BlockState) ((BlockState) this.stateContainer.getBaseState()).with(WATERLOGGED, false));
	}

	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState blockState, IWorld world,
			BlockPos pos, BlockPos blockPos) {
		if ((Boolean) state.get(WATERLOGGED)) {
			world.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return super.updatePostPlacement(state, direction, blockState, world, pos, blockPos);
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return (Boolean) state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
			Hand hand, BlockRayTraceResult result) {
		ItemStack stack = player.getHeldItem(hand);
		if (stack.isEmpty()) {
			return ActionResultType.PASS;
		} else {
			Item item = stack.getItem();
			if (item == ItemInit.RED_WINE_GLASS.get() || item == ItemInit.WHITE_WINE_GLASS.get()) {
				if (!player.abilities.isCreativeMode) {
					if (item == ItemInit.RED_WINE_GLASS.get()) {
						player.setHeldItem(hand, new ItemStack(ItemInit.WINE_GLASS.get()));
						world.setBlockState(pos, (BlockState) BlockInit.RED_WINE_BOTTLE.get().getDefaultState()
								.with(WineBottleBlock.LEVEL, 1), 11);
						world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS,
								1.0F, 1.0F);
					} else if (item == ItemInit.WHITE_WINE_GLASS.get()) {
						player.setHeldItem(hand, new ItemStack(ItemInit.WINE_GLASS.get()));
						world.setBlockState(pos, (BlockState) BlockInit.WHITE_WINE_BOTTLE.get().getDefaultState()
								.with(WineBottleBlock.LEVEL, 1), 11);
						world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS,
								1.0F, 1.0F);
					}
				}
				player.addStat(StatInit.FILL_WINE_BOTTLE);
				return ActionResultType.func_233537_a_(world.isRemote);
			} else {
				return ActionResultType.PASS;
			}
		}
	}

	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(new Property[] { WATERLOGGED });
	}

	static {
		WATERLOGGED = BlockStateProperties.WATERLOGGED;
	}
}
