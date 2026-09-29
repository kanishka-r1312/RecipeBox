package com.example.recipebox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipebox.entity.MealPlan;
import com.example.recipebox.entity.Recipe;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {

    List<MealPlan> findByRecipe(Recipe recipe);

    List<MealPlan> findByDate(String date);
}