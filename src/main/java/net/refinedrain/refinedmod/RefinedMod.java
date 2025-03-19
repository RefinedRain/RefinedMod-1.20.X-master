package net.refinedrain.refinedmod;

import com.mojang.logging.LogUtils;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.refinedrain.refinedmod.item.ModItems;
import net.refinedrain.refinedmod.registry.EntityRegistry;
import net.refinedrain.refinedmod.registry.MobEffectRegistry;
import net.refinedrain.refinedmod.registry.ParticleRegistry;
import net.refinedrain.refinedmod.registry.RefinedSpellRegistry;

import net.refinedrain.refinedmod.sound.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;



@Mod(RefinedMod.MOD_ID)
public class RefinedMod {

    public static final String MOD_ID = "refined_mod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RefinedMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        EntityRegistry.register(modEventBus);
        modEventBus.addListener(this::commonSetup);

        AttributeRegistry.register(modEventBus);
        RefinedSpellRegistry.register(modEventBus);
        MobEffectRegistry.register(modEventBus);
        ModItems.register(modEventBus);

        ModSounds.register(modEventBus);

        ParticleRegistry.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.JADES_JEWEL_MUSIC_DISC);
        }

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation(RefinedMod.MOD_ID, path);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
