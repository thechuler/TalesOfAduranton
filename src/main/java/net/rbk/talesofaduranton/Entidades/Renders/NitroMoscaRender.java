package net.rbk.talesofaduranton.Entidades.Renders;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


import net.rbk.talesofaduranton.Entidades.Modelos.NitroMoscaModel;

import net.rbk.talesofaduranton.Entidades.entity.NitroMoscaEntity;
import net.rbk.talesofaduranton.TalesOfAduranton;


public class NitroMoscaRender extends MobRenderer<NitroMoscaEntity, NitroMoscaModel<NitroMoscaEntity>> {

    private static final ResourceLocation TEXTURAS = ResourceLocation.fromNamespaceAndPath(TalesOfAduranton.MODID,"textures/entity/moskabumtexture.png");

    public NitroMoscaRender(EntityRendererProvider.Context context) {
        super(context, new NitroMoscaModel(context.bakeLayer(NitroMoscaModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(NitroMoscaEntity nitroMoscaEntity) {
        return TEXTURAS;
    }
}
