package net.rbk.talesofaduranton.items.custom.LavaEaters;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.rbk.talesofaduranton.TalesOfAduranton;

@EventBusSubscriber(modid = TalesOfAduranton.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegistrarLayerDevorador {
    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        EntityModelSet entityModelSet = event.getEntityModels();
        for (PlayerSkin.Model skin : event.getSkins()) {
            PlayerRenderer playerRenderer = event.getSkin(skin);
            if (playerRenderer != null) {
                playerRenderer.addLayer(new DevoradorDeLavaSpinRender<>(playerRenderer, entityModelSet));
            }
        }
    }



}
