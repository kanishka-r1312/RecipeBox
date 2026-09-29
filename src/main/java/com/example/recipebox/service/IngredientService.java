package com.example.recipebox.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recipebox.entity.Ingredient;
import com.example.recipebox.entity.Recipe;
import com.example.recipebox.exception.ResourceNotFoundException;
import com.example.recipebox.repository.IngredientRepository;
import com.example.recipebox.repository.RecipeRepository;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public IngredientService(
            IngredientRepository ingredientRepository,
            RecipeRepository recipeRepository) {

        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    public Ingredient createIngredient(Long recipeId, Ingredient ingredient) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Recipe not found with id: " + recipeId
                    )
                );

        ingredient.setRecipe(recipe);

        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Long id) {

        return ingredientRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Ingredient not found with id: " + id
                    )
                );
    }

    public List<Ingredient> getIngredientsByRecipe(Long recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Recipe not found with id: " + recipeId
                    )
                );

        return ingredientRepository.findByRecipe(recipe);
    }

    public Ingredient updateIngredient(
            Long id,
            Ingredient ingredient) {

        Ingredient existing = getIngredientById(id);

        existing.setName(ingredient.getName());
        existing.setQuantity(ingredient.getQuantity());

        return ingredientRepository.save(existing);
    }

    public void deleteIngredient(Long id) {

        Ingredient ingredient = getIngredientById(id);

        ingredientRepository.delete(ingredient);
    }
}