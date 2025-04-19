package me.erykczy.colorfulglowstone.datagen;

import me.erykczy.colorfulglowstone.ColorfulGlowstone;
import me.erykczy.colorfulglowstone.block.ModBlocks;
import me.erykczy.colorfulglowstone.block.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootSubProvider extends BlockLootSubProvider {
    protected ModBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(e -> (Block)e.value()).toList();
    }

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        for(var dyeColor : ColorfulGlowstone.SUPPORTED_DYE_COLORS) {
            add(ModBlocks.GLOWSTONE_BLOCKS.get(dyeColor).get(), block -> createSilkTouchDispatchTable(
                    block,
                    applyExplosionDecay(
                            block,
                            LootItem.lootTableItem(ModItems.GLOWSTONE_DUST_ITEMS.get(dyeColor))
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                    .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                                    .apply(LimitCount.limitCount(IntRange.range(1, 4)))
                    )
            ));

            dropSelf(ModBlocks.REDSTONE_LAMP_BLOCKS.get(dyeColor).get());
        }
    }
}
