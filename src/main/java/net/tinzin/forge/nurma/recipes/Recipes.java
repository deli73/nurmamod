package net.tinzin.forge.nurma.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.tinzin.forge.nurma.blocks.ModBlocks;
import net.tinzin.forge.nurma.items.ModItems;

import java.util.HashMap;
import java.util.Map;

public class Recipes {
    public Map<Ingredient, ItemStack> recipes = new HashMap<>();
    public static Recipes INSTANCE = new Recipes();

    public Recipes(){
        Ingredient[] in = {Ingredient.fromItem(Items.EMERALD),      Ingredient.fromItem(Items.DIAMOND),     Ingredient.fromItem(Items.PRISMARINE_CRYSTALS), Ingredient.fromItem(Items.QUARTZ),      Ingredient.fromItem(Item.getItemFromBlock(Blocks.GLASS))};
        ItemStack[] out = {new ItemStack(ModItems.refinedEmerald),  new ItemStack(ModItems.refinedDiamond), new ItemStack(ModItems.refinedPrismarine),      new ItemStack(ModItems.refinedQuartz),  new ItemStack(ModItems.glassShard)};
        addRecipes(in,out);

        Ingredient[] in2= {Ingredient.fromItem(Items.GOLD_NUGGET),  Ingredient.fromStacks(new ItemStack(Items.DYE,1,4)),       Ingredient.fromItem(Item.getItemFromBlock(ModBlocks.stonePolished)), Ingredient.fromStacks(new ItemStack(Items.COAL,1,1)), Ingredient.fromStacks(new ItemStack(Item.getItemFromBlock(Blocks.STONE),1,5))};
        ItemStack[] out2= {new ItemStack(ModItems.goldDust),        new ItemStack(ModItems.lapisDust),                                       new ItemStack(ModItems.tabletStone),                                 new ItemStack(ModItems.soot),                                       new ItemStack(Item.getItemFromBlock(ModBlocks.inkstone))};
        addRecipes(in2,out2);
    }

    public void addRecipe(Ingredient input, ItemStack output){
        recipes.put(input,output);
    }

    public void addRecipes(Ingredient[] inputs, ItemStack[] outputs){
        if(inputs.length != outputs.length){
            String[] nope = {"unbalanced recipe lists!!!"};
            throw new AssertionError(nope);
        }
        for (int i = 0; i < inputs.length; i++) {
            addRecipe(inputs[i],outputs[i]);
        }
    }

    public static boolean keyCheck(ItemStack key){
        for (Ingredient i : INSTANCE.recipes.keySet()){
            if(keyCompare(key,i)){ return true; }
        }
        return false;
    }

    public static boolean keyCompare(ItemStack key, Ingredient internal){
        return internal.apply(key);
    }

    public static ItemStack getOutputFor(ItemStack key){
        for (Ingredient i : INSTANCE.recipes.keySet()){
            if(keyCompare(key,i)){
                return INSTANCE.recipes.get(i);
            }
        }
        return key;
    }

}
