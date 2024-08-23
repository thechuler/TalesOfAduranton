package net.rbk.talesofaduranton.Bloques;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rbk.talesofaduranton.Bloques.custom.BloqueNitroFluido;
import net.rbk.talesofaduranton.TalesOfAduranton;
import net.rbk.talesofaduranton.items.InicializarItems;


import java.util.function.Supplier;

public class InicializarBloques {


    public static final DeferredRegister.Blocks BLOQUES =
            DeferredRegister.createBlocks(TalesOfAduranton.MODID);


    public static final DeferredBlock<Block> GEODE_BLOCK = registerBlock("geode_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE))
    );

    public static final DeferredBlock<Block> NITRO_FLUID_BLOCK = registerBlock("nitro_fluid_block",
            () -> new BloqueNitroFluido(BlockBehaviour.Properties.ofFullCopy(Blocks.SLIME_BLOCK))
    );




    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOQUES.register(name, block);
        RegistrarItemDelBloque(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> DeferredItem<Item> RegistrarItemDelBloque(String name, DeferredBlock<T> block) {
        return InicializarItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void registrar(IEventBus bus) {
        BLOQUES.register(bus);
    }
}
