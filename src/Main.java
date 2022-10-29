import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        File in = new File("src/basket.bin");
        String[] groceries = {"Молоко", "Хлеб", "Сыр", "Гречка", "Рис"};
        Basket basket;
        long[] prices = {70, 30, 100, 35, 40};
        if (!in.exists()) {
            in.createNewFile();
            basket = new Basket(prices, groceries);
            basket.setFile(in);
        } else {
            basket = Basket.loadFromTxt(in);
            basket.setFile(in);
        }
        basket.printCart();
        basket.addToCart(2, 10);
        basket.addToCart(5, 5);
        basket.printCart();

    }
}


