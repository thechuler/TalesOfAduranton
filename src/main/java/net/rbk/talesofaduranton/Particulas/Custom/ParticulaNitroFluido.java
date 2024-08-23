package net.rbk.talesofaduranton.Particulas.Custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


import java.util.Random;

public class ParticulaNitroFluido extends TextureSheetParticle {


    public ParticulaNitroFluido(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.friction = 0.8F;
        this.xd = pXSpeed;
        this.yd = pYSpeed;
        this.zd = pZSpeed;
        this.quadSize *= 3F;
        this.lifetime = 60;

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
         this.gravity = 1f;

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
            Random random = new Random();
            double randomXSpeed = (random.nextDouble() - 0.5) * 0.1; // Velocidad aleatoria en X
            double randomYSpeed = (random.nextDouble() - 0.5) * 0.1; // Velocidad aleatoria en Y
            double randomZSpeed = (random.nextDouble() - 0.5) * 0.1; // Velocidad aleatoria en Z

            ParticulaNitroFluido particle = new ParticulaNitroFluido(level, x, y, z, randomXSpeed, randomYSpeed, randomZSpeed);
            particle.pickSprite(this.sprites);
            return particle;
        }
    }



}
