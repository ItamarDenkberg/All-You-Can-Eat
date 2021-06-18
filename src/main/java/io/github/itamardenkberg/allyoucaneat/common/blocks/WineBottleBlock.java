package io.github.itamardenkberg.allyoucaneat.common.blocks;

import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import io.github.itamardenkberg.allyoucaneat.core.init.StatInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class WineBottleBlock extends Block {
	public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_0_3;
	private static final VoxelShape SHAPE = Block.makeCuboidShape(6, 0, 6, 10, 16, 10);
	private static final VoxelShape INSIDE = makeCuboidShape(6.1, 0, 6.1, 9.899999999999999, 11, 9.9);

	public WineBottleBlock(Properties properties) {
		super(properties);
		this.setDefaultState((BlockState) ((BlockState) this.stateContainer.getBaseState()).with(LEVEL, 3));
	}

	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	public VoxelShape getRaytraceShape(BlockState state, IBlockReader reader, BlockPos pos) {
		return INSIDE;
	}

	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
			Hand hand, BlockRayTraceResult result) {
		ItemStack stack = player.getHeldItem(hand);
		if (stack.isEmpty()) {
			return ActionResultType.PASS;
		} else {
			int i = (Integer) state.get(LEVEL);
			Item item = stack.getItem();
			if (item == ItemInit.RED_WINE_GLASS.get() || item == ItemInit.WHITE_WINE_GLASS.get()) {
				if (i < 3 && !world.isRemote) {
					if (!player.abilities.isCreativeMode) {
						if (item == ItemInit.RED_WINE_GLASS.get()
								&& world.getBlockState(pos).getBlock() == BlockInit.RED_WINE_BOTTLE.get()) {
							player.setHeldItem(hand, new ItemStack(ItemInit.WINE_GLASS.get()));
							this.setWineLevel(world, pos, state, i + 1);
						} else if (item == ItemInit.WHITE_WINE_GLASS.get()
								&& world.getBlockState(pos).getBlock() == BlockInit.WHITE_WINE_BOTTLE.get()) {
							player.setHeldItem(hand, new ItemStack(ItemInit.WINE_GLASS.get()));
							this.setWineLevel(world, pos, state, i + 1);
						}
					}
					player.addStat(StatInit.FILL_WINE_BOTTLE);
					world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F,
							1.0F);
				}
				return ActionResultType.func_233537_a_(world.isRemote);
			} else if (item == ItemInit.WINE_GLASS.get()) {
				if (i > 0 && !world.isRemote) {
					if (!player.abilities.isCreativeMode) {
						if (world.getBlockState(pos).getBlock() == BlockInit.RED_WINE_BOTTLE.get()) {
							ItemStack newItem = new ItemStack(ItemInit.RED_WINE_GLASS.get());
							stack.shrink(1);
							if (stack.isEmpty()) {
								player.setHeldItem(hand, newItem);
							} else if (!player.inventory.addItemStackToInventory(newItem)) {
								player.dropItem(newItem, false);
							} else if (player instanceof ServerPlayerEntity) {
								((ServerPlayerEntity) player).sendContainerToPlayer(player.container);
							}
						} else if (world.getBlockState(pos).getBlock() == BlockInit.WHITE_WINE_BOTTLE.get()) {
							ItemStack newItem = new ItemStack(ItemInit.WHITE_WINE_GLASS.get());
							stack.shrink(1);
							if (stack.isEmpty()) {
								player.setHeldItem(hand, newItem);
							} else if (!player.inventory.addItemStackToInventory(newItem)) {
								player.dropItem(newItem, false);
							} else if (player instanceof ServerPlayerEntity) {
								((ServerPlayerEntity) player).sendContainerToPlayer(player.container);
							}
						}
					}
					player.addStat(StatInit.USE_WINE_BOTTLE);
					this.setWineLevel(world, pos, state, i - 1);
					world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F,
							1.0F);
				}
				return ActionResultType.func_233537_a_(world.isRemote);
			}
		}
		return null;

	}

	public void setWineLevel(World world, BlockPos pos, BlockState state, int i) {
		world.setBlockState(pos, (BlockState) state.with(LEVEL, MathHelper.clamp(i, 0, 3)), 2);
		world.updateComparatorOutputLevel(pos, this);
	}

	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
		return (Integer) state.get(LEVEL);
	}

	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(new Property[] { LEVEL });
	}
}
