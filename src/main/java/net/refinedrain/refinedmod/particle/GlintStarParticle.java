package net.refinedrain.refinedmod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GlintStarParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    public GlintStarParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);
        this.friction = 0.8f + this.random.nextFloat() * 0.1f;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= .65f;
        this.scale(1f);
        this.lifetime = 5 + (int) (Math.random() * 30);
        this.sprites = spriteSet;
        this.gravity = 0.0F;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
        this.alpha = 0.9f;
    }

    public void setCustomAlpha(float alpha) {
        this.alpha = alpha;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
            this.xd += (double) (this.random.nextFloat() / 5000.0F * (float) (this.random.nextBoolean() ? 1 : -1));
            this.zd += (double) (this.random.nextFloat() / 5000.0F * (float) (this.random.nextBoolean() ? 1 : -1));
            this.yd -= (double) (this.random.nextFloat() / 5000.0F * (float) (this.random.nextBoolean() ? 1 : -1));
            this.xd *= friction;
            this.zd *= friction;
            this.yd *= friction;
            this.move(this.xd, this.yd, this.zd);

            float fadeStart = 0.7f * this.lifetime;
            if (this.age > fadeStart) {
                this.alpha = Math.max(0.0f, 0.9f - (this.age - fadeStart) / (this.lifetime - fadeStart));
            }
        } else {
            this.remove();
        }
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public int getLightColor(float partialTick) {
        int i = super.getLightColor(partialTick);
        int brightness = (int) (240 * this.alpha);
        int k = i >> 16 & 255;
        return brightness | k << 16;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new GlintStarParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}