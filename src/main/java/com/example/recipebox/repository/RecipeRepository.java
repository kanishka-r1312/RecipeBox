package com.example.recipebox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipebox.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByCuisineIgnoreCase(String cuisine);

    List<Recipe> findByFavouriteTrue();
}