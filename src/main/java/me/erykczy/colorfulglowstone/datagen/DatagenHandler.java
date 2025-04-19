package me.erykczy.colorfulglowstone.datagen;

import me.erykczy.colorfulglowstone.ColorfulGlowstone;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ColorfulGlowstone.MODID)
public class DatagenHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var fileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, ColorfulGlowstone.MODID, fileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output, ColorfulGlowstone.MODID, fileHelper));
    }
}
