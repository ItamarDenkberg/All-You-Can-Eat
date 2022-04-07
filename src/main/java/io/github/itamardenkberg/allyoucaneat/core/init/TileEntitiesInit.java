package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.common.tileentities.MilkCauldronTileEntity;
import io.github.itamardenkberg.allyoucaneat.common.tileentities.SignBlockTileEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TileEntitiesInit {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITES = DeferredRegister
			.create(ForgeRegistries.BLOCK_ENTITIES, AllYouCanEat.MOD_ID);

	public static final RegistryObject<BlockEntityType<SignBlockTileEntity>> SIGN_TILE_ENTITIES = BLOCK_ENTITES
			.register("sign_block_entity",
					() -> BlockEntityType.Builder
							.of(SignBlockTileEntity::new, BlockInit.HAZEL_WALL_SIGN.get(), BlockInit.HAZEL_SIGN.get())
							.build(null));

	public static final RegistryObject<BlockEntityType<MilkCauldronTileEntity>> MILK_CAULDRON_TILE_ENTITY = BLOCK_ENTITES
			.register("milk_cauldron_block_entity", () -> BlockEntityType.Builder
					.of(MilkCauldronTileEntity::new, BlockInit.MILK_CAULDRON.get()).build(null));
}
