package net.rbk.talesofaduranton.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;

import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.rbk.talesofaduranton.GeneracionDeMundo.ModBiomeModifiers;
import net.rbk.talesofaduranton.GeneracionDeMundo.ModConfiguredFeatures;
import net.rbk.talesofaduranton.GeneracionDeMundo.ModPlacedFeatures;
import net.rbk.talesofaduranton.TalesOfAduranton;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ProveedorGeneracionMundo extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);


    public ProveedorGeneracionMundo(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TalesOfAduranton.MODID));
    }
}
