import java.io.File;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Settings settings = new Settings();
        File in = new File(settings.getLoadFileName());
        File out = new File(settings.getSaveFileName());
        ClientLog logger = null;
        Basket basket = null;
        logger = new ClientLog(new File(settings.getLogFileName()));
        if (!in.exists() || !settings.getLoadEnabled()) {
            String[] groceries = {"Молоко", "Хлеб", "Сыр", "Гречка", "Рис"};
            long[] prices = {70, 30, 100, 35, 40};
            basket = new Basket(prices, groceries);
            basket.setFile(out);
        } else {
            if (settings.getLoadFileFormat().equals("json")) {
                basket = Basket.loadJSON(in);
                basket.setFile(out);

            } else if (settings.getLoadFileFormat().equals("txt")) {
                basket = Basket.loadFromTxt(in);
                basket.setFile(out);

            }
        }

        while (true) {
            System.out.println("Введите команду:");
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "add":
                    System.out.println("Ведите номер товара");
                    int id = Integer.parseInt(scanner.nextLine());
                    if (id > basket.getNames().length || basket.getNames().length <= 0) {
                        System.out.println("Неверный номер товара");
                        break;
                    }
                    System.out.println("Товар - " + basket.getNames()[id - 1] + ". Введите количество ");
                    int num = Integer.parseInt(scanner.nextLine().trim());
                    basket.addToCart(id, num, settings);
                    if (settings.getLogEnabled()) {
                        logger.log(id, num);
                    }
                    break;
                case "print":
                    basket.printCart();
                    break;
                case "end":
                    logger.exportAsCSV(new File(settings.getLogFileName()));
                    return;
                default:
                    System.out.println("Неверная команда");
                    break;
            }
        }
    }
}


