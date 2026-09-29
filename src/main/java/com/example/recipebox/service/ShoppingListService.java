package com.example.recipebox.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recipebox.entity.ShoppingList;
import com.example.recipebox.exception.ResourceNotFoundException;
import com.example.recipebox.repository.ShoppingListRepository;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(
            ShoppingListRepository shoppingListRepository) {

        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingList createItem(ShoppingList item) {
        return shoppingListRepository.save(item);
    }

    public List<ShoppingList> getAllItems() {
        return shoppingListRepository.findAll();
    }

    public ShoppingList getItemById(Long id) {

        return shoppingListRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Shopping item not found with id: " + id
                    )
                );
    }

    public ShoppingList updateItem(
            Long id,
            ShoppingList item) {

        ShoppingList existing = getItemById(id);

        existing.setItemName(item.getItemName());
        existing.setQuantity(item.getQuantity());
        existing.setPurchased(item.isPurchased());

        return shoppingListRepository.save(existing);
    }

    public void deleteItem(Long id) {

        ShoppingList item = getItemById(id);

        shoppingListRepository.delete(item);
    }
}