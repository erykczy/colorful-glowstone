package me.erykczy.colorfulglowstone.datagen;

import me.erykczy.colorfulglowstone.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for(var entry : ModBlocks.GLOWSTONE_BLOCKS.entrySet()) {
            ModelFile model = models().withExistingParent(entry.getValue().getRegisteredName(), modLoc("block/tinted_glowstone"));
            simpleBlockWithItem(entry.getValue().get(), model);
        }
        for(var entry : ModBlocks.REDSTONE_LAMP_BLOCKS.entrySet()) {
            Block block = entry.getValue().get();
            ModelFile model_off = models().withExistingParent(entry.getValue().getRegisteredName(), modLoc("block/tinted_redstone_lamp"));
            ModelFile model_on = models().withExistingParent(entry.getValue().getRegisteredName()+"_on", modLoc("block/tinted_redstone_lamp_on"));
            getVariantBuilder(block).forAllStates((blockState -> new ConfiguredModel[] {
                new ConfiguredModel(blockState.getValue(RedstoneLampBlock.LIT) ? model_on : model_off)
            }));

            simpleBlockItem(block, model_off);
        }
    }
}
