package ru.geekbrains.context.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class MainApp {

    /**
     * Приложение - управление корзиной
     * Комманды:
     * /show_products - вывод продуктов в магазине
     * /show_cart - вывод продуктов в корзине
     * /add 1 - добавление продукта с id 1 в корзину
     * /remove 1 - удаление продукта с id 1 из корзины
     * /exit - выход из программы
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);
        // System.out.println(productRepository.findAll());
        CartService cartService = context.getBean("cartService", CartService.class);

        while (true) {
            System.out.println("------------");
            System.out.println("Введите комманду: ");
            try {
                Scanner scanner = new Scanner(System.in);
                String serverLine = scanner.nextLine();
                if (serverLine.equals("/exit")) {
                    break;
                } else if (serverLine.startsWith("/show_products")) {
                    productRepository.showProducts();
                } else if (serverLine.startsWith("/show_cart")) {
                    cartService.showCart();
                } else if (serverLine.startsWith("/add")) {
                    String[] strParts = serverLine.split("\\s+");
                    cartService.addProductToCart(Long.valueOf(strParts[1]));
                } else if (serverLine.startsWith("/remove")) {
                    String[] strParts = serverLine.split("\\s+");
                    cartService.removeProductFromCart(Long.valueOf(strParts[1]));
                } else
                    System.out.println("Команда " + serverLine + " не найдена.");

            } catch (Exception e) {
                System.out.println("Ошибка");
            }
        }
        context.close();
    }
}
