package com.example.recipebox.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recipebox.entity.Recipe;
import com.example.recipebox.exception.ResourceNotFoundException;
import com.example.recipebox.repository.RecipeRepository;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(Long id) {

        return recipeRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Recipe not found with id: " + id
                    )
                );
    }

    public Recipe updateRecipe(Long id, Recipe recipe) {

        Recipe existing = getRecipeById(id);

        existing.setName(recipe.getName());
        existing.setDescription(recipe.getDescription());
        existing.setSteps(recipe.getSteps());
        existing.setPrepTime(recipe.getPrepTime());
        existing.setCuisine(recipe.getCuisine());
        existing.setFavourite(recipe.isFavourite());

        return recipeRepository.save(existing);
    }

    public void deleteRecipe(Long id) {

        Recipe recipe = getRecipeById(id);

        recipeRepository.delete(recipe);
    }

    public List<Recipe> getRecipesByCuisine(String cuisine) {
        return recipeRepository.findByCuisineIgnoreCase(cuisine);
    }

    public List<Recipe> getFavouriteRecipes() {
        return recipeRepository.findByFavouriteTrue();
    }
}