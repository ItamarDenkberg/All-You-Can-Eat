package io.github.itamardenkberg.allyoucaneat.common.blocks;

import java.util.Random;

import io.github.itamardenkberg.allyoucaneat.core.init.BlockStatePropertiesInit;
import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WhiteGrapeCropBlock extends CropBlock {
	public static final IntegerProperty GRAPE_AGE = BlockStatePropertiesInit.AGE_0_4;
	private static final VoxelShape[] SHAPE = new VoxelShape[] {
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };

	public WhiteGrapeCropBlock(Properties properties) {
		super(properties);
	}

	public IntegerProperty getAgeProperty() {
		return GRAPE_AGE;
	}

	public int getMaxAge() {
		return 4;
	}

	protected ItemLike getBaseSeedId() {
		return ItemInit.WHITE_GRAPE_SEEDS.get();
	}

	public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
		if (random.nextInt(3) != 0) {
			super.randomTick(state, world, pos, random);
		}

	}

	protected int getBonemealAgeIncrease(Level world) {
		return super.getBonemealAgeIncrease(world) / 3;
	}

	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(new Property[] { GRAPE_AGE });
	}

	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		return SHAPE[(Integer) state.getValue(this.getAgeProperty())];
	}
}