package io.github.itamardenkberg.allyoucaneat.common.blocks;

import javax.annotation.Nullable;

import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;

public class FlammableRotatedPillarBlock extends RotatedPillarBlock {

	public FlammableRotatedPillarBlock(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return true;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return 5;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return 5;
	}

	@Override
	public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction,
			boolean simulate) {
		if (context.getItemInHand().getItem() instanceof AxeItem) {
			if (state.is(BlockInit.HAZEL_LOG.get())) {
				return BlockInit.STRIPPED_HAZEL_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}

			if (state.is(BlockInit.HAZEL_WOOD.get())) {
				return BlockInit.STRIPPED_HAZEL_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
			}
		}

		return super.getToolModifiedState(state, context, toolAction, simulate);
	}
}
