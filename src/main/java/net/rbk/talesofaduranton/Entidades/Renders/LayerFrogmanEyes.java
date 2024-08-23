package net.rbk.talesofaduranton.Entidades.Renders;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.rbk.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbk.talesofaduranton.Entidades.entity.FrogManEntity;
import net.rbk.talesofaduranton.TalesOfAduranton;


@OnlyIn(Dist.CLIENT)
public class LayerFrogmanEyes<T extends FrogManEntity> extends EyesLayer<T, FrogManModel<T>> {

    private static final RenderType FROGMAN_EYES = RenderType.eyes(ResourceLocation
            .fromNamespaceAndPath(TalesOfAduranton.MODID,"textures/entity/frogman_eyes.png"));

    public LayerFrogmanEyes(RenderLayerParent<T, FrogManModel<T>> pRenderer) {
        super(pRenderer);
    }

    public RenderType renderType() {
        return FROGMAN_EYES;
    }
}
