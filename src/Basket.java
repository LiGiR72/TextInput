import java.io.*;
import java.util.Arrays;


public class Basket {
    private long[] prices;
    private String[] names;
    private long[] basket;

    private File in;

    public Basket(long[] prices, String[] names) {
        if (prices.length != names.length) {
            return;
        }
        this.prices = prices;
        this.names = names;
        basket = new long[prices.length];
    }

    public Basket(long[] prices, String[] names, long[] basket) {
        this.prices = prices;
        this.names = names;
        this.basket = basket;
    }

    static Basket loadFromTxt(File textFile) throws IOException {
        long[] prices;
        String[] names;
        long[] basket;
        try (BufferedReader in = new BufferedReader(new FileReader(textFile))) {
            prices = Arrays.stream(in.readLine().trim().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();
            names = in.readLine().trim().split(" ");
            basket = Arrays.stream(in.readLine().trim().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();
            return new Basket(prices, names, basket);
        }
    }

    public boolean addToCart(int productNum, int amount) {
        if ((productNum > prices.length) || (amount < 0)) {
            return false;
        }
        basket[productNum + 1] += amount;
        try{
            saveTxt();
        }catch(IOException exception){
            System.out.println("Оштбка записи");
            return false;
        }
        return true;
    }

    public long[] getBasket() {
        return basket;
    }

    public long[] getPrices() {
        return prices;
    }

    public String[] getNames() {
        return names;
    }

    public void setFile(File in) {
        this.in = in;
    }

    public void printCart() {
        long amount = 0;
        for (int i = 0; i < prices.length; i++) {
            if (basket[i] == 0) {
                continue;
            }
            System.out.println(names[i] + " - " + basket[i] + " .шт - " + (basket[i] * prices[i]) + " руб.");
            amount += basket[i] * prices[i];
        }
        System.out.println("Сумма покупки - " + amount);
    }

    public boolean saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile)) {

            for (long num : prices) {
                out.print(num + " ");
            }
            out.println();
            for (String num : names) {
                out.print(num + " ");
            }
            out.println();
            for (long num : basket) {
                out.print(num + " ");
            }

        }
        return true;
    }

    public boolean saveTxt() throws IOException {
        try (PrintWriter out = new PrintWriter(in)) {

            for (long num : prices) {
                out.print(num + " ");
            }
            out.println();
            for (String num : names) {
                out.print(num + " ");
            }
            out.println();
            for (long num : basket) {
                out.print(num + " ");
            }

        }
        return true;
    }
}
