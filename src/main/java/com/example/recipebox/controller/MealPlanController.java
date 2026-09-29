package com.example.recipebox.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.recipebox.entity.MealPlan;
import com.example.recipebox.entity.ShoppingList;
import com.example.recipebox.service.MealPlanService;

@RestController
@RequestMapping("/api/mealplans")
@CrossOrigin(origins = {
        "http://localhost:5500",
        "http://127.0.0.1:5500"
})
public class MealPlanController {

    private final MealPlanService mealPlanService;

    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @PostMapping("/recipe/{recipeId}")
    public ResponseEntity<MealPlan> createMealPlan(
            @PathVariable Long recipeId,
            @RequestBody MealPlan mealPlan) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    mealPlanService.createMealPlan(
                        recipeId,
                        mealPlan
                    )
                );
    }

    @GetMapping
    public ResponseEntity<List<MealPlan>> getAllMealPlans() {

        return ResponseEntity.ok(
                mealPlanService.getAllMealPlans()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealPlan> getMealPlanById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                mealPlanService.getMealPlanById(id)
        );
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<MealPlan>> getByDate(
            @PathVariable String date) {

        return ResponseEntity.ok(
                mealPlanService.getMealPlansByDate(date)
        );
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<List<MealPlan>> getByRecipe(
            @PathVariable Long recipeId) {

        return ResponseEntity.ok(
                mealPlanService.getMealPlansByRecipe(recipeId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealPlan> updateMealPlan(
            @PathVariable Long id,
            @RequestBody MealPlan mealPlan) {

        return ResponseEntity.ok(
                mealPlanService.updateMealPlan(
                    id,
                    mealPlan
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMealPlan(
            @PathVariable Long id) {

        mealPlanService.deleteMealPlan(id);

        return ResponseEntity.ok(
                Map.of("message", "Meal plan deleted successfully")
        );
    }

    @PostMapping("/generate-shopping-list")
    public ResponseEntity<List<ShoppingList>> generateShoppingList() {

        return ResponseEntity.ok(
                mealPlanService.generateShoppingList()
        );
    }

    @PostMapping("/shopping-list/generate")
    public ResponseEntity<List<ShoppingList>> generateShoppingListAlternative() {

        return ResponseEntity.ok(
                mealPlanService.generateShoppingList()
        );
    }
}