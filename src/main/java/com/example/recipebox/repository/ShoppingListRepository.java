package com.example.recipebox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.recipebox.entity.ShoppingList;

public interface ShoppingListRepository
        extends JpaRepository<ShoppingList, Long> {
}