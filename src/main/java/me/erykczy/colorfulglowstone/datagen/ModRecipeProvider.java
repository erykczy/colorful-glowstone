package me.erykczy.colorfulglowstone.datagen;

import me.erykczy.colorfulglowstone.ColorfulGlowstone;
import me.erykczy.colorfulglowstone.block.ModBlocks;
import me.erykczy.colorfulglowstone.block.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    private static final HashMap<DyeColor, Item> DYES = new HashMap<>() {{
        put(DyeColor.BLUE, Items.BLUE_DYE);
        put(DyeColor.BROWN, Items.BROWN_DYE);
        put(DyeColor.CYAN, Items.CYAN_DYE);
        put(DyeColor.GRAY, Items.GRAY_DYE);
        put(DyeColor.GREEN, Items.GREEN_DYE);
        put(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_DYE);
        put(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_DYE);
        put(DyeColor.LIME, Items.LIME_DYE);
        put(DyeColor.MAGENTA, Items.MAGENTA_DYE);
        put(DyeColor.ORANGE, Items.ORANGE_DYE);
        put(DyeColor.PINK, Items.PINK_DYE);
        put(DyeColor.PURPLE, Items.PURPLE_DYE);
        put(DyeColor.RED, Items.RED_DYE);
        put(DyeColor.WHITE, Items.WHITE_DYE);
        put(DyeColor.YELLOW, Items.YELLOW_DYE);
    }};

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        for(var dyeColor : ColorfulGlowstone.SUPPORTED_DYE_COLORS) {
            // dye glowstone dust
            ShapedRecipeBuilder
                    .shaped(RecipeCategory.MISC, ModItems.GLOWSTONE_DUST_ITEMS.get(dyeColor), 4)
                    .define('D', DYES.get(dyeColor))
                    .define('G', Items.GLOWSTONE_DUST)
                    .pattern(" G ")
                    .pattern("GDG")
                    .pattern(" G ")
                    .unlockedBy("has_glowstone_dust", has(Tags.Items.DUSTS_GLOWSTONE))
                    .save(recipeOutput);

            // craft glowstone from dust
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOWSTONE_BLOCK_ITEMS.get(dyeColor), 1)
                    .define('#', ModItems.GLOWSTONE_DUST_ITEMS.get(dyeColor))
                    .pattern("##")
                    .pattern("##")
                    .unlockedBy("has_glowstone_dust", has(Tags.Items.DUSTS_GLOWSTONE))
                    .group("colorful_glowstone:craft_glowstone")
                    .save(recipeOutput);

            // craft redstone lamp from glowstone
            ShapedRecipeBuilder
                    .shaped(RecipeCategory.REDSTONE, ModBlocks.REDSTONE_LAMP_BLOCK_ITEMS.get(dyeColor), 1)
                    .define('G', ModBlocks.GLOWSTONE_BLOCK_ITEMS.get(dyeColor))
                    .define('R', Tags.Items.DUSTS_REDSTONE)
                    .pattern(" R ")
                    .pattern("RGR")
                    .pattern(" R ")
                    .unlockedBy("has_glowstone_dust", has(Tags.Items.DUSTS_GLOWSTONE))
                    .group("colorful_glowstone:redstone_lamp")
                    .save(recipeOutput);
        }

        // color glowstone
        var dyeableGlowstones = new java.util.ArrayList<>(ModBlocks.GLOWSTONE_BLOCK_ITEMS.values().stream().map(DeferredItem::asItem).toList());
        dyeableGlowstones.add(Items.GLOWSTONE);
        colorBlockWithDye(recipeOutput, DYES.values().stream().toList(), dyeableGlowstones, "colorful_glowstone:dye_glowstone");

        // color redstone lamp
        var dyeableRedstoneLamps = new java.util.ArrayList<>(ModBlocks.REDSTONE_LAMP_BLOCK_ITEMS.values().stream().map(DeferredItem::asItem).toList());
        dyeableRedstoneLamps.add(Items.REDSTONE_LAMP);
        colorBlockWithDye(recipeOutput, DYES.values().stream().toList(), dyeableRedstoneLamps, "colorful_glowstone:dye_redstone_lamp");
    }
}
