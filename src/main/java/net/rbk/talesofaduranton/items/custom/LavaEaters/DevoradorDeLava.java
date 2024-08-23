package net.rbk.talesofaduranton.items.custom.LavaEaters;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.rbk.talesofaduranton.Utilidades;


public class DevoradorDeLava extends SwordItem {


    public DevoradorDeLava(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        if(pUsedHand == InteractionHand.OFF_HAND && pPlayer.getMainHandItem().is(Items.BLAZE_POWDER) && itemStack.getDamageValue() > 0){
            itemStack.setDamageValue(itemStack.getDamageValue() - 100);
            pPlayer.getMainHandItem().shrink(1);
            return InteractionResultHolder.success(itemStack);
        }

        if(pUsedHand == InteractionHand.OFF_HAND || itemStack.getDamageValue() >= itemStack.getMaxDamage()){
            return InteractionResultHolder.fail(itemStack);
        }


            if (pPlayer.isCrouching() && pPlayer.onGround() ) {
                pPlayer.startUsingItem(pUsedHand);
                return InteractionResultHolder.consume(itemStack);
            }

            else if (!pPlayer.isCrouching()) {
                pPlayer.level().playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FIRE_AMBIENT, pPlayer.getSoundSource(), 1.0F, 1);
                pPlayer.startAutoSpinAttack(20, 8.0F, itemStack);
                Vec3 lookDirection = pPlayer.getLookAngle();
                Vec3 pushDirection = lookDirection.scale(1);
                pPlayer.setDeltaMovement(pushDirection.x * 1.5, pushDirection.y * 1.5, pushDirection.z * 1.5);
                Utilidades.spawnearParticulas(pPlayer, 20, ParticleTypes.FLAME);
                pPlayer.hurtMarked = true;
                itemStack.setDamageValue(itemStack.getDamageValue() + 5);
                return InteractionResultHolder.fail(itemStack);
            }



        return InteractionResultHolder.pass(itemStack);
    }


    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if(pRemainingUseDuration >= 1.0){
            Utilidades.spawnearParticulas(pLivingEntity,3,ParticleTypes.FLAME);
        }
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if (pLivingEntity instanceof Player pPlayer) {
            int chargeTime = this.getUseDuration(pStack,pLivingEntity) - pTimeCharged;
            if (chargeTime > 0) {
                executeChargedAttack(pPlayer);
                pStack.setDamageValue(pStack.getDamageValue() + 500);
            }
        }
    }


    private void executeChargedAttack(Player pPlayer) {
        Vec3 lookDirection = pPlayer.getLookAngle();
        Vec3 pushDirection = lookDirection.scale(3);
        pPlayer.setDeltaMovement(pushDirection.x, pushDirection.y, pushDirection.z);
        Utilidades.spawnearParticulas(pPlayer, 20, ParticleTypes.FLAME);
        pPlayer.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,120));
        if (!pPlayer.level().isClientSide()) {
            pPlayer.level().explode(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), (float) 5, true, Level.ExplosionInteraction.TNT);
        }
        pPlayer.hurtMarked = true;
    }


    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {

            pTarget.igniteForTicks(100);

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }


    @Override
    public int getBarColor(ItemStack pStack) {
        return Mth.hsvToRgb(30 / 360.0F, 1.0F, 1.0F);
    }
}
