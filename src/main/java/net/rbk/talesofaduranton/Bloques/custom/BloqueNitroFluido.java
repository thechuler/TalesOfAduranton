package net.rbk.talesofaduranton.Bloques.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;

public class BloqueNitroFluido extends FallingBlock {

    public BloqueNitroFluido(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        return null;
    }


    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pPos, Player player, boolean willHarvest, FluidState fluid) {
        level.explode(null, pPos.getX(), pPos.getY(), pPos.getZ(), 4.0F, Level.ExplosionInteraction.BLOCK);
        return super.onDestroyedByPlayer(state, level, pPos, player, willHarvest, fluid);
    }




    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if(!pLevel.isClientSide()){
            if(pStack.is(Items.BRUSH)){
                pLevel.destroyBlock(pPos, true, pPlayer);
            }
        }
        return super.useItemOn(pStack, pState, pLevel, pPos, pPlayer, pHand, pHitResult);
    }
}
