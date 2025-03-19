package net.refinedrain.refinedmod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.registries.RegistryObject;
import net.refinedrain.refinedmod.RefinedMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.refinedrain.refinedmod.sound.ModSounds;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RefinedMod.MOD_ID);

    public static final RegistryObject<Item> JADES_JEWEL_MUSIC_DISC = ITEMS.register("jades_jewel_music_disc",
            () -> new RecordItem(6, ModSounds.JADES_JEWEL, new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE), 3420));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
