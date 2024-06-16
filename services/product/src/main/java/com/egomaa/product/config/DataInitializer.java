package com.egomaa.product.config;

import com.egomaa.product.entity.Category;
import com.egomaa.product.entity.Product;
import com.egomaa.product.repository.CategoryRepository;
import com.egomaa.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;


    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count() == 0) {
            Category watches = new Category(null,"Watches", "Category for various types of watches");
            Category laptops = new Category(null,"Laptops", "Category for different brands of laptops");
            Category mobiles = new Category(null,"Mobiles", "Category for various mobile phones");

            categoryRepository.saveAll(List.of(watches, laptops, mobiles));
            if (productRepository.count() == 0) {
                Product rolex = new Product(null, "Rolex Watch", "Luxury watch with gold strap", new BigDecimal("9999.99"), null, null, watches);
                Product appleWatch = new Product(null, "Apple Watch", "Smart watch with various features", new BigDecimal("399.99"), null, null, watches);
                Product casioWatch = new Product(null, "Casio Watch", "Digital watch with multiple functionalities", new BigDecimal("59.99"), null, null, watches);

                Product macBookPro = new Product(null, "MacBook Pro", "Apple laptop with M1 chip", new BigDecimal("1999.99"), null, null, laptops);
                Product dellXPS = new Product(null, "Dell XPS", "High-performance laptop with sleek design", new BigDecimal("1499.99"), null, null, laptops);
                Product hpSpectre = new Product(null, "HP Spectre", "Ultra-thin laptop with premium features", new BigDecimal("1299.99"), null, null, laptops);

                Product iPhone12 = new Product(null, "iPhone 12", "Apple smartphone with A14 chip", new BigDecimal("799.99"), null, null, mobiles);
                Product samsungGalaxyS21 = new Product(null, "Samsung Galaxy S21", "Flagship Android smartphone", new BigDecimal("699.99"), null, null, mobiles);
                Product googlePixel5 = new Product(null, "Google Pixel 5", "Google’s flagship smartphone", new BigDecimal("599.99"), null, null, mobiles);
                Product onePlus9 = new Product(null, "OnePlus 9", "High-end smartphone with Snapdragon 888", new BigDecimal("729.99"), null, null, mobiles);

                productRepository.saveAll(List.of(rolex, appleWatch, casioWatch, macBookPro, dellXPS, hpSpectre, iPhone12, samsungGalaxyS21, googlePixel5, onePlus9));
            }
        }





    }


}
