package net.rbk.talesofaduranton.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.rbk.talesofaduranton.TalesOfAduranton;
import net.rbk.talesofaduranton.items.InicializarItems;


import java.util.concurrent.CompletableFuture;

public class ProveedorRecipe extends RecipeProvider implements IConditionBuilder {


    public ProveedorRecipe(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(pOutput, lookupProvider);
    }


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
     simpleCookingRecipe(recipeOutput,"smelting",RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new,200, InicializarItems.RAW_FROG_MEAT.get(),InicializarItems.COOKED_FROG_MEAT.get(),0.35f);
     simpleCookingRecipe(recipeOutput,"smoking",RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new,100, InicializarItems.RAW_FROG_MEAT.get(),InicializarItems.COOKED_FROG_MEAT.get(),0.35f);
     simpleCookingRecipe(recipeOutput,"campfire_cooking",RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new,200, InicializarItems.RAW_FROG_MEAT.get(),InicializarItems.COOKED_FROG_MEAT.get(),0.35f);






        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InicializarItems.CANNON_HEAD.get())
                .pattern("OOO")
                .pattern("GHG")
                .define('H', Items.IRON_INGOT)
                .define('G', Items.GOLD_INGOT)
                .define('O', InicializarItems.FERROMAGMA_INGOT.get())
                .unlockedBy("has_netherite", has(InicializarItems.FERROMAGMA_INGOT.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,InicializarItems.GUN_FRAME.get())
                .pattern("HOH")
                .pattern("G G")
                .define('H', Items.IRON_INGOT)
                .define('G', Items.GOLD_INGOT)
                .define('O', InicializarItems.FERROMAGMA_INGOT.get())
                .unlockedBy("has_ferromagma", has(InicializarItems.FERROMAGMA_INGOT.get()))
                .save(recipeOutput);





        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,InicializarItems.LIL_ROCKY.get())
                .pattern("FOH")
                .define('O', InicializarItems.FERROMAGMA_INGOT.get())
                .define('H', InicializarItems.CANNON_HEAD.get())
                .define('F', InicializarItems.GUN_FRAME.get())

                .unlockedBy("has_gunpart", has(InicializarItems.GUN_FRAME.get()))
                .unlockedBy("has_gunpart2", has(InicializarItems.CANNON_HEAD.get()))
                .save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,InicializarItems.LAVA_EATERS.get())
                .pattern("OOO")
                .pattern("OCO")
                .pattern("HHH")
                .define('H', Items.LEATHER)
                .define('C', Items.NETHER_STAR)
                .define('O', InicializarItems.FERROMAGMA_INGOT.get())
                .unlockedBy("has_ferromagma", has(InicializarItems.FERROMAGMA_INGOT.get()))
                .save(recipeOutput);



        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,InicializarItems.CANNON_CHARGE.get(), 3)
                .requires(InicializarItems.NITRO_FLUID.get())
                .requires(Items.GUNPOWDER)
                .requires(Items.COBBLESTONE)
                .unlockedBy("has_rocky", has(InicializarItems.LIL_ROCKY.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,InicializarItems.DECAYED_CHARGE.get(), 1)
                .requires(InicializarItems.CANNON_CHARGE.get())
                .requires(InicializarItems.POISON_GLAND.get())
                .unlockedBy("has_rocky", has(InicializarItems.LIL_ROCKY.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,InicializarItems.UNSTABLE_CHARGE.get(), 1)
                .requires(InicializarItems.CANNON_CHARGE.get())
                .requires(Items.GUNPOWDER)
                .unlockedBy("has_rocky", has(InicializarItems.LIL_ROCKY.get()))
                .save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,InicializarItems.FERROMAGMA_INGOT.get(), 1)
                .requires(InicializarItems.NITRO_FLUID.get())
                .requires(Items.NETHERITE_INGOT)
                .requires(Items.BLAZE_POWDER)
                .unlockedBy("has_nitrofluido", has(InicializarItems.NITRO_FLUID.get()))
                .save(recipeOutput);



    }


    protected static <T extends AbstractCookingRecipe> void simpleCookingRecipe(RecipeOutput pRecipeOutput, String pCookingMethod, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> pRecipeFactory, int pCookingTime, ItemLike pMaterial, ItemLike pResult, float pExperience) {
        SimpleCookingRecipeBuilder var10000 = SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{pMaterial}), RecipeCategory.FOOD, pResult, pExperience, pCookingTime, pCookingSerializer, pRecipeFactory).unlockedBy(getHasName(pMaterial), has(pMaterial));
        String var10002 = getItemName(pResult);
        var10000.save(pRecipeOutput, TalesOfAduranton.MODID + ":" + var10002 + "_from_" + pCookingMethod);
    }

}
