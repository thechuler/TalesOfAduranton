package net.rbk.talesofaduranton.Particulas.Custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


public class ParticulaDeVeneno extends TextureSheetParticle {


    public ParticulaDeVeneno(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.friction = 0.8F;
        this.xd = pXSpeed;
        this.yd = pYSpeed;
        this.zd = pZSpeed;
        this.quadSize *= 3F;
        this.lifetime = 200;

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
         this.gravity = -0.5f;

    }


    @Override
    public void tick() {
        super.tick();
        Desaparecer();

    }




    private void Desaparecer(){
        this.alpha =(-(1/(float)lifetime) * age * 1);
    }







    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    @OnlyIn(Dist.CLIENT)



    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;



        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }



        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            ParticulaDeVeneno $$8 = new ParticulaDeVeneno(level, x, y, z, dx, dy, dz);
            $$8.pickSprite(this.sprites);
            return $$8;
        }
    }



}
