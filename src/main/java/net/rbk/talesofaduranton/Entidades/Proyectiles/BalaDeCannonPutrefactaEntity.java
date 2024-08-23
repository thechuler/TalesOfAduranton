package net.rbk.talesofaduranton.Entidades.Proyectiles;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.rbk.talesofaduranton.Entidades.InicializarEntidades;
import net.rbk.talesofaduranton.Particulas.InicializarParticulas;
import net.rbk.talesofaduranton.Utilidades;
import net.rbk.talesofaduranton.items.InicializarItems;


import java.util.List;

public class BalaDeCannonPutrefactaEntity extends BalaDeCannonEntity{


    public BalaDeCannonPutrefactaEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

  /*  public BalaDeCannonPutrefactaEntity(Level pLevel, LivingEntity livingEntity) {
        super(InicializarEntidades.DECAYED_PROJECTILE.get(), pLevel, livingEntity);
    }
*/

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        List<LivingEntity> EntidadesAenvenenar= Utilidades.ObtenerEntidadesEnArea(this.level(),pResult.getBlockPos(),10);
        for (LivingEntity entidad : EntidadesAenvenenar){
            entidad.addEffect(new MobEffectInstance(MobEffects.POISON,200,3));
            entidad.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,200,3));
        }

        Utilidades.spawnParticlesEnArea(this.level(), (ParticleOptions) InicializarParticulas.POISON_PARTICLES.get(),pResult.getBlockPos(),10,1000);
        super.onHitBlock(pResult);
    }

    @Override
    public void explotar() {
        if (!this.level().isClientSide) {
            List<LivingEntity> EntidadesAenvenenar= Utilidades.ObtenerEntidadesEnArea(this.level(),this.blockPosition(),3);
            for (LivingEntity entidad : EntidadesAenvenenar){
                entidad.addEffect(new MobEffectInstance(MobEffects.POISON,200,3));
                entidad.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,200,3));
            }
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 4.0F, false, Level.ExplosionInteraction.NONE);

            this.discard();
        }
    }


    @Override
    protected Item getDefaultItem() {
        return InicializarItems.DECAYED_CHARGE.get();
    }
}
