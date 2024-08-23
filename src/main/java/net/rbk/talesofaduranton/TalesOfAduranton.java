package net.rbk.talesofaduranton;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.rbk.talesofaduranton.Bloques.InicializarBloques;

import net.rbk.talesofaduranton.Entidades.InicializarEntidades;
import net.rbk.talesofaduranton.Entidades.Modelos.FrogManModel;
import net.rbk.talesofaduranton.Entidades.Modelos.NitroMoscaModel;
import net.rbk.talesofaduranton.Entidades.Renders.FrogmanRender;
import net.rbk.talesofaduranton.Entidades.Renders.NitroMoscaRender;
import net.rbk.talesofaduranton.Particulas.Custom.ParticulaDeVeneno;
import net.rbk.talesofaduranton.Particulas.Custom.ParticulaNitroFluido;
import net.rbk.talesofaduranton.Particulas.InicializarParticulas;
import net.rbk.talesofaduranton.Sonidos.InicializarSonidos;
import net.rbk.talesofaduranton.datagen.GeneradorData;
import net.rbk.talesofaduranton.items.InicializarCreativeTab;
import net.rbk.talesofaduranton.items.InicializarItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;



@Mod(TalesOfAduranton.MODID)
public class TalesOfAduranton
{
    public static final String MODID = "talesofaduranton";
    private static final Logger LOGGER = LogUtils.getLogger();




    public TalesOfAduranton(IEventBus modEventBus, ModContainer modContainer)
    {
        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::commonSetup);

        InicializarItems.registrar(modEventBus);
        InicializarBloques.registrar(modEventBus);
        InicializarSonidos.register(modEventBus);
        InicializarParticulas.register(modEventBus);
        InicializarEntidades.ENTIDADES.register(modEventBus);

        InicializarCreativeTab.registrar(modEventBus);
        modEventBus.addListener(GeneradorData::gatherData);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }


    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }





    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(InicializarEntidades.FROGMAN.get(),FrogmanRender::new);
            event.registerEntityRenderer(InicializarEntidades.NITRO_FLY.get(),NitroMoscaRender::new);
        }

        @SubscribeEvent
        public  static  void  registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
            event.registerLayerDefinition(FrogManModel.LAYER_LOCATION, FrogManModel::createBodyLayer);
            event.registerLayerDefinition(NitroMoscaModel.LAYER_LOCATION, NitroMoscaModel::createBodyLayer);
        }



        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(InicializarBloques.NITRO_FLUID_BLOCK.get(), RenderType.translucent());
            EntityRenderers.register(InicializarEntidades.CANNON_PROJECTILE .get(), ThrownItemRenderer::new);
            EntityRenderers.register(InicializarEntidades.DECAYED_PROJECTILE.get(),ThrownItemRenderer::new);


        }





    }
}
