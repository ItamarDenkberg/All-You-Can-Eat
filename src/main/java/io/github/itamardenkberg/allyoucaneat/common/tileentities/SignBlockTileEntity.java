package io.github.itamardenkberg.allyoucaneat.common.tileentities;

import io.github.itamardenkberg.allyoucaneat.core.init.TileEntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SignBlockTileEntity extends SignBlockEntity {

	public SignBlockTileEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public BlockEntityType<?> getType() {
		return TileEntitiesInit.SIGN_TILE_ENTITIES.get();
	}
}