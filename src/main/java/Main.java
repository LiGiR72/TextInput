import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        File in = new File("IO_files/basket.json");
        Basket basket;
        ClientLog logger = new ClientLog(new File("IO_files/log.csv"));
        while(true){
            if (!in.exists()) {
                in.createNewFile();
                String[] groceries = {"Молоко", "Хлеб", "Сыр", "Гречка", "Рис"};
                long[] prices = {70, 30, 100, 35, 40};
                basket = new Basket(prices, groceries);
                basket.setFile(in);
            } else {
                basket = Basket.loadJSON(in);
                basket.setFile(in);
            }
            System.out.println("Введите команду:");
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "add":
                    System.out.println("Ведите номер товара");
                    int id = Integer.parseInt(scanner.nextLine());
                    if(id > basket.getNames().length || basket.getNames().length <= 0){
                        System.out.println("Неверный номер товара");
                        break;
                    }
                    System.out.println("Товар - " + basket.getNames()[id - 1] + "Введите количество");
                    int num =  Integer.parseInt(scanner.nextLine().trim());
                    basket.addToCart(id,num);
                    logger.log(id, num);
                    break;
                case "print":
                    basket.printCart();
                    break;
                case "end":
                    logger.exportAsCSV();
                    return;
                default:
                    System.out.println("Неверная команда");
                    break;
            }
        }
    }
}


