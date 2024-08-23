package net.rbk.talesofaduranton.Sonidos;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.talesofaduranton.TalesOfAduranton;


public class InicializarSonidos {

    public static final DeferredRegister<SoundEvent> SONIDOS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, TalesOfAduranton.MODID);




    public static final DeferredHolder<SoundEvent,SoundEvent> FROGMANAMBIENT =
            SONIDOS.register("frogmanambient", ()-> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath
                    (TalesOfAduranton.MODID,"frogmanambient")));


    public static final DeferredHolder<SoundEvent,SoundEvent> FROGMANDEATH =
            SONIDOS.register("frogmandeath", ()-> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath
                    (TalesOfAduranton.MODID,"frogmandeath")));


    public static final DeferredHolder<SoundEvent,SoundEvent> FROGMANHURT=
            SONIDOS.register("frogmanhurt", ()-> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath
                    (TalesOfAduranton.MODID,"frogmanhurt")));


    public static final DeferredHolder<SoundEvent,SoundEvent> FROGMANEASTEREGG=
            SONIDOS.register("frogmaneasteregg", ()-> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath
                    (TalesOfAduranton.MODID,"frogmaneasteregg")));



    public static final DeferredHolder<SoundEvent,SoundEvent> EMPTY_CANNON=
            SONIDOS.register("empty_cannon", ()-> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath
                    (TalesOfAduranton.MODID,"empty_cannon")));


    public static final DeferredHolder<SoundEvent,SoundEvent> CANNON_RELOAD=
            SONIDOS.register("cannon_reload", ()-> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath
                    (TalesOfAduranton.MODID,"cannon_reload")));



    public static final DeferredHolder<SoundEvent,SoundEvent> CANNON_CLICK=
            SONIDOS.register("cannon_click", ()-> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath
                    (TalesOfAduranton.MODID,"cannon_click")));


    public static final DeferredHolder<SoundEvent,SoundEvent> CANNON_SHOOT=
            SONIDOS.register("cannon_shoot", ()-> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath
                    (TalesOfAduranton.MODID,"cannon_shoot")));



    public static final DeferredHolder<SoundEvent,SoundEvent> NITRO_FLUID_SPLASH=
            SONIDOS.register("nitro_fluid_splash", ()-> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath
                    (TalesOfAduranton.MODID,"nitro_fluid_splash")));






    public static void register(IEventBus eventBus) {
        SONIDOS.register(eventBus);
    }

}
