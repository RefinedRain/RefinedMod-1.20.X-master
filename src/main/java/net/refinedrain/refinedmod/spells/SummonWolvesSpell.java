package net.refinedrain.refinedmod.spells;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;

import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.refinedrain.refinedmod.RefinedMod;
import net.refinedrain.refinedmod.entity.mobs.SummonedWolf;
import net.refinedrain.refinedmod.registry.MobEffectRegistry;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class SummonWolvesSpell extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(RefinedMod.MOD_ID, "summon_wolves");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.UNCOMMON)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(180)
            .build();
    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    public SummonWolvesSpell() {
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 4;
        this.spellPowerPerLevel = 1;
        this.castTime = 20;
        this.baseManaCost = 40;
    }

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.hp", getWolfHealth(spellLevel, null)),
                Component.translatable("ui.irons_spellbooks.damage", getWolfDamage(spellLevel, null))
        );
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }



    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundEvents.EVOKER_PREPARE_SUMMON);
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        int summonTime = 20 * 60 * 10;

        for (int i = 0; i < 3; i++) {
            SummonedWolf wolf = new SummonedWolf(world, entity);
            wolf.setPos(
                    entity.getX() + Utils.getRandomScaled(2), // Random offset within a radius of 2
                    entity.getY(),
                    entity.getZ() + Utils.getRandomScaled(2)
            );

            // Set wolf attributes
            wolf.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(getWolfDamage(spellLevel, entity));
            wolf.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(getWolfHealth(spellLevel, entity));
            wolf.setHealth(wolf.getMaxHealth());

            // Add the wolf to the world
            world.addFreshEntity(wolf);

            // Apply effects
            wolf.addEffect(new MobEffectInstance(MobEffectRegistry.WOLF_TIMER.get(), summonTime, 0, false, false, false));
        }

        int effectAmplifier = 0;
        if (entity.hasEffect(MobEffectRegistry.WOLF_TIMER.get()))
            effectAmplifier += entity.getEffect(MobEffectRegistry.WOLF_TIMER.get()).getAmplifier() + 1;
        entity.addEffect(new MobEffectInstance(MobEffectRegistry.WOLF_TIMER.get(), summonTime, effectAmplifier, false, false, true));

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getWolfHealth(int spellLevel, LivingEntity caster) {
        return 10 + spellLevel * 4;
    }

    private float getWolfDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster);
    }
}