package me.erykczy.colorfulglowstone.block;

import me.erykczy.colorfulglowstone.ColorfulGlowstone;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;

public class ModItems {
    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems(ColorfulGlowstone.MODID);
    public static final HashMap<DyeColor, DeferredItem<Item>> GLOWSTONE_DUST_ITEMS = new HashMap<>();

    static {
        for(var dye : ColorfulGlowstone.SUPPORTED_DYE_COLORS) {
            GLOWSTONE_DUST_ITEMS.put(dye, registerGlowstoneDust(dye));
        }
    }

    private static DeferredItem<Item> registerGlowstoneDust(DyeColor dyeColor) {
        return ITEMS.registerSimpleItem(dyeColor.getName()+"_glowstone_dust", new Item.Properties());
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        bus.addListener(ModItems::registerItemColorHandler);
    }

    private static void registerItemColorHandler(RegisterColorHandlersEvent.Item event) {
        for(var entry : ModItems.GLOWSTONE_DUST_ITEMS.entrySet()) {
            event.register((stack, tintIndex) -> ColorfulGlowstone.getColorFromDye(entry.getKey()), entry.getValue().get());
        }
    }
}
