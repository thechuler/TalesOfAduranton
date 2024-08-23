package net.rbk.talesofaduranton.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.rbk.talesofaduranton.Bloques.InicializarBloques;
import net.rbk.talesofaduranton.TalesOfAduranton;


public class ProveedorBlockStateData extends BlockStateProvider {

    public ProveedorBlockStateData(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TalesOfAduranton.MODID, exFileHelper);
    }



    // Aca se van a registrar los modelos de los bloques.
    @Override
    protected void registerStatesAndModels() {
        blockWithItem(InicializarBloques.NITRO_FLUID_BLOCK);
        blockWithItem(InicializarBloques.GEODE_BLOCK);

    }



    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }


}
