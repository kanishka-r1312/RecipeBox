package com.example.recipebox.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.recipebox.entity.Recipe;
import com.example.recipebox.service.RecipeService;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = {
        "http://localhost:5500",
        "http://127.0.0.1:5500"
})
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(
            @RequestBody Recipe recipe) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recipeService.createRecipe(recipe));
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {

        return ResponseEntity.ok(
                recipeService.getAllRecipes()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                recipeService.getRecipeById(id)
        );
    }

    @GetMapping("/cuisine/{cuisine}")
    public ResponseEntity<List<Recipe>> getByCuisine(
            @PathVariable String cuisine) {

        return ResponseEntity.ok(
                recipeService.getRecipesByCuisine(cuisine)
        );
    }

    @GetMapping("/favourites")
    public ResponseEntity<List<Recipe>> getFavourites() {

        return ResponseEntity.ok(
                recipeService.getFavouriteRecipes()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(
            @PathVariable Long id,
            @RequestBody Recipe recipe) {

        return ResponseEntity.ok(
                recipeService.updateRecipe(id, recipe)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(
            @PathVariable Long id) {

        recipeService.deleteRecipe(id);

        return ResponseEntity.ok(
                Map.of("message", "Recipe deleted successfully")
        );
    }
}