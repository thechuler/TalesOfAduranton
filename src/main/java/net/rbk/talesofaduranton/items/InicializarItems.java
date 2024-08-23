package net.rbk.talesofaduranton.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.talesofaduranton.Entidades.InicializarEntidades;
import net.rbk.talesofaduranton.TalesOfAduranton;
import net.rbk.talesofaduranton.items.custom.LavaEaters.DevoradorDeLava;

import net.rbk.talesofaduranton.items.custom.LilRocky;
import net.rbk.talesofaduranton.items.custom.geodaItem;


public class InicializarItems {



    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TalesOfAduranton.MODID);



    public static final DeferredItem<Item> GEODE = ITEMS.register("geode",()-> new geodaItem(new Item.Properties()));

    public static final DeferredItem<Item> POISON_GLAND = ITEMS.register("poison_gland",()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_FROG_MEAT = ITEMS.register("raw_frog_meat",()-> new Item(new Item.Properties().
            food(new FoodProperties.Builder().nutrition(4).saturationModifier(1.2f).effect(new MobEffectInstance(MobEffects.POISON,100),30).build())));

    public static final DeferredItem<Item> COOKED_FROG_MEAT = ITEMS.register("cooked_frog_meat",()-> new Item(new Item.Properties().
            food(new FoodProperties.Builder().nutrition(7).saturationModifier(5.2f).build())));

    public static final DeferredItem<Item> FERROMAGMA_INGOT = ITEMS.register("ferromagma_ingot",()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GUN_FRAME = ITEMS.register("gun_frame",()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CANNON_HEAD = ITEMS.register("cannon_head",()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CANNON_CHARGE = ITEMS.register("cannon_charge",()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DECAYED_CHARGE = ITEMS.register("decayed_charge",()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> UNSTABLE_CHARGE = ITEMS.register("unstable_charge",()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NITRO_FLUID = ITEMS.register("nitro_fluid",()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIL_ROCKY = ITEMS.register("lil_rocky",()-> new LilRocky(new Item.Properties()));
    public static final DeferredItem<Item> LAVA_EATERS = ITEMS.register("lava_eaters",()-> new DevoradorDeLava(Tiers.NETHERITE, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND,5,-2.5F)).stacksTo(2).rarity(Rarity.RARE).durability(100).fireResistant()));
    public static final DeferredItem<Item> FROG_MAN_SPAWN_EGG = ITEMS.register("frogman_spawn_egg", () -> new SpawnEggItem(InicializarEntidades.FROGMAN.get(), 0x428430, 0xa8a84e, new Item.Properties()));
    public static final DeferredItem<Item> NITRO_FLY_SPAWN_EGG = ITEMS.register("nitro_fly_spawn_egg", () -> new SpawnEggItem(InicializarEntidades.NITRO_FLY.get(), 0x701717, 0x2ca3a3, new Item.Properties()));





/*


    public static final RegistryObject<Item> FROG_MAN_SPAWN_EGG = ITEMS.register("frogman_spawn_egg",
            () -> new ForgeSpawnEggItem(InicializarEntidades.FROGMAN_ENTITY, 0x428430, 0xa8a84e, new Item.Properties()));


    public static final RegistryObject<Item> NITRO_MOSCA_SPAWN_EGG = ITEMS.register("nitro_mosca_spawn_egg",
            () -> new ForgeSpawnEggItem(InicializarEntidades.NITRO_MOSCA_ENTITY, 0x701717, 0x2ca3a3, new Item.Properties()));


*/






    public static void registrar(IEventBus bus){
        ITEMS.register(bus);
    }






}
