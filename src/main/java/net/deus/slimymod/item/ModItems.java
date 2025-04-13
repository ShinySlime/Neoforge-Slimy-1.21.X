package net.deus.slimymod.item;

import net.deus.slimymod.SlimyMod;
import net.deus.slimymod.item.custom.KnifeItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SlimyMod.MOD_ID);

    public static final DeferredItem<Item> MITHRIL_INGOT = ITEMS.register("mithril_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_MITHRIL = ITEMS.register("raw_mithril",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> KNIFE = ITEMS.register("knife",
            () -> new KnifeItem(new Item.Properties().durability(32)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}
