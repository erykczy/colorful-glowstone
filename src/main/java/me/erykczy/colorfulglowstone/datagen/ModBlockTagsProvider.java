package me.erykczy.colorfulglowstone.datagen;

import me.erykczy.colorfulglowstone.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Blocks.DYED_BLUE).add(dyed(DyeColor.BLUE));
        tag(Tags.Blocks.DYED_BROWN).add(dyed(DyeColor.BROWN));
        tag(Tags.Blocks.DYED_CYAN).add(dyed(DyeColor.CYAN));
        tag(Tags.Blocks.DYED_GRAY).add(dyed(DyeColor.GRAY));
        tag(Tags.Blocks.DYED_GREEN).add(dyed(DyeColor.GREEN));
        tag(Tags.Blocks.DYED_LIGHT_BLUE).add(dyed(DyeColor.LIGHT_BLUE));
        tag(Tags.Blocks.DYED_LIGHT_GRAY).add(dyed(DyeColor.LIGHT_GRAY));
        tag(Tags.Blocks.DYED_LIME).add(dyed(DyeColor.LIME));
        tag(Tags.Blocks.DYED_MAGENTA).add(dyed(DyeColor.MAGENTA));
        tag(Tags.Blocks.DYED_ORANGE).add(dyed(DyeColor.ORANGE));
        tag(Tags.Blocks.DYED_PINK).add(dyed(DyeColor.PINK));
        tag(Tags.Blocks.DYED_PURPLE).add(dyed(DyeColor.PURPLE));
        tag(Tags.Blocks.DYED_RED).add(dyed(DyeColor.RED));
        tag(Tags.Blocks.DYED_WHITE).add(dyed(DyeColor.WHITE));
        tag(Tags.Blocks.DYED_YELLOW).add(dyed(DyeColor.YELLOW));
    }

    private Block[] dyed(DyeColor color) {
        return new Block[]{ModBlocks.GLOWSTONE_BLOCKS.get(color).get(), ModBlocks.REDSTONE_LAMP_BLOCKS.get(color).get()};
    }
}
