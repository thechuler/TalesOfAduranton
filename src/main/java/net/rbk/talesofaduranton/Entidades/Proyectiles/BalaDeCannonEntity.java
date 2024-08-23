package net.rbk.talesofaduranton.Entidades.Proyectiles;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.rbk.talesofaduranton.Entidades.InicializarEntidades;
import net.rbk.talesofaduranton.items.InicializarItems;


public class BalaDeCannonEntity extends ThrowableItemProjectile {

    // Constructor público
    public BalaDeCannonEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    

    public BalaDeCannonEntity(Level pLevel, LivingEntity livingEntity) {
        super(InicializarEntidades.CANNON_PROJECTILE.get(), livingEntity, pLevel);
    }

    public BalaDeCannonEntity(EntityType<BalaDeCannonEntity> balaDeCannonEntityEntityType, Level pLevel, LivingEntity livingEntity) {
        super(balaDeCannonEntityEntityType, livingEntity, pLevel);;
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide()) {
            explotar();
        }
        this.discard();
    }

    public void explotar() {
        if (!this.level().isClientSide) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 4.0F, false, Level.ExplosionInteraction.NONE);
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) this.level();
            ParticleOptions particleType = ParticleTypes.FLAME;
            serverLevel.sendParticles(particleType, this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return InicializarItems.CANNON_CHARGE.get();
    }


}
