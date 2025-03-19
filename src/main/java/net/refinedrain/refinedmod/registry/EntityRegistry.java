package net.refinedrain.refinedmod.registry;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.mobs.SummonedPolarBear;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.RegistryObject;
import net.refinedrain.refinedmod.RefinedMod;
import net.refinedrain.refinedmod.entity.mobs.SummonedWolf;
import net.refinedrain.refinedmod.entity.spells.frost_strike.FrostStrikeEntity;
import net.refinedrain.refinedmod.entity.spells.moon_slash.MoonSlashProjectile;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "refined_mod");

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    public static final RegistryObject<EntityType<FrostStrikeEntity>> FROST_STRIKE_ENTITY =
            ENTITIES.register("frost_strike", () -> EntityType.Builder.<FrostStrikeEntity>of(FrostStrikeEntity::new, MobCategory.MISC)
                    .sized(5f, 1f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(RefinedMod.MOD_ID, "frost_strike").toString()));

    public static final RegistryObject<EntityType<MoonSlashProjectile>> MOON_SLASH_PROJECTILE =
            ENTITIES.register("moon_slash", () -> EntityType.Builder.<MoonSlashProjectile>of(MoonSlashProjectile::new, MobCategory.MISC)
                    .sized(5f, 1f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(RefinedMod.MOD_ID, "moon_slash").toString()));

    public static final RegistryObject<EntityType<SummonedWolf>> SUMMONED_WOLF =
            ENTITIES.register("summoned_wolf", () -> EntityType.Builder.<SummonedWolf>of(SummonedWolf::new, MobCategory.CREATURE)
                    .immuneTo(Blocks.POWDER_SNOW)
                    .sized(1.4F, 1.4F)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(RefinedMod.MOD_ID, "summoned_wolf").toString()));

}
