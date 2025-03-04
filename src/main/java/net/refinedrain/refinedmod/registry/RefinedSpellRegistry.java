package net.refinedrain.refinedmod.registry;

import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.refinedrain.refinedmod.RefinedMod;
import net.refinedrain.refinedmod.spells.FrostStrikeSpell;
import net.refinedrain.refinedmod.spells.MoonSlashSpell;
import net.refinedrain.refinedmod.spells.ThunderStrikeSpell;


public class RefinedSpellRegistry {

    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, RefinedMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }
    public static RegistryObject<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    public static final RegistryObject<AbstractSpell> FROST_STRIKE_SPELL = registerSpell(new FrostStrikeSpell());
    public static final RegistryObject<AbstractSpell> THUNDER_STRIKE_SPELL = registerSpell(new ThunderStrikeSpell());
    public static final RegistryObject<AbstractSpell> MOON_SLASH_SPELL = registerSpell(new MoonSlashSpell());

}

