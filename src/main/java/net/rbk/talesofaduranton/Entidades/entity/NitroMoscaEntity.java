package net.rbk.talesofaduranton.Entidades.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.rbk.talesofaduranton.Entidades.InicializarEntidades;
import net.rbk.talesofaduranton.Entidades.ia.GenerarNitroFluidoGoal;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.UUID;


public class NitroMoscaEntity extends Animal implements NeutralMob {

    public NitroMoscaEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }



    public static final int TICKS_PER_FLAP = Mth.ceil(1.4959966F);
    public final AnimationState idleAnimationState = new AnimationState();


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER,0)
                .add(Attributes.FLYING_SPEED,20);
    }





    @Override
    public boolean isInvulnerableTo(DamageSource pSource) {
        return pSource.is(DamageTypes.LAVA) || pSource.is(DamageTypes.IN_FIRE)  || pSource.is(DamageTypes.ON_FIRE) || super.isInvulnerableTo(pSource);
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8 ,new GenerarNitroFluidoGoal(this,200));
        this.goalSelector.addGoal(0 ,new FloatGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(2,new MeleeAttackGoal(this,1,true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, true));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.1, (p_326983_) -> {return p_326983_.is(Items.WARPED_FUNGUS);}, false));
        this.goalSelector.addGoal(6 ,new BreedGoal(this,1));
        this.goalSelector.addGoal(7 ,new BeeWanderGoal());
    }




    @Override
    public void tick() {
        super.tick();

        if (this.tickCount % 40 == 0) {
            this.playAmbientSound();
        }
        if(this.level().isClientSide() && !this.idleAnimationState.isStarted()){
            this.idleAnimationState.start(this.tickCount);
        }


    }



    @Override
    protected void tickDeath() {
        super.tickDeath();
        this.explotar();
    }




    public static void init(){

    }





    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.WARPED_FUNGUS);
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return InicializarEntidades.NITRO_FLY.get().create(serverLevel);
    }



    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.BEE_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BEE_LOOP;
    }




    @Override
    public void playAmbientSound() {
        SoundEvent soundEvent = getAmbientSound();
        if (soundEvent != null) {
            this.playSound(soundEvent, 0.4F, 3f);
        }
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int i) {
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }


    @Override
    public void setPersistentAngerTarget(@Nullable UUID uuid) {
    }

    @Override
    public void startPersistentAngerTimer() {
    }




    //*--------------------------VOLAR----------------------------------------*//
    class BeeWanderGoal extends Goal {

        BeeWanderGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            return NitroMoscaEntity.this.navigation.isDone() && NitroMoscaEntity.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return NitroMoscaEntity.this.navigation.isInProgress();
        }

        public void start() {
            Vec3 vec3 = this.findPos();
            if (vec3 != null) {
                NitroMoscaEntity.this.navigation.moveTo(NitroMoscaEntity.this.navigation.createPath(BlockPos.containing(vec3), 1), 1.0);
            }

        }

        @javax.annotation.Nullable
        private Vec3 findPos() {
            Vec3 vec3;

                vec3 = NitroMoscaEntity.this.getViewVector(0.0F);

            Vec3 vec32 = HoverRandomPos.getPos(NitroMoscaEntity.this, 8, 7, vec3.x, vec3.z, 1.5707964F, 3, 1);
            return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(NitroMoscaEntity.this, 8, 4, -2, vec3.x, vec3.z, 1.5707963705062866);
        }
    }

    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel) {
            public boolean isStableDestination(BlockPos p_27947_) {
                return !this.level.getBlockState(p_27947_.below()).isAir();
            }

        };
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(false);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    public boolean isFlapping() {
        return this.isFlying() && this.tickCount % TICKS_PER_FLAP == 0;
    }

    public boolean isFlying() {
        return !this.onGround();
    }

//--------------------------------------------------------------------------------//











//--------------------------GENERACION DE NITRO FLUIDO---------------------------//











    @Override
    public boolean doHurtTarget(Entity pEntity) {
        this.explotar();
        return super.doHurtTarget(pEntity);

    }




    //-----Funcion explotar
    private void explotar() {
        if (!this.level().isClientSide) {
            this.dead = true;
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)3, Level.ExplosionInteraction.MOB);
            this.triggerOnDeathMobEffects(RemovalReason.KILLED);
            this.discard();
        }

    }





    public static boolean PuedeSpawnear(EntityType<NitroMoscaEntity>entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random) {
        return !level.getBlockState(position.below()).is(Blocks.LAVA) ;
    }

}
