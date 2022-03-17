package io.github.itamardenkberg.allyoucaneat.common.blocks;

import java.util.Random;

import io.github.itamardenkberg.allyoucaneat.core.init.BlockStatePropertiesInit;
import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StrawberryBushBlock extends BushBlock implements BonemealableBlock {
	public static final int MAX_AGE = 3;
	public static final IntegerProperty AGE = BlockStatePropertiesInit.AGE_0_3;
	private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public StrawberryBushBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
	}

	public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
		return new ItemStack(ItemInit.STRAWBERRY.get());
	}

	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		if (state.getValue(AGE) == 0) {
			return SAPLING_SHAPE;
		} else {
			return state.getValue(AGE) < 3 ? MID_GROWTH_SHAPE : super.getShape(state, getter, pos, context);
		}
	}

	public boolean isRandomlyTicking(BlockState state) {
		return state.getValue(AGE) < 3;
	}

	public void randomTick(BlockState state, ServerLevel server, BlockPos pos, Random random) {
		int i = state.getValue(AGE);
		if (i < 3 && server.getRawBrightness(pos.above(), 0) >= 9
				&& net.minecraftforge.common.ForgeHooks.onCropsGrowPre(server, pos, state, random.nextInt(5) == 0)) {
			server.setBlock(pos, state.setValue(AGE, Integer.valueOf(i + 1)), 2);
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(server, pos, state);
		}

	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		int i = state.getValue(AGE);
		boolean flag = i == 3;
		if (!flag && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
			return InteractionResult.PASS;
		} else if (i > 1) {
			int j = 1 + world.random.nextInt(2);
			popResource(world, pos, new ItemStack(ItemInit.STRAWBERRY.get(), j + (flag ? 1 : 0)));
			world.playSound((Player) null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F,
					0.8F + world.random.nextFloat() * 0.4F);
			world.setBlock(pos, state.setValue(AGE, Integer.valueOf(1)), 2);
			return InteractionResult.sidedSuccess(world.isClientSide);
		} else {
			return super.use(state, world, pos, player, hand, result);
		}
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean valid) {
		return state.getValue(AGE) < 3;
	}

	public boolean isBonemealSuccess(Level world, Random random, BlockPos pos, BlockState state) {
		return true;
	}

	public void performBonemeal(ServerLevel world, Random random, BlockPos pos, BlockState state) {
		int i = Math.min(3, state.getValue(AGE) + 1);
		world.setBlock(pos, state.setValue(AGE, Integer.valueOf(i)), 2);
	}
}
