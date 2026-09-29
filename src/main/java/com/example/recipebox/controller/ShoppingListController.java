package com.example.recipebox.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.recipebox.entity.ShoppingList;
import com.example.recipebox.service.ShoppingListService;

@RestController
@RequestMapping("/api/shopping-list")
@CrossOrigin(origins = {
        "http://localhost:5500",
        "http://127.0.0.1:5500"
})
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(
            ShoppingListService shoppingListService) {

        this.shoppingListService = shoppingListService;
    }

    @PostMapping
    public ResponseEntity<ShoppingList> createItem(
            @RequestBody ShoppingList item) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    shoppingListService.createItem(item)
                );
    }

    @GetMapping
    public ResponseEntity<List<ShoppingList>> getAllItems() {

        return ResponseEntity.ok(
                shoppingListService.getAllItems()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingList> getItemById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                shoppingListService.getItemById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingList> updateItem(
            @PathVariable Long id,
            @RequestBody ShoppingList item) {

        return ResponseEntity.ok(
                shoppingListService.updateItem(
                    id,
                    item
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(
            @PathVariable Long id) {

        shoppingListService.deleteItem(id);

        return ResponseEntity.ok(
                Map.of("message", "Shopping item deleted successfully")
        );
    }
}