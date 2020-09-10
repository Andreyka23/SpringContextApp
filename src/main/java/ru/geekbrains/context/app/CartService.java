package ru.geekbrains.context.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartService {
    private Cart currentCart;
    private ProductRepository productRepository;

    public CartService(Cart currentCart, ProductRepository productRepository) {
        this.currentCart = currentCart;
        this.productRepository = productRepository;
    }

    public void showCart() {
        System.out.println("Сейчас в корзине: ");
        if ( currentCart.getProducts().size() > 0 ) {
            for ( Product product :  currentCart.getProducts()) {
                System.out.println(product.getTitle());
            }
        } else {
            System.out.println("Корзина пуста.");
        }
    }

    public void addProductToCart(Long productId) {
        Product addProduct = productRepository.findProductById(productId);
        currentCart.addProduct(addProduct);
        System.out.println(addProduct.getTitle() + " добавлен в корзину.");
    }

    public void removeProductFromCart(Long productId) {
        List<Product> newProducts = new ArrayList<>();
        for ( Product product :  currentCart.getProducts()) {
            if ( ! product.getId().equals(productId) ) {
                newProducts.add(product);
            }
        }
        currentCart.setProducts(newProducts);
        System.out.println("Продукт удален из корзины.");
    }

}
