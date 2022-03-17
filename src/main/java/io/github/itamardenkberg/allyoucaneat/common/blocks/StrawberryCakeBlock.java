package io.github.itamardenkberg.allyoucaneat.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StrawberryCakeBlock extends CakeBlock {
	public static final IntegerProperty BITES = BlockStateProperties.BITES;
	protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[] {
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(3.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D),
			Block.box(5.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(7.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D),
			Block.box(9.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), Block.box(11.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D),
			Block.box(13.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D) };

	public StrawberryCakeBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(BITES, Integer.valueOf(0)));
	}

	public InteractionResult use(BlockState state, Level world, BlockPos blockPos, Player player,
			InteractionHand interactionHand, BlockHitResult hitResult) {
		ItemStack itemStack = player.getItemInHand(interactionHand);
		Item item = itemStack.getItem();
		if (itemStack.is(ItemTags.CANDLES) && (Integer) state.getValue(BITES) == 0) {
			Block block = Block.byItem(item);
			if (block instanceof CandleBlock) {
				if (!player.isCreative()) {
					itemStack.shrink(1);
				}

				world.playSound((Player) null, blockPos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
				world.setBlockAndUpdate(blockPos, CandleStrawberryCakeBlock.byCandle(block));
				world.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
				player.awardStat(Stats.ITEM_USED.get(item));
				return InteractionResult.SUCCESS;
			}
		}

		if (world.isClientSide) {
			if (eat(world, blockPos, state, player).consumesAction()) {
				return InteractionResult.SUCCESS;
			}

			if (itemStack.isEmpty()) {
				return InteractionResult.CONSUME;
			}
		}

		return eat(world, blockPos, state, player);
	}

	protected static InteractionResult eat(LevelAccessor levelAccessor, BlockPos blockPos, BlockState p_51188_,
			Player player) {
		if (!player.canEat(false)) {
			return InteractionResult.PASS;
		} else {
			player.awardStat(Stats.EAT_CAKE_SLICE);
			player.getFoodData().eat(2, 0.1F);
			int i = p_51188_.getValue(BITES);
			levelAccessor.gameEvent(player, GameEvent.EAT, blockPos);
			if (i < 6) {
				levelAccessor.setBlock(blockPos, p_51188_.setValue(BITES, Integer.valueOf(i + 1)), 3);
			} else {
				levelAccessor.removeBlock(blockPos, false);
				levelAccessor.gameEvent(player, GameEvent.BLOCK_DESTROY, blockPos);
			}

			return InteractionResult.SUCCESS;
		}
	}

}