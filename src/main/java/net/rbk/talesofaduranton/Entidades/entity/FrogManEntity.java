package net.rbk.talesofaduranton.Entidades.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import net.rbk.talesofaduranton.Sonidos.InicializarSonidos;
import net.rbk.talesofaduranton.Utilidades;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class FrogManEntity extends Animal {

   public int index = (int) (Math.random() * 3);


   public static EntityDataAccessor<Boolean> RUGIR = SynchedEntityData.defineId(FrogManEntity.class, EntityDataSerializers.BOOLEAN);
    public static EntityDataAccessor<Boolean> ATACAR = SynchedEntityData.defineId(FrogManEntity.class, EntityDataSerializers.BOOLEAN);


    public FrogManEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }



    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState gruñirAnimationState = new AnimationState();
    public final AnimationState atacarAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int  rugidoAnimationTimeout = 0;
    private int  atacarAnimationTimeOut = 0;


//----------------------------------------DATA------------------------------------------//







    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(RUGIR,false);
        pBuilder.define(ATACAR,false);
    }


    public void setData(EntityDataAccessor<Boolean> DATA,boolean bool ) {
        this.entityData.set(DATA, bool);
    }

    public boolean getData(EntityDataAccessor<Boolean> DATA){
        return this.entityData.get(DATA);
    }









//---------------------------------SONIDOS--------------------------------------------------//


    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        setData(RUGIR,true);
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return InicializarSonidos.FROGMANHURT.get();
    }


    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return InicializarSonidos.FROGMANDEATH.get();
    }

    @Override
    protected void playAttackSound() {
        setData(ATACAR,true);

    }


    //-----------------ATAQUE----------------------------//

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        if(!this.level().isClientSide()&& pEntity instanceof LivingEntity){
            ((LivingEntity) pEntity).addEffect(new MobEffectInstance(MobEffects.POISON,50));
        }

        return super.doHurtTarget(pEntity);
    }


    private void MontarseParaAlcanzarObjetivo() {
        if (this.getTarget() != null) {
            List<LivingEntity> frogmanCercanos = Utilidades.ObtenerEntidadesEnArea(this.level(), this.blockPosition(), 3);
            if(this.getTarget().getBlockY()   > this.getBlockY() + 1){
                for (LivingEntity frogman : frogmanCercanos) {
                    if (frogman instanceof FrogManEntity && !frogman.isPassenger() && !frogman.isVehicle() && frogman != this) {
                        frogman.startRiding(this);
                    }
                }
            }else{
                if(this.isPassenger() && !this.isVehicle()){
                    this.stopRiding();

                    double dx = this.getTarget().getX() - this.getX();
                    double dz = this.getTarget().getZ() - this.getZ();
                    double distancia = Math.sqrt(dx * dx + dz * dz);
                    dx = dx / distancia;
                    dz = dz / distancia;
                    this.setDeltaMovement(this.getDeltaMovement().add(dx * 1, 1, dz * 1));
                    this.hasImpulse = true;
                }else{
                }
            }

        } else if (this.isPassenger()) {
            this.stopRiding();
        }


    }



    //-------------------------------TICK-------------------------------------//
  @Override
  public void tick() {


      if(this.level().isClientSide()){
          setUpAnimationStates();
          ManageRugido();
          ManageAtaque();
      }

      if(!this.level().isClientSide()){
       MontarseParaAlcanzarObjetivo();
      }

      super.tick();
  }







    //-------------------------------------ATRIBUTOS----------------------------------//
public static AttributeSupplier.Builder createAttributes() {
    return Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 10.0)
            .add(Attributes.MOVEMENT_SPEED, 0.4)
            .add(Attributes.ATTACK_DAMAGE, 8.5)
            .add(Attributes.FALL_DAMAGE_MULTIPLIER,0);
}


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1,new FloatGoal(this));
        this.goalSelector.addGoal(2,new MeleeAttackGoal(this,1,true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, AbstractVillager.class, false));
      this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, NitroMoscaEntity.class, true));



    }


    public static void init(){

    }






//-------------------------------------------ANIMACIONES--------------------------------------//

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING){
            f = Math.min(pPartialTick * 6f,1f);
        }else{
            f=0f;
        }

        this.walkAnimation.update(f,0.2f);
    }


    //Setup Estados Animacion
    private void setUpAnimationStates(){

        if(idleAnimationTimeout<= 0){
            this.idleAnimationTimeout = this.random.nextInt(40)+80;
            this.idleAnimationState.start(this.tickCount);
        }else{
            --this.idleAnimationTimeout;
        }


    }









    private void ManageRugido(){

        if(getData(RUGIR) && this.rugidoAnimationTimeout <= 0){
            if(this.level().isClientSide()){
                setData(RUGIR,false);
                this.rugidoAnimationTimeout = 60;
                this.gruñirAnimationState.start(this.tickCount);
                if (this.hasCustomName() && this.getCustomName() != null && this.getCustomName().getString().equals("Roman")) {
                    this.level().playLocalSound(this,InicializarSonidos.FROGMANEASTEREGG.get(), SoundSource.NEUTRAL,this.getSoundVolume(),this.getVoicePitch());
                }else{
                    this.level().playLocalSound(this,InicializarSonidos.FROGMANAMBIENT.get(), SoundSource.NEUTRAL,this.getSoundVolume(),this.getVoicePitch());
                }

            }
        }else{
            this.rugidoAnimationTimeout --;
        }

        if(getData(RUGIR) == false && this.rugidoAnimationTimeout <= 0){
            gruñirAnimationState.stop();
        }

    }




    private void ManageAtaque(){

        if(getData(ATACAR) && this.atacarAnimationTimeOut <= 0){
            if(this.level().isClientSide()){
                setData(ATACAR,false);
                this.atacarAnimationTimeOut = 40;
                this.atacarAnimationState.start(this.tickCount);
                this.level().playLocalSound(this,InicializarSonidos.FROGMANAMBIENT.get(), SoundSource.NEUTRAL,this.getSoundVolume(),this.getVoicePitch());
            }
        }else{
            this.atacarAnimationTimeOut --;
        }

        if(getData(ATACAR) == false && this.atacarAnimationTimeOut <= 0){
            atacarAnimationState.stop();
        }

    }












    //---------------Extras--------------------------------//
    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }


    @Override
    public boolean addEffect(MobEffectInstance pEffectInstance, @Nullable Entity pEntity) {
        if(pEffectInstance.getEffect() == MobEffects.POISON){
            return false;
        }
        return super.addEffect(pEffectInstance, pEntity);
    }



    public static boolean PuedeSpawnear(EntityType<FrogManEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random) {
        return true ;
    }

}
