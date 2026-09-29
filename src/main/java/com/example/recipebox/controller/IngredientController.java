package com.example.recipebox.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.recipebox.entity.Ingredient;
import com.example.recipebox.service.IngredientService;

@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin(origins = {
        "http://localhost:5500",
        "http://127.0.0.1:5500"
})
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/recipe/{recipeId}")
    public ResponseEntity<Ingredient> createIngredient(
            @PathVariable Long recipeId,
            @RequestBody Ingredient ingredient) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    ingredientService.createIngredient(
                        recipeId,
                        ingredient
                    )
                );
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {

        return ResponseEntity.ok(
                ingredientService.getAllIngredients()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ingredientService.getIngredientById(id)
        );
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<List<Ingredient>> getByRecipe(
            @PathVariable Long recipeId) {

        return ResponseEntity.ok(
                ingredientService.getIngredientsByRecipe(recipeId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(
            @PathVariable Long id,
            @RequestBody Ingredient ingredient) {

        return ResponseEntity.ok(
                ingredientService.updateIngredient(
                    id,
                    ingredient
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(
            @PathVariable Long id) {

        ingredientService.deleteIngredient(id);

        return ResponseEntity.ok(
                Map.of("message", "Ingredient deleted successfully")
        );
    }
}