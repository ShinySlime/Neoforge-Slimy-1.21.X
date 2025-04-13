package net.deus.slimymod.item.custom;

import net.deus.slimymod.block.ModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class KnifeItem extends Item {
    private static final Map<Block, Block> KNIFE_MAP =
            Map.of(
                    Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN,
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.STONE_BRICKS, Blocks.CHISELED_STONE_BRICKS,
                    Blocks.CHISELED_STONE_BRICKS, Blocks.STONE,
                    Blocks.ANDESITE, Blocks.POLISHED_ANDESITE,
                    Blocks.POLISHED_ANDESITE, ModBlocks.CHISELED_ANDESITE.get(),
                    ModBlocks.CHISELED_ANDESITE.get(), Blocks.POLISHED_ANDESITE

            );

    public KnifeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock =  level.getBlockState(context.getClickedPos()).getBlock();

        if(KNIFE_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                //ONLY ON SERVER!
                level.setBlockAndUpdate(context.getClickedPos(), KNIFE_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1,((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

            }
        }

        return InteractionResult.SUCCESS;
    }
}
