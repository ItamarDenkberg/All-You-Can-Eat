package io.github.itamardenkberg.allyoucaneat.mixins;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import io.github.itamardenkberg.allyoucaneat.common.blocks.MilkCauldronBlock;
import io.github.itamardenkberg.allyoucaneat.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

@Mixin(AbstractCauldronBlock.class)
public abstract class AbstractCauldronBlockMixin extends Block {
	private final Map<Item, CauldronInteraction> interactions;

	public AbstractCauldronBlockMixin(BlockBehaviour.Properties properties, Map<Item, CauldronInteraction> map) {
		super(properties);
		this.interactions = map;
	}

	/**
	 * InteractionResult use method.
	 * Filling a cauldron with milk when clicking with
	 * a milk bucket.
	 *
	 * @reason For the MilkCauldron block.
	 * @author ItamarDenkberg
	 */
	@Overwrite
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.isEmpty()) {
			return InteractionResult.PASS;
		} else {
			Item item = stack.getItem();
			if (item == Items.MILK_BUCKET) {
				if (!player.isCreative()) {
					player.setItemInHand(hand, new ItemStack(Items.BUCKET));
				}
				world.setBlock(pos, (BlockState) BlockInit.MILK_CAULDRON.get().defaultBlockState()
						.setValue(MilkCauldronBlock.LEVEL, 3), 11);
				world.playSound((Player) null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
				return InteractionResult.sidedSuccess(world.isClientSide);
			} else {
				CauldronInteraction cauldroninteraction = interactions.get(stack.getItem());
				return cauldroninteraction.interact(state, world, pos, player, hand, stack);
			}
		}
	}
}