package me.erykczy.colorfulglowstone.creativetab;

import me.erykczy.colorfulglowstone.ColorfulGlowstone;
import me.erykczy.colorfulglowstone.block.ModBlocks;
import me.erykczy.colorfulglowstone.block.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ColorfulGlowstone.MODID);
    public static final Supplier<CreativeModeTab> COLORFUL_GLOWSTONE = CREATIVE_MODE_TABS.register("colorful_glowstone", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + ColorfulGlowstone.MODID + ".colorful_glowstone"))
            .icon(() -> new ItemStack(ModBlocks.REDSTONE_LAMP_BLOCK_ITEMS.get(DyeColor.RED).get()))
            .displayItems((params, output) -> {
                for(var entry : ModBlocks.GLOWSTONE_BLOCK_ITEMS.entrySet()) {
                    output.accept(entry.getValue().get());
                }
                for(var entry : ModBlocks.REDSTONE_LAMP_BLOCK_ITEMS.entrySet()) {
                    output.accept(entry.getValue().get());
                }
                for(var entry : ModItems.GLOWSTONE_DUST_ITEMS.entrySet()) {
                    output.accept(entry.getValue().get());
                }
            })
            .build()
    );

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
