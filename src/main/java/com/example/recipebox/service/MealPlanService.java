package com.example.recipebox.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recipebox.entity.Ingredient;
import com.example.recipebox.entity.MealPlan;
import com.example.recipebox.entity.Recipe;
import com.example.recipebox.entity.ShoppingList;
import com.example.recipebox.exception.ResourceNotFoundException;
import com.example.recipebox.repository.IngredientRepository;
import com.example.recipebox.repository.MealPlanRepository;
import com.example.recipebox.repository.RecipeRepository;
import com.example.recipebox.repository.ShoppingListRepository;

@Service
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final ShoppingListRepository shoppingListRepository;

    public MealPlanService(
            MealPlanRepository mealPlanRepository,
            RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository,
            ShoppingListRepository shoppingListRepository) {

        this.mealPlanRepository = mealPlanRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

    public MealPlan createMealPlan(Long recipeId, MealPlan mealPlan) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Recipe not found with id: " + recipeId
                    )
                );

        mealPlan.setRecipe(recipe);

        return mealPlanRepository.save(mealPlan);
    }

    public List<MealPlan> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }

    public MealPlan getMealPlanById(Long id) {

        return mealPlanRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Meal plan not found with id: " + id
                    )
                );
    }

    public MealPlan updateMealPlan(Long id, MealPlan mealPlan) {

        MealPlan existing = getMealPlanById(id);

        existing.setDate(mealPlan.getDate());
        existing.setMealType(mealPlan.getMealType());

        if (mealPlan.getRecipe() != null
                && mealPlan.getRecipe().getId() != null) {

            Recipe recipe = recipeRepository
                    .findById(mealPlan.getRecipe().getId())
                    .orElseThrow(() ->
                        new ResourceNotFoundException(
                            "Recipe not found"
                        )
                    );

            existing.setRecipe(recipe);
        }

        return mealPlanRepository.save(existing);
    }

    public void deleteMealPlan(Long id) {

        MealPlan mealPlan = getMealPlanById(id);

        mealPlanRepository.delete(mealPlan);
    }

    public List<MealPlan> getMealPlansByDate(String date) {
        return mealPlanRepository.findByDate(date);
    }

    public List<MealPlan> getMealPlansByRecipe(Long recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Recipe not found with id: " + recipeId
                    )
                );

        return mealPlanRepository.findByRecipe(recipe);
    }

    public List<ShoppingList> generateShoppingList() {

        List<MealPlan> mealPlans = mealPlanRepository.findAll();

        List<ShoppingList> shoppingItems = new ArrayList<>();

        for (MealPlan mealPlan : mealPlans) {

            Recipe recipe = mealPlan.getRecipe();

            if (recipe == null) {
                continue;
            }

            List<Ingredient> ingredients =
                    ingredientRepository.findByRecipe(recipe);

            for (Ingredient ingredient : ingredients) {

                ShoppingList item = new ShoppingList();

                item.setItemName(ingredient.getName());
                item.setQuantity(ingredient.getQuantity());
                item.setPurchased(false);

                shoppingItems.add(item);
            }
        }

        return shoppingListRepository.saveAll(shoppingItems);
    }
}