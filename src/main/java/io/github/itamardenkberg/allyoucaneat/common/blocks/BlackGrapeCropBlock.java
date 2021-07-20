package io.github.itamardenkberg.allyoucaneat.common.blocks;

import java.util.Random;

import io.github.itamardenkberg.allyoucaneat.core.init.BlockStatePropertiesInit;
import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BlackGrapeCropBlock extends CropsBlock {
	public static final IntegerProperty GRAPE_AGE = BlockStatePropertiesInit.AGE_0_4;
	private static final VoxelShape[] SHAPE = new VoxelShape[] {
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };

	public BlackGrapeCropBlock(Properties properties) {
		super(properties);
	}

	public IntegerProperty getAgeProperty() {
		return GRAPE_AGE;
	}

	public int getMaxAge() {
		return 4;
	}

	protected IItemProvider getSeedsItem() {
		return ItemInit.BLACK_GRAPE_SEEDS.get();
	}

	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (random.nextInt(3) != 0) {
			super.randomTick(state, world, pos, random);
		}

	}

	protected int getBonemealAgeIncrease(World world) {
		return super.getBonemealAgeIncrease(world) / 3;
	}

	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(new Property[] { GRAPE_AGE });
	}

	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE[(Integer) state.get(this.getAgeProperty())];
	}
}