package net.rbk.talesofaduranton.items.custom.LavaEaters;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.SpinAttackEffectLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.rbk.talesofaduranton.TalesOfAduranton;
import net.rbk.talesofaduranton.items.InicializarItems;


public class DevoradorDeLavaSpinRender<T extends LivingEntity> extends SpinAttackEffectLayer<T> {

    public static final ResourceLocation CUSTOM_TEXTURE = ResourceLocation.fromNamespaceAndPath(TalesOfAduranton.MODID,"textures/entity/prueba1.png");
    private ModelPart box = null;
    public DevoradorDeLavaSpinRender(RenderLayerParent pRenderer, EntityModelSet pModelSet) {
        super(pRenderer, pModelSet);
        this.box = pModelSet.bakeLayer(ModelLayers.PLAYER_SPIN_ATTACK).getChild("box");
    }


    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, LivingEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (pLivingEntity.isAutoSpinAttack() && pLivingEntity.getMainHandItem().is( InicializarItems.LAVA_EATERS.get())) {
            VertexConsumer vertexConsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(CUSTOM_TEXTURE));
            for (int i = 0; i < 3; ++i) {
                pPoseStack.pushPose();
                float rotation = pAgeInTicks * (-(45 + i * 5));
                pPoseStack.mulPose(Axis.YP.rotationDegrees(rotation));
                float scale = 0.75F * (float) i;
                pPoseStack.scale(scale, scale, scale);
                pPoseStack.translate(0.0F, -0.2F + 0.6F * (float) i, 0.0F);
                this.box.render(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY);
                pPoseStack.popPose();
            }
        }
    }





}


