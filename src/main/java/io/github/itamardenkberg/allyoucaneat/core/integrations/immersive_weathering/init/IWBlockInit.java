package io.github.itamardenkberg.allyoucaneat.core.integrations.immersive_weathering.init;

import java.util.List;

import com.ordana.immersive_weathering.common.blocks.ModBlocks;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.blocks.LeafPileBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IWBlockInit extends ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			AllYouCanEat.MOD_ID);

	public static void init() {
		if (ModList.get().isLoaded("immersive_weathering")) {
			HAZEL_LEAF_PILE = BLOCKS.register("hazel_leaf_pile",
					() -> new LeafPileBlock(BlockBehaviour.Properties.copy(ModBlocks.OAK_LEAF_PILE.get()), false, false,
							List.of(IWParticleInit.HAZEL_LEAF)));
		}
	}

	public static RegistryObject<Block> HAZEL_LEAF_PILE;
}
