package me.erykczy.colorfulglowstone;

import com.mojang.logging.LogUtils;
import me.erykczy.colorfulglowstone.block.ModBlocks;
import me.erykczy.colorfulglowstone.block.ModItems;
import me.erykczy.colorfulglowstone.colorhandler.ModColorHandler;
import me.erykczy.colorfulglowstone.creativetab.ModCreativeTabs;
import net.minecraft.world.item.DyeColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

import java.util.List;

@Mod(ColorfulGlowstone.MODID)
public class ColorfulGlowstone
{
    public static final String MODID = "colorful_glowstone";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final List<DyeColor> SUPPORTED_DYE_COLORS = List.of(DyeColor.WHITE, DyeColor.LIGHT_GRAY, DyeColor.GRAY, DyeColor.BROWN, DyeColor.RED, DyeColor.ORANGE, DyeColor.YELLOW, DyeColor.LIME, DyeColor.GREEN, DyeColor.CYAN, DyeColor.LIGHT_BLUE, DyeColor.BLUE, DyeColor.PURPLE, DyeColor.MAGENTA, DyeColor.PINK);

    public ColorfulGlowstone(IEventBus modEventBus, ModContainer modContainer)
    {
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModColorHandler.register(modEventBus);
    }
}
