package me.erykczy.colorfulglowstone.block;

import me.erykczy.colorfulglowstone.ColorfulGlowstone;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ColorfulGlowstone.MODID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(ColorfulGlowstone.MODID);
    public static final HashMap<DyeColor, DeferredBlock<Block>> GLOWSTONE_BLOCKS = new HashMap<>();
    public static final HashMap<DyeColor, DeferredItem<BlockItem>> GLOWSTONE_BLOCK_ITEMS = new HashMap<>();
    public static final HashMap<DyeColor, DeferredBlock<Block>> REDSTONE_LAMP_BLOCKS = new HashMap<>();
    public static final HashMap<DyeColor, DeferredItem<BlockItem>> REDSTONE_LAMP_BLOCK_ITEMS = new HashMap<>();

    static {
        for(var dyeColor : ColorfulGlowstone.SUPPORTED_DYE_COLORS) {
            GLOWSTONE_BLOCKS.put(dyeColor, registerGlowstone(dyeColor));
            GLOWSTONE_BLOCK_ITEMS.put(dyeColor, registerGlowstoneItem(dyeColor));

            REDSTONE_LAMP_BLOCKS.put(dyeColor, registerRedstoneLamp(dyeColor));
            REDSTONE_LAMP_BLOCK_ITEMS.put(dyeColor, registerRedstoneLampItem(dyeColor));
        }
    }

    private static DeferredBlock<Block> registerGlowstone(DyeColor color) {
        return BLOCKS.registerSimpleBlock(color.getName()+"_glowstone", BlockBehaviour.Properties.ofFullCopy(Blocks.GLOWSTONE));
    }

    private static DeferredBlock<Block> registerRedstoneLamp(DyeColor color) {
        return BLOCKS.registerBlock(color.getName()+"_redstone_lamp", RedstoneLampBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP));
    }

    private static DeferredItem<BlockItem> registerGlowstoneItem(DyeColor color) {
        return BLOCK_ITEMS.registerSimpleBlockItem(GLOWSTONE_BLOCKS.get(color));
    }

    private static DeferredItem<BlockItem> registerRedstoneLampItem(DyeColor color) {
        return BLOCK_ITEMS.registerSimpleBlockItem(REDSTONE_LAMP_BLOCKS.get(color));
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        BLOCK_ITEMS.register(bus);
    }
}
