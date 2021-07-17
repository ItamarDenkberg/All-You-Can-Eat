package io.github.itamardenkberg.allyoucaneat.common.blocks;

import java.util.Random;

import io.github.itamardenkberg.allyoucaneat.common.tileentities.WinepressTileEntity;
import io.github.itamardenkberg.allyoucaneat.core.init.SoundEventInit;
import io.github.itamardenkberg.allyoucaneat.core.init.StatInit;
import io.github.itamardenkberg.allyoucaneat.core.init.TileEntityTypeInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class WinepressBlock extends BaseHorizontalBlock {
	public static final BooleanProperty PRESSING = BooleanProperty.create("pressing");

	public WinepressBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(PRESSING, false));
		runCalculation(SHAPE);
	}

	private static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6, 16, 0, 10, 23, 16),
			Block.makeCuboidShape(0, 0, 0, 16, 16, 16), IBooleanFunction.OR);

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return SHAPES.get(this).get(state.get(HORIZONTAL_FACING));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypeInit.WINEPRESS_TILE_ENTITY_TYPE.get().create();
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(PRESSING);
	}

	protected void interactWith(World world, BlockPos pos, PlayerEntity entity) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof WinepressTileEntity) {
			entity.openContainer((INamedContainerProvider) tile);
			entity.addStat(StatInit.INTERACT_WITH_WINEPRESS);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
			Hand hand, BlockRayTraceResult hit) {
		if (world != null && !world.isRemote()) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof WinepressTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (WinepressTileEntity) tile, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return super.onBlockActivated(state, world, pos, player, hand, hit);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moving) {
		if (!state.matchesBlock(newState.getBlock())) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof IInventory) {
                InventoryHelper.dropInventoryItems(world, pos, (IInventory)tile);
                world.updateComparatorOutputLevel(pos, this);
            }

            super.onReplaced(state, world, pos, newState, moving);
        }
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
		return Container.calcRedstone(world.getTileEntity(pos));
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
		if ((Boolean) state.get(PRESSING)) {
			double d = (double) pos.getX() + 0.5D;
			double e = (double) pos.getY();
			double f = (double) pos.getZ() + 0.5D;
			if (random.nextDouble() < 0.1D) {
				world.playSound(d, e, f, SoundEventInit.BLOCK_WINEPRESS_PRESSING, SoundCategory.BLOCKS, 1.0F, 1.0F,
						false);
			}

			Direction direction = (Direction) state.get(HORIZONTAL_FACING);
			Axis axis = direction.getAxis();
			@SuppressWarnings("unused")
			double g = 0.52D;
			double h = random.nextDouble() * 0.6D - 0.3D;
			double i = axis == Axis.X ? (double) direction.getXOffset() * 0.52D : h;
			double j = random.nextDouble() * 6.0D / 16.0D;
			double k = axis == Axis.Z ? (double) direction.getZOffset() * 0.52D : h;
			world.addParticle(ParticleTypes.DRIPPING_WATER, d + i, e + j, f + k, 0.0D, 0.0D, 0.0D);
		}
	}
}
