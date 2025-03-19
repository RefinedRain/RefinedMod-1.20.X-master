package net.refinedrain.refinedmod.setup;

import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.refinedrain.refinedmod.RefinedMod;
import net.minecraftforge.api.distmarker.Dist;
import net.refinedrain.refinedmod.entity.spells.frost_strike.FrostStrikeRenderer;
import net.refinedrain.refinedmod.entity.spells.moon_slash.MoonSlashRenderer;
import net.refinedrain.refinedmod.particle.GlintStarParticle;
import net.refinedrain.refinedmod.registry.EntityRegistry;
import net.refinedrain.refinedmod.registry.ParticleRegistry;

@Mod.EventBusSubscriber(modid = RefinedMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.FROST_STRIKE_ENTITY.get(), FrostStrikeRenderer::new);
        event.registerEntityRenderer(EntityRegistry.MOON_SLASH_PROJECTILE.get(), MoonSlashRenderer::new);

        event.registerEntityRenderer(EntityRegistry.SUMMONED_WOLF.get(), WolfRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticle(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleRegistry.GLINT_STAR_PARTICLE.get(), GlintStarParticle.Provider::new);
    }

}
