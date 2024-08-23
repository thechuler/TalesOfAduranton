package net.rbk.talesofaduranton.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.rbk.talesofaduranton.TalesOfAduranton;


import java.util.concurrent.CompletableFuture;



public class GeneradorData {


    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

       generator.addProvider(event.includeServer(), new ProveedorRecipe(packOutput,lookupProvider));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput,lookupProvider));
        generator.addProvider(event.includeClient(), new ProveedorBlockStateData(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ProveedorItemModel(packOutput, existingFileHelper));
        ProveedorBlockTag proveedorBlockTag = generator.addProvider(event.includeServer(), new ProveedorBlockTag(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ProveedorItemTag(packOutput, lookupProvider, proveedorBlockTag.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new ProveedorGeneracionMundo(packOutput, lookupProvider));



    }


}
