package net.rbk.talesofaduranton.Entidades;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.talesofaduranton.Entidades.Proyectiles.BalaDeCannonEntity;
import net.rbk.talesofaduranton.Entidades.Proyectiles.BalaDeCannonInestableEntity;
import net.rbk.talesofaduranton.Entidades.Proyectiles.BalaDeCannonPutrefactaEntity;
import net.rbk.talesofaduranton.Entidades.entity.FrogManEntity;
import net.rbk.talesofaduranton.Entidades.entity.NitroMoscaEntity;
import net.rbk.talesofaduranton.TalesOfAduranton;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class InicializarEntidades {

    public static final DeferredRegister<EntityType<?>> ENTIDADES = DeferredRegister.create(Registries.ENTITY_TYPE,
            TalesOfAduranton.MODID);



    public static final DeferredHolder<EntityType<?>, EntityType<BalaDeCannonEntity>>CANNON_PROJECTILE =
            ENTIDADES.register("cannon_projectile", () -> EntityType.Builder.<BalaDeCannonEntity>of(BalaDeCannonEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("cannon_projectile"));


    public static final DeferredHolder<EntityType<?>, EntityType<BalaDeCannonPutrefactaEntity>>DECAYED_PROJECTILE =
            ENTIDADES.register("decayed_projectile", () -> EntityType.Builder.of(BalaDeCannonPutrefactaEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("cannon_projectile"));





    public static final DeferredHolder<EntityType<?>, EntityType<FrogManEntity>> FROGMAN =ENTIDADES.register("frogman_entity",
            () -> EntityType.Builder.of(FrogManEntity::new, MobCategory.CREATURE).sized(1f,1.3f)
                    .build(ResourceLocation.fromNamespaceAndPath(TalesOfAduranton.MODID,"frogman_entity").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<NitroMoscaEntity>> NITRO_FLY = ENTIDADES.register("nitro_mosca_entity",
            ()->EntityType.Builder.of(NitroMoscaEntity::new,MobCategory.MONSTER).sized(1.2f,1f)
                    .build(ResourceLocation.fromNamespaceAndPath(TalesOfAduranton.MODID,"nitro_mosca_entity").toString()));







    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
         FrogManEntity.init();
         NitroMoscaEntity.init();

        });
    }


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(FROGMAN.get(), FrogManEntity.createAttributes().build());
        event.put(NITRO_FLY.get(), NitroMoscaEntity.createAttributes().build());
    }






    @SubscribeEvent
    public  static void  RegistrarLugardeSpawn(RegisterSpawnPlacementsEvent event){

        event.register(InicializarEntidades.FROGMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE,FrogManEntity::PuedeSpawnear,RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(InicializarEntidades.NITRO_FLY.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NitroMoscaEntity::PuedeSpawnear,RegisterSpawnPlacementsEvent.Operation.REPLACE);


    }

}
