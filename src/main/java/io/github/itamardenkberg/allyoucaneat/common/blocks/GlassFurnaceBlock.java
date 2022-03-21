package io.github.itamardenkberg.allyoucaneat.common.blocks;

import java.util.Random;

import io.github.itamardenkberg.allyoucaneat.common.tileentities.GlassFurnaceTileEntity;
import io.github.itamardenkberg.allyoucaneat.core.init.TileEntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class GlassFurnaceBlock extends BaseEntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public GlassFurnaceBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(LIT,
				Boolean.valueOf(false)));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
	}

	@Override
	public BlockState rotate(BlockState pState, Rotation pRotation) {
		return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState pState, Mirror pMirror) {
		return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, LIT);
	}
	
	@Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

	public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
		if (state.getValue(LIT)) {
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY();
			double d2 = (double) pos.getZ() + 0.5D;
			if (random.nextDouble() < 0.1D) {
				world.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F,
						false);
			}

			Direction direction = state.getValue(FACING);
			Direction.Axis direction$axis = direction.getAxis();
			@SuppressWarnings("unused")
			double d3 = 0.52D;
			double d4 = random.nextDouble() * 0.6D - 0.3D;
			double d5 = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : d4;
			double d6 = random.nextDouble() * 6.0D / 16.0D;
			double d7 = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : d4;
			world.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
			world.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
		if (pState.getBlock() != pNewState.getBlock()) {
			BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
			if (blockEntity instanceof GlassFurnaceTileEntity) {
				((GlassFurnaceTileEntity) blockEntity).drops();
			}
		}
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
			BlockHitResult pHit) {
		if (!pLevel.isClientSide()) {
			BlockEntity entity = pLevel.getBlockEntity(pPos);
			if (entity instanceof GlassFurnaceTileEntity) {
				NetworkHooks.openGui(((ServerPlayer) pPlayer), (GlassFurnaceTileEntity) entity, pPos);
			} else {
				throw new IllegalStateException("Container provider is missing");
			}
		}

		return InteractionResult.sidedSuccess(pLevel.isClientSide());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new GlassFurnaceTileEntity(pPos, pState);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState,
			BlockEntityType<T> pBlockEntityType) {
		return createTickerHelper(pBlockEntityType, TileEntitiesInit.GLASS_FURNACE.get(), GlassFurnaceTileEntity::tick);
	}
}