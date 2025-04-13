package net.deus.slimymod.item;

import net.deus.slimymod.SlimyMod;
import net.deus.slimymod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SlimyMod.MOD_ID);

    public static final Supplier<CreativeModeTab> MODDED_BLOCKS_TAB = CREATIVE_MODE_TAB.register("modded_blocks_tab",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModBlocks.CHISELED_ANDESITE.get()))
                    .title(Component.translatable("creativetab.slimymod.modded_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.MITHRIL_BLOCK);
                        output.accept(ModBlocks.DEEPSLATE_MITHRIL_ORE);
                        output.accept(ModBlocks.CHISELED_ANDESITE);
                    }).build());

    public static final Supplier<CreativeModeTab> MODDED_ITEMS_TAB = CREATIVE_MODE_TAB.register("modded_items_tab",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.RAW_MITHRIL.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(SlimyMod.MOD_ID, "modded_blocks_tab"))
                    .title(Component.translatable("creativetab.slimymod.modded_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_MITHRIL);
                        output.accept(ModItems.MITHRIL_INGOT);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
