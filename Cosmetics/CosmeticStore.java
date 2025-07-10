package com.example.cosmeticstore.store;

import com.example.cosmeticstore.model.Brand;
import com.example.cosmeticstore.model.CosmeticProduct;
import com.example.cosmeticstore.model.Product;
import com.example.cosmeticstore.model.ShoppingCart;

import java.util.*;

public class CosmeticStore {
    public static void main(String[] args) {
        // ... (unchanged code)

        try {
            // ... (unchanged code)

            // Select one or more products
            System.out.println("Select the products you want to purchase (enter product name):");
            System.out.println("Enter 'done' when finished.");

            while (true) {
                String selectedProductName = scanner.nextLine();

                if (selectedProductName.equalsIgnoreCase("done")) {
                    break;
                }

                CosmeticProduct selectedProduct = null;
                for (CosmeticProduct product : selectedBrand.getProducts()) {
                    if (product.getName().equalsIgnoreCase(selectedProductName)) {
                        selectedProduct = product;
                        break;
                    }
                }

                if (selectedProduct != null) {
                    // Display price and quantity available
                    System.out.println(selectedProduct.getName() + "\t$" + selectedProduct.getPrice()
                            + "\tAvailable Quantity: " + selectedBrand.getProductQuantity(selectedProductName));

                    // Enter the quantity
                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();

                    // Validate quantity
                    int availableQuantity = selectedBrand.getProductQuantity(selectedProductName);
                    if (quantity < 1 || quantity > availableQuantity) {
                        throw new IllegalArgumentException("Invalid quantity selection. Available Quantity: " + availableQuantity);
                    }

                    // Add the selected product to the cart
                    for (int i = 0; i < quantity; i++) {
                        cart.addItem(selectedProduct);
                    }

                    // Consume the newline character
                    scanner.nextLine();
                } else {
                    System.out.println("Invalid product selection.");
                }
            }

            // ... (unchanged code)

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
