package ru.geekbrains.context.app;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Чай", 95),
                new Product(2L, "Хлеб", 30),
                new Product(3L, "Масло", 130),
                new Product(4L, "Сахар", 150),
                new Product(5L, "Пирожное", 200)
        ));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public void showProducts() {
        System.out.println("Доступные продукты");
        for (Product i : products) {
            System.out.println( i.getId() + " - " + i.getTitle() + " - " + i.getPrice() );
        }
    }

    public Product findProductById(Long id) {
        for (Product i : products) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        throw new RuntimeException("Product not found");
    }

    public void save(Product product) {
        products.add(product);
    }
}
