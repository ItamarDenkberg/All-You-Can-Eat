package io.github.itamardenkberg.allyoucaneat.core.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;

public class SoundEventInit {
	public static final SoundEvent BLOCK_WINEPRESS_PRESSING = register("block.winepress.pressing");
	
	@SuppressWarnings("deprecation")
	private static SoundEvent register(String string) {
		return (SoundEvent) Registry.register(Registry.SOUND_EVENT, string,
				new SoundEvent(new ResourceLocation(string)));
	}
}
