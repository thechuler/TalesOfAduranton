package net.rbk.talesofaduranton.GeneracionDeMundo;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.rbk.talesofaduranton.Entidades.InicializarEntidades;
import net.rbk.talesofaduranton.TalesOfAduranton;

import java.util.Collections;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_GEODE_BLOCK = registerKey("add_geode_block");
    public static final ResourceKey<BiomeModifier> SPAWNEAR_FROGMAN = registerKey("spawn_frogman");
    public static final ResourceKey<BiomeModifier> SPAWNEAR_NITROMOSCA = registerKey("spawn_nitromosca");




    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);



        context.register(ADD_GEODE_BLOCK, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PIEDRA_CARGADA_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));






        HolderSet<Biome> BIOMASFROGMAN = HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP));
        context.register(SPAWNEAR_FROGMAN, new BiomeModifiers.AddSpawnsBiomeModifier(BIOMASFROGMAN,
                Collections.singletonList(new MobSpawnSettings.SpawnerData(InicializarEntidades.FROGMAN.get(),
                        100, 4, 6))));





        HolderSet<Biome> BIOMESNITROFLY = HolderSet.direct(biomes.getOrThrow(Biomes.WARPED_FOREST));
        context.register(SPAWNEAR_NITROMOSCA, new BiomeModifiers.AddSpawnsBiomeModifier(BIOMESNITROFLY,
                Collections.singletonList(new MobSpawnSettings.SpawnerData(InicializarEntidades.NITRO_FLY.get(),
                        80, 6, 10))));





    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(TalesOfAduranton.MODID, name));
    }

}
