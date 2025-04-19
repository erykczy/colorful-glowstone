package me.erykczy.colorfulglowstone.datagen;

import me.erykczy.colorfulglowstone.ColorfulGlowstone;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ColorfulGlowstone.MODID)
public class DatagenHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var fileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        // client
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, ColorfulGlowstone.MODID, fileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output, ColorfulGlowstone.MODID, fileHelper));

        // server
        var blockTagsProvider = generator.addProvider(event.includeServer(), new ModBlockTagsProvider(output, lookupProvider, ColorfulGlowstone.MODID, fileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(output, lookupProvider, blockTagsProvider.contentsGetter()));

        generator.addProvider(event.includeServer(), new ModRecipeProvider(output, lookupProvider));
        generator.addProvider(event.includeServer(), new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootSubProvider::new, LootContextParamSets.BLOCK)
        ), lookupProvider));
    }
}
