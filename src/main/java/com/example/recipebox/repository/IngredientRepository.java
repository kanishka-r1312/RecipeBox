package com.example.recipebox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipebox.entity.Ingredient;
import com.example.recipebox.entity.Recipe;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByRecipe(Recipe recipe);
}