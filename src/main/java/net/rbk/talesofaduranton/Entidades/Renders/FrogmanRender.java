package net.rbk.talesofaduranton.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbk.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbk.talesofaduranton.Entidades.entity.FrogManEntity;
import net.rbk.talesofaduranton.TalesOfAduranton;

public class FrogmanRender extends MobRenderer<FrogManEntity, FrogManModel<FrogManEntity>> {

    FrogManEntity entity;

    private static final ResourceLocation[] TEXTURAS = new ResourceLocation[] {
            ResourceLocation.fromNamespaceAndPath(TalesOfAduranton.MODID, "textures/entity/frogmantexture.png"),
            ResourceLocation.fromNamespaceAndPath(TalesOfAduranton.MODID, "textures/entity/frogmantexture1.png"),
            ResourceLocation.fromNamespaceAndPath(TalesOfAduranton.MODID, "textures/entity/frogmantexture2.png")
    };
    private static final ResourceLocation EASTEREGG = ResourceLocation.fromNamespaceAndPath(TalesOfAduranton.MODID, "textures/entity/frogmanboca1.png");



    public FrogmanRender(EntityRendererProvider.Context context) {
        super(context, new FrogManModel(context.bakeLayer(FrogManModel.LAYER_LOCATION)), 0.5f);
        this.addLayer(new LayerFrogmanEyes(this));
    }


    @Override
    public void render(FrogManEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        this.entity = entity;
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(FrogManEntity frogManEntity) {
        if (this.entity.hasCustomName() && this.entity.getCustomName() != null && this.entity.getCustomName().getString().equals("Roman")) {
            return EASTEREGG;
        } else {
            return TEXTURAS[this.entity.index];
        }

    }
}
