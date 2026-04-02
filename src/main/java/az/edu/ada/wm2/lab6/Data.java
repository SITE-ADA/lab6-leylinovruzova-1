package az.edu.ada.wm2.lab6;

import az.edu.ada.wm2.lab6.model.Category;
import az.edu.ada.wm2.lab6.model.Product;
import az.edu.ada.wm2.lab6.repository.CategoryRepository;
import az.edu.ada.wm2.lab6.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Data {

    @Bean
    CommandLineRunner loadData(CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        return args -> {

            if (categoryRepository.count() == 0) {

                Category food = Category.builder()
                        .name("Food")
                        .products(new ArrayList<>())
                        .build();

                Category drinks = Category.builder()
                        .name("Drinks")
                        .products(new ArrayList<>())
                        .build();

                food = categoryRepository.save(food);
                drinks = categoryRepository.save(drinks);

                Product milk = Product.builder()
                        .productName("Milk")
                        .price(new BigDecimal("3.50"))
                        .expirationDate(LocalDate.now().plusDays(7))
                        .categories(new ArrayList<>(List.of(food)))
                        .build();

                Product cola = Product.builder()
                        .productName("Cola")
                        .price(new BigDecimal("2.00"))
                        .expirationDate(LocalDate.now().plusDays(30))
                        .categories(new ArrayList<>(List.of(drinks, food)))
                        .build();

                productRepository.save(milk);
                productRepository.save(cola);

                System.out.println();
            }
        };
    }
}