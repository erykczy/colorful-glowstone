package me.erykczy.colorfulglowstone.datagen;

import me.erykczy.colorfulglowstone.block.ModBlocks;
import me.erykczy.colorfulglowstone.block.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Items.DYED_BLUE).add(dyed(DyeColor.BLUE));
        tag(Tags.Items.DYED_BROWN).add(dyed(DyeColor.BROWN));
        tag(Tags.Items.DYED_CYAN).add(dyed(DyeColor.CYAN));
        tag(Tags.Items.DYED_GRAY).add(dyed(DyeColor.GRAY));
        tag(Tags.Items.DYED_GREEN).add(dyed(DyeColor.GREEN));
        tag(Tags.Items.DYED_LIGHT_BLUE).add(dyed(DyeColor.LIGHT_BLUE));
        tag(Tags.Items.DYED_LIGHT_GRAY).add(dyed(DyeColor.LIGHT_GRAY));
        tag(Tags.Items.DYED_LIME).add(dyed(DyeColor.LIME));
        tag(Tags.Items.DYED_MAGENTA).add(dyed(DyeColor.MAGENTA));
        tag(Tags.Items.DYED_ORANGE).add(dyed(DyeColor.ORANGE));
        tag(Tags.Items.DYED_PINK).add(dyed(DyeColor.PINK));
        tag(Tags.Items.DYED_PURPLE).add(dyed(DyeColor.PURPLE));
        tag(Tags.Items.DYED_RED).add(dyed(DyeColor.RED));
        tag(Tags.Items.DYED_WHITE).add(dyed(DyeColor.WHITE));
        tag(Tags.Items.DYED_YELLOW).add(dyed(DyeColor.YELLOW));

        var glowstone_dusts = tag(Tags.Items.DUSTS_GLOWSTONE);
        for(var entry : ModItems.GLOWSTONE_DUST_ITEMS.entrySet())
            glowstone_dusts.add(entry.getValue().get());
    }

    private Item[] dyed(DyeColor color) {
        return new Item[]{ModItems.GLOWSTONE_DUST_ITEMS.get(color).get(), ModBlocks.GLOWSTONE_BLOCK_ITEMS.get(color).get(), ModBlocks.REDSTONE_LAMP_BLOCK_ITEMS.get(color).get()};
    }
}
