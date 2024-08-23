package net.rbk.talesofaduranton.Particulas;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.talesofaduranton.Particulas.Custom.ParticulaDeVeneno;
import net.rbk.talesofaduranton.Particulas.Custom.ParticulaNitroFluido;
import net.rbk.talesofaduranton.TalesOfAduranton;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class InicializarParticulas {
    public static final DeferredRegister<ParticleType<?>> PARTICULAS = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, TalesOfAduranton.MODID);


    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> POISON_PARTICLES =
            PARTICULAS.register("poison_particles", () -> new SimpleParticleType(true));



    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> NITRO_FLUID_PARTICLES =
            PARTICULAS.register("nitro_fluid_particles", () -> new SimpleParticleType(true));




    public static void register(IEventBus eventBus) {
        PARTICULAS.register(eventBus);
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(InicializarParticulas.NITRO_FLUID_PARTICLES.get(), ParticulaNitroFluido.Provider::new);
        Minecraft.getInstance().particleEngine.register(InicializarParticulas.POISON_PARTICLES.get(), ParticulaDeVeneno.Provider::new);
    }


}
