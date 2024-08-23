package net.rbk.talesofaduranton.datagen;


import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import net.rbk.talesofaduranton.TalesOfAduranton;
import net.rbk.talesofaduranton.items.InicializarItems;


public class ProveedorItemModel extends ItemModelProvider {

    public ProveedorItemModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TalesOfAduranton.MODID, existingFileHelper);
    }



    @Override
    protected void registerModels() {
        simpleItem(InicializarItems.GEODE);
        simpleItem(InicializarItems.COOKED_FROG_MEAT);
        simpleItem(InicializarItems.RAW_FROG_MEAT);
        simpleItem(InicializarItems.POISON_GLAND);
        simpleItem(InicializarItems.CANNON_CHARGE);
        simpleItem(InicializarItems.DECAYED_CHARGE);
        simpleItem(InicializarItems.UNSTABLE_CHARGE);
        simpleItem(InicializarItems.NITRO_FLUID);
        simpleItem(InicializarItems.FERROMAGMA_INGOT);
        simpleItem(InicializarItems.CANNON_HEAD);
        simpleItem(InicializarItems.GUN_FRAME);
        withExistingParent(InicializarItems.FROG_MAN_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
        withExistingParent(InicializarItems.NITRO_FLY_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
    }


    private ItemModelBuilder simpleItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated"))
                .texture("layer0",
                        ResourceLocation.fromNamespaceAndPath(TalesOfAduranton.MODID, "item/" + item.getId().getPath()));
    }
}
