package net.rbk.talesofaduranton.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.rbk.talesofaduranton.Bloques.InicializarBloques;
import net.rbk.talesofaduranton.items.InicializarItems;


import java.util.Set;
public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(),provider);
    }

    @Override
    protected void generate() {
        this.add(InicializarBloques.GEODE_BLOCK.get(),
               block -> createOreDrop(InicializarBloques.GEODE_BLOCK.get(), InicializarItems.GEODE.get())
                );



        this.add(InicializarBloques.NITRO_FLUID_BLOCK.get(),
                block -> createRedstoneOreDrops(InicializarBloques.NITRO_FLUID_BLOCK.get()));



    }



    protected LootTable.Builder createRedstoneOreDrops(Block pBlock) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, (LootPoolEntryContainer.Builder)this.applyExplosionDecay(pBlock, LootItem.lootTableItem(InicializarItems.NITRO_FLUID.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))));
    }



    @Override
    protected Iterable<Block> getKnownBlocks() {
        return () -> InicializarBloques.BLOQUES.getEntries().stream()
                .map(holder -> (Block) holder.value())
                .iterator();
    }

}