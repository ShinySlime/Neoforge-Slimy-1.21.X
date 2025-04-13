package net.deus.slimymod.block;

import net.deus.slimymod.SlimyMod;
import net.deus.slimymod.block.custom.CursedBlock;
import net.deus.slimymod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Properties;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(SlimyMod.MOD_ID);

    public static final DeferredBlock<Block> CHISELED_ANDESITE = registerBlock("chiseled_andesite",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DEEPSLATE_MITHRIL_ORE = registerBlock("deepslate_mithril_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,4),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> MITHRIL_BLOCK = registerBlock("mithril_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(5f).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)));

    public static final  DeferredBlock<Block> CURSED_BLOCK = registerBlock("cursed_block",
            ()-> new CursedBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventbus) {
        BLOCKS.register(eventbus);
    }

}
