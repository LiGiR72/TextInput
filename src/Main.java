import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] groceries = {"Молоко", "Хлеб", "Сыр", "Гречка", "Рис"};
        int[] prices = {70, 30, 100, 35, 40};
        int[] shoppingList = new int[5];
        Scanner scanner = new Scanner(System.in);
        showPrices(groceries, prices);
        while (true) {
            System.out.println("Введите:\nНомер товара и его количество, чтобы добваить в корзину");
            System.out.println("Введите end, чтобы выйти из программы");
            System.out.println("Список, чтобы вывести цены еще раз");
            System.out.println("Сумма, чтобы высчитать итоговую сумму покупок");
            System.out.print(">> ");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }
            if (input.equals("Список")) {
                showPrices(groceries, prices);
                continue;
            }
            if (input.equals("Сумма")) {
                int sum = 0;
                for (int i = 0; i < shoppingList.length; i++) {
                    if (shoppingList[i] == 0)
                        continue;
                    System.out.println(groceries[i] + " " + prices[i] + " р/шт " + (shoppingList[i] / prices[i]) + " штук  = " + shoppingList[i] + " рублей");
                    sum += shoppingList[i];
                }
                System.out.println("Итого = " + sum + " рублей");
                continue;
            }
            String[] product = input.split(" ");
            int productPos = Integer.parseInt(product[0]) - 1;
            int productCount = Integer.parseInt(product[1]);
            shoppingList[productPos] += prices[productPos] * productCount;
        }
    }

    public static void showPrices(String[] grc, int[] prices) {
        for (int i = 0; i < grc.length; i++) {
            System.out.println((i + 1) + " " + grc[i] + " " + prices[i] + " р/шт");
        }
    }
}
