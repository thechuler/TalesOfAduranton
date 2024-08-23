package net.rbk.talesofaduranton.items.custom;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.rbk.talesofaduranton.Entidades.Proyectiles.BalaDeCannonEntity;
import net.rbk.talesofaduranton.Entidades.Proyectiles.BalaDeCannonInestableEntity;
import net.rbk.talesofaduranton.Entidades.Proyectiles.BalaDeCannonPutrefactaEntity;
import net.rbk.talesofaduranton.Sonidos.InicializarSonidos;
import net.rbk.talesofaduranton.Utilidades;
import net.rbk.talesofaduranton.items.InicializarItems;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

public class LilRocky extends CrossbowItem {


    private boolean startSoundPlayed = false;
    private boolean midLoadSoundPlayed = false;
    static Optional<Holder<SoundEvent>> endSound = Optional.of(Holder.direct(InicializarSonidos.CANNON_SHOOT.get()));
    static Optional<Holder<SoundEvent>> midSound = Optional.of(Holder.direct(InicializarSonidos.CANNON_CLICK.get()));
    static Optional<Holder<SoundEvent>> startSound = Optional.of(Holder.direct(InicializarSonidos.CANNON_RELOAD.get()));
    private static final ChargingSounds   DEFAULT_SOUNDS = new ChargingSounds(startSound,midSound, endSound); ;

    public LilRocky(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return itemStack -> itemStack.is(InicializarItems.CANNON_CHARGE.get()) || itemStack.is(InicializarItems.DECAYED_CHARGE.get()) || itemStack.is(InicializarItems.UNSTABLE_CHARGE.get());
    }


    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return itemStack -> itemStack.is(InicializarItems.CANNON_CHARGE.get()) || itemStack.is(InicializarItems.DECAYED_CHARGE.get()) || itemStack.is(InicializarItems.UNSTABLE_CHARGE.get());
    }



    @Override
    protected void shootProjectile(LivingEntity livingEntity, Projectile projectile, int index, float velocity, float inaccuracy, float angle, @Nullable LivingEntity target) {
        if (projectile instanceof BalaDeCannonEntity)
            projectile.shootFromRotation(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0.0F, 1.5f, inaccuracy);





    }


    private ChargingSounds getCustomChargingSounds() {
        return DEFAULT_SOUNDS;
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);





        if (!level.isClientSide) {
            if (isCharged(itemStack)) {
                performShooting(level, player, hand, itemStack, 2.8F, 1.0F, null); // Usar el poder de disparo que desees
                spawnParticles(player,10);
                Vec3 lookDirection = player.getLookAngle();
                Vec3 pushDirection = lookDirection.scale(-1);
                player.setDeltaMovement(pushDirection.x * 1.5, pushDirection.y * 1.5, pushDirection.z * 1.5);
                player.hurtMarked = true;

                return InteractionResultHolder.consume(itemStack);
            }else{
                ItemStack MunicionComun = Utilidades.EncontrarItemEnJugador(player,InicializarItems.CANNON_CHARGE.get());

                ItemStack MunicionVenenosa = Utilidades.EncontrarItemEnJugador(player,InicializarItems.DECAYED_CHARGE.get());


                ItemStack MunicionInestable = Utilidades.EncontrarItemEnJugador(player,InicializarItems.UNSTABLE_CHARGE.get());
                if(MunicionInestable.isEmpty() && MunicionComun.isEmpty()  && MunicionVenenosa.isEmpty()){
                    player.level().playSound((Player)null, player.getX(), player.getY(), player.getZ(), InicializarSonidos.EMPTY_CANNON.get(), player.getSoundSource(), 1.0F, 1);
                }
            }

        }


        return super.use(level, player, hand);
    }






    @Override
    protected Projectile createProjectile(Level level, LivingEntity shooter, ItemStack weapon, ItemStack ammo, boolean isCrit) {
      if(ammo.is(InicializarItems.CANNON_CHARGE.get())){
          shooter.level().playSound((Player)null, shooter.getX(), shooter.getY(), shooter.getZ(), InicializarSonidos.CANNON_SHOOT.get(), shooter.getSoundSource(), 1.0F, 1);
          return new BalaDeCannonEntity(level, shooter);
      }
   /*   if (ammo.is(InicializarItems.DECAYED_CHARGE.get())){
          shooter.level().playSound((Player)null, shooter.getX(), shooter.getY(), shooter.getZ(), InicializarSonidos.CANNON_SHOOT.get(), shooter.getSoundSource(), 1.0F, 1);
          return new BalaDeCannonPutrefactaEntity(level, shooter);
      }
*/

        if (ammo.is(InicializarItems.UNSTABLE_CHARGE.get())){
            shooter.level().playSound((Player)null, shooter.getX(), shooter.getY(), shooter.getZ(), InicializarSonidos.CANNON_SHOOT.get(), shooter.getSoundSource(), 1.0F, 1);
            return new BalaDeCannonInestableEntity( shooter,level);
        }


        return null;
    }




    private void spawnParticles(LivingEntity entity, int particleCount) {
        if (!entity.level().isClientSide()) {
            Random random = new Random();
            ServerLevel serverLevel = (ServerLevel) entity.level();

            ParticleOptions particleType = ParticleTypes.EXPLOSION;

            for (int i = 0; i < particleCount; i++) {
                double offsetX = (random.nextDouble() - 0.5) * 2.0;
                double offsetY = random.nextDouble() * 2.0;
                double offsetZ = (random.nextDouble() - 0.5) * 2.0;

                double posX = entity.getX() + offsetX;
                double posY = entity.getY() + offsetY;
                double posZ = entity.getZ() + offsetZ;

                serverLevel.sendParticles(particleType, posX, posY, posZ, 1, 0, 0, 0, 0);
            }
        }


    }








    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pCount) {
        if (!pLevel.isClientSide) {
            ChargingSounds crossbowitem$chargingsounds = this.getCustomChargingSounds();
            float f = (float)(pStack.getUseDuration(pLivingEntity) - pCount) / (float)getChargeDuration(pStack, pLivingEntity);
            if (f < 0.2F) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
            }

            if (f >= 0.2F && !this.startSoundPlayed) {
                this.startSoundPlayed = true;
                crossbowitem$chargingsounds.start().ifPresent((p_345510_) -> {
                    pLevel.playSound((Player)null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), (SoundEvent)p_345510_.value(), SoundSource.PLAYERS, 0.5F, 1.0F);
                });
            }

            if (f >= 0.5F && !this.midLoadSoundPlayed) {
                this.midLoadSoundPlayed = true;
                crossbowitem$chargingsounds.mid().ifPresent((p_342652_) -> {
                    pLevel.playSound((Player)null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), (SoundEvent)p_342652_.value(), SoundSource.PLAYERS, 0.5F, 1.0F);
                });
            }
    }





}}
