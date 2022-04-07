package io.github.itamardenkberg.allyoucaneat.core.init;

import java.util.Map;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LayeredCauldronBlock;

public class CauldronInteractionsInit {
	public static final Map<Item, CauldronInteraction> MILK_CAULDRON_INTERACTION = CauldronInteraction
			.newInteractionMap();
	public static final CauldronInteraction EMPTY_MILK_CAULDRON_INTERACTION = (state, world, pos, player, hand,
			stack) -> CauldronInteraction.fillBucket(state, world, pos, player, hand, stack,
					new ItemStack(Items.MILK_BUCKET),
					blockState -> blockState.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL);
	public static final CauldronInteraction FILL_MILK_CAULDRON_INTERACTION = (state, world, pos, player, hand,
			stack) -> CauldronInteraction.emptyBucket(world, pos, player, hand, stack,
					BlockInit.MILK_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3),
					SoundEvents.BUCKET_EMPTY);

	public static void registerCauldronInteractions() {
		CauldronInteraction.addDefaultInteractions(MILK_CAULDRON_INTERACTION);

		CauldronInteraction.EMPTY.put(Items.MILK_BUCKET, FILL_MILK_CAULDRON_INTERACTION);
		CauldronInteraction.WATER.put(Items.MILK_BUCKET, FILL_MILK_CAULDRON_INTERACTION);
		CauldronInteraction.LAVA.put(Items.MILK_BUCKET, FILL_MILK_CAULDRON_INTERACTION);
		CauldronInteraction.POWDER_SNOW.put(Items.MILK_BUCKET, FILL_MILK_CAULDRON_INTERACTION);

		MILK_CAULDRON_INTERACTION.put(Items.MILK_BUCKET, FILL_MILK_CAULDRON_INTERACTION);
		MILK_CAULDRON_INTERACTION.put(Items.BUCKET, EMPTY_MILK_CAULDRON_INTERACTION);
	}
}
