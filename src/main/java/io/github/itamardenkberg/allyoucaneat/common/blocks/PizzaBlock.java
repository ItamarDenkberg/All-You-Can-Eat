package io.github.itamardenkberg.allyoucaneat.common.blocks;

import java.util.stream.Stream;

import io.github.itamardenkberg.allyoucaneat.core.init.BlockStatePropertiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PizzaBlock extends Block {
	public static final int MAX_BITES = 3;
	public static final IntegerProperty BITES = BlockStatePropertiesInit.BITES;
	public static final int FULL_CAKE_SIGNAL = getOutputSignal(0);
	protected static final float AABB_OFFSET = 1.0F;
	protected static final float AABB_SIZE_PER_BITE = 2.0F;
	protected static final VoxelShape SHAPE_0 = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D);
	protected static final VoxelShape SHAPE_1 = Stream.of(Block.box(1, 0, 1, 15, 3, 8), Block.box(8, 0, 8, 15, 3, 15))
			.reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	protected static final VoxelShape SHAPE_2 = Block.box(1, 0, 1, 15, 3, 8);
	protected static final VoxelShape SHAPE_3 = Block.box(1, 0, 1, 8, 3, 8);

	public PizzaBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(BITES, Integer.valueOf(0)));
	}

	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		switch (state.getValue(BITES)) {
		case 0:
			return SHAPE_0;
		case 1:
			return SHAPE_1;
		case 2:
			return SHAPE_2;
		case 3:
			return SHAPE_3;
		default:
			return SHAPE_0;
		}
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (world.isClientSide) {
			if (eat(world, pos, state, player).consumesAction()) {
				return InteractionResult.SUCCESS;
			}

			if (itemstack.isEmpty()) {
				return InteractionResult.CONSUME;
			}
		}

		return eat(world, pos, state, player);
	}

	protected static InteractionResult eat(LevelAccessor accessor, BlockPos pos, BlockState state, Player player) {
		if (!player.canEat(false)) {
			return InteractionResult.PASS;
		} else {
			player.awardStat(Stats.EAT_CAKE_SLICE);
			player.getFoodData().eat(5, 0.3F);
			int i = state.getValue(BITES);
			accessor.gameEvent(player, GameEvent.EAT, pos);
			if (i < 3) {
				accessor.setBlock(pos, state.setValue(BITES, Integer.valueOf(i + 1)), 3);
			} else {
				accessor.removeBlock(pos, false);
				accessor.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
			}

			return InteractionResult.SUCCESS;
		}
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState state2, LevelAccessor accessor,
			BlockPos pos, BlockPos pos2) {
		return direction == Direction.DOWN && !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState()
				: super.updateShape(state, direction, state2, accessor, pos, pos2);
	}

	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		return reader.getBlockState(pos.below()).getMaterial().isSolid();
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BITES);
	}

	public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
		return getOutputSignal(state.getValue(BITES));
	}

	public static int getOutputSignal(int signal) {
		return (4 - signal) * 2;
	}

}
