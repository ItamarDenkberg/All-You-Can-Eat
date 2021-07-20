package io.github.itamardenkberg.allyoucaneat.core.init;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeInit {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, AllYouCanEat.MOD_ID);

//	public static final RegistryObject<TileEntityType<WinepressTileEntity>> WINEPRESS_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
//			.register("winepress", () -> TileEntityType.Builder
//					.create(WinepressTileEntity::new, BlockInit.WINEPRESS.get()).build(null));
}
