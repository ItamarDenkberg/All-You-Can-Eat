package io.github.itamardenkberg.allyoucaneat.common.blocks;

import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;

public class FlammableRotatedPillarBlock extends RotatedPillarBlock {

	public FlammableRotatedPillarBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return true;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 5;
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, Level world, BlockPos pos, Player player, ItemStack stack,
			ToolAction toolAction) {
		if (stack.getItem() instanceof AxeItem) {
			if (state.is(BlockInit.HAZEL_LOG.get())) {
				return BlockInit.STRIPPED_HAZEL_LOG.get().defaultBlockState();
			}
			if (state.is(BlockInit.HAZEL_WOOD.get())) {
				return BlockInit.STRIPPED_HAZEL_WOOD.get().defaultBlockState();
			}
		}

		return super.getToolModifiedState(state, world, pos, player, stack, toolAction);
	}
}
