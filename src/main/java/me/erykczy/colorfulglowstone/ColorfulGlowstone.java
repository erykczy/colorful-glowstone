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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mod(ColorfulGlowstone.MODID)
public class ColorfulGlowstone
{
    public static final String MODID = "colorfulglowstone";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final List<DyeColor> SUPPORTED_DYE_COLORS = Collections.unmodifiableList(Arrays.asList(
        DyeColor.WHITE,
        DyeColor.LIGHT_GRAY,
        DyeColor.GRAY,
        DyeColor.BROWN,
        DyeColor.RED,
        DyeColor.ORANGE,
        DyeColor.YELLOW,
        DyeColor.LIME,
        DyeColor.GREEN,
        DyeColor.CYAN,
        DyeColor.LIGHT_BLUE,
        DyeColor.BLUE,
        DyeColor.PURPLE,
        DyeColor.MAGENTA,
        DyeColor.PINK
    ));

    public ColorfulGlowstone(IEventBus modEventBus, ModContainer modContainer)
    {
        ModBlocks.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
    }
}
