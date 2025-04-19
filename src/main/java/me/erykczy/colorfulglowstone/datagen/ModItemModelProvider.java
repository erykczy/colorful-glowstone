package me.erykczy.colorfulglowstone.datagen;

import me.erykczy.colorfulglowstone.block.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(var entry : ModItems.GLOWSTONE_DUST_ITEMS.entrySet()) {
            withExistingParent(entry.getValue().getRegisteredName(), mcLoc("item/generated")).texture("layer0", modLoc("item/tinted_glowstone_dust"));
        }
    }
}
