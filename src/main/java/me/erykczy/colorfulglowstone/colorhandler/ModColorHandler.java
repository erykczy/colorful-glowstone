package me.erykczy.colorfulglowstone.colorhandler;

import me.erykczy.colorfulglowstone.block.ModBlocks;
import me.erykczy.colorfulglowstone.block.ModItems;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.DyeColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public class ModColorHandler {
    public static void register(IEventBus bus) {
        bus.addListener(ModColorHandler::registerBlockColorHandler);
        bus.addListener(ModColorHandler::registerItemColorHandler);
    }

    private static void registerBlockColorHandler(RegisterColorHandlersEvent.Block event) {
        for(var entry : ModBlocks.GLOWSTONE_BLOCKS.entrySet()) {
            event.register((state, level, pos, tintIndex) -> getColorFromDye(entry.getKey()), entry.getValue().get());
        }
        for(var entry : ModBlocks.REDSTONE_LAMP_BLOCKS.entrySet()) {
            event.register((state, level, pos, tintIndex) -> getColorFromDye(entry.getKey()), entry.getValue().get());
        }
    }

    private static void registerItemColorHandler(RegisterColorHandlersEvent.Item event) {
        for(var entry : ModBlocks.GLOWSTONE_BLOCK_ITEMS.entrySet()) {
            event.register((stack, tintIndex) -> getColorFromDye(entry.getKey()), entry.getValue().get());
        }
        for(var entry : ModBlocks.REDSTONE_LAMP_BLOCK_ITEMS.entrySet()) {
            event.register((stack, tintIndex) -> getColorFromDye(entry.getKey()), entry.getValue().get());
        }
        for(var entry : ModItems.GLOWSTONE_DUST_ITEMS.entrySet()) {
            event.register((stack, tintIndex) -> getColorFromDye(entry.getKey()), entry.getValue().get());
        }
    }

    private static int getColorFromDye(DyeColor dyeColor) {
        return FastColor.ARGB32.opaque(dyeColor.getTextColor());
    }
}
