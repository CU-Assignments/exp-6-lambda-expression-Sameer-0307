package Experiments;

import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - " + price;
    }
}

public class ProductProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of products: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of product " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter category of product " + (i + 1) + ": ");
            String category = scanner.nextLine();
            System.out.print("Enter price of product " + (i + 1) + ": ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            products.add(new Product(name, category, price));
        }

        // Grouping products by category
        Map<String, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        
        System.out.println("\nProducts grouped by category:");
        groupedByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });

        // Finding the most expensive product in each category
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))));

        System.out.println("\nMost expensive product in each category:");
        mostExpensiveByCategory.forEach((category, product) ->
                System.out.println(category + ": " + product.orElse(null)));

        // Calculating the average price of all products
        double averagePrice = products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);

        System.out.println("\nAverage price of all products: " + averagePrice);
    }
}
