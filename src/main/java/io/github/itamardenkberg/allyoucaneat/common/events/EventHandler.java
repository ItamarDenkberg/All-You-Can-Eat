package io.github.itamardenkberg.allyoucaneat.common.events;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import io.github.itamardenkberg.allyoucaneat.AllYouCanEat;
import io.github.itamardenkberg.allyoucaneat.core.config.CommonConfig;
import io.github.itamardenkberg.allyoucaneat.core.init.ItemInit;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.event.level.BlockEvent.BreakEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = AllYouCanEat.MOD_ID, bus = Bus.FORGE)
public class EventHandler {

	@SubscribeEvent
	public static void onBlockBroken(BreakEvent.BreakEvent event)
			throws IllegalArgumentException, IllegalAccessException {
		CommonConfig config = new CommonConfig();
		if (!event.getLevel().isClientSide()) {
			if ((event.getPlayer().getMainHandItem().getItem() != Items.SHEARS) && (!event.getPlayer().isCreative())) {
				if (event.getState().getBlock() == Blocks.GRASS || event.getState().getBlock() == Blocks.TALL_GRASS
						|| event.getState().getBlock() == Blocks.FERN
						|| event.getState().getBlock() == Blocks.LARGE_FERN) {
					for (Item seed : ItemInit.seeds) {
						Field[] fields = CommonConfig.class.getFields();
						Field[] filteredList = Arrays.stream(fields).filter(x -> x.getName().contains(seed.toString()))
								.toArray(Field[]::new);
						for (Field fieldInfo : filteredList) {
							if (fieldInfo.getName().contains(seed.toString())
									&& fieldInfo.getType() == ConfigValue.class) {
								@SuppressWarnings("unchecked")
								ConfigValue<? extends Integer> value = (ConfigValue<? extends Integer>) fieldInfo
										.get(config);
								if (Math.random() <= ((int) value.get()) / 100.0d) {
									event.getLevel().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), 2);
									event.getLevel()
											.addFreshEntity(new ItemEntity((Level) event.getLevel(),
													event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(),
													new ItemStack(seed)));
								}
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void addCustomTrades(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.FARMER) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(ItemInit.STRAWBERRY.get(), 18),
					new ItemStack(Items.EMERALD, 1), 16, 2, 0.05F));
			trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(ItemInit.TOMATO.get(), 22),
					new ItemStack(Items.EMERALD, 1), 16, 2, 0.05F));
			trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(ItemInit.BLACK_GRAPE.get(), 20),
					new ItemStack(Items.EMERALD, 1), 16, 2, 0.05F));
			trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack(ItemInit.WHITE_GRAPE.get(), 20),
					new ItemStack(Items.EMERALD, 1), 16, 2, 0.05F));
		}

		if (event.getType() == VillagerProfession.CLERIC) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			trades.get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 4),
					new ItemStack(ItemInit.PARROTFRUIT.get(), 3), 12, 20, 0.05F));
		}
	}
}
