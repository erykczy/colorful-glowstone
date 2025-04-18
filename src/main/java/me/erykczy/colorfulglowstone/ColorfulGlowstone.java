package me.erykczy.colorfulglowstone;

import com.mojang.logging.LogUtils;
import me.erykczy.colorfulglowstone.block.ModBlocks;
import me.erykczy.colorfulglowstone.creativetab.ModCreativeTabs;
import net.minecraft.world.item.DyeColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.slf4j.Logger;

@Mod(ColorfulGlowstone.MODID)
public class ColorfulGlowstone
{
    public static final String MODID = "colorfulglowstone";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ColorfulGlowstone(IEventBus modEventBus, ModContainer modContainer)
    {
        ModBlocks.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        modEventBus.addListener(ColorfulGlowstone::registerBlockColorHandler);
        modEventBus.addListener(ColorfulGlowstone::registerItemColorHandler);
    }

    private static void registerBlockColorHandler(RegisterColorHandlersEvent.Block event) {
        for(var dyeColor : DyeColor.values()) {
            event.register((state, level, pos, tintIndex) -> dyeColor.getTextColor(), ModBlocks.GLOWSTONES.get(dyeColor).get());
        }
    }

    private static void registerItemColorHandler(RegisterColorHandlersEvent.Item event) {
        for(var dyeColor : DyeColor.values()) {
            event.register((stack, tintIndex) -> dyeColor.getTextColor(), ModBlocks.GLOWSTONE_BLOCK_ITEMS.get(dyeColor).get());
        }
    }
}
