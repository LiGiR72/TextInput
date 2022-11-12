import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;

public class Basket {
    private long[] prices;
    private String[] names;
    private long[] basket;
    private File out;

    public Basket(long[] prices, String[] names) throws IOException {
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

    public Basket() throws IOException {

    }
    public static Basket loadFromTxt(File textFile) throws IOException {
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

    public static Basket loadJSON(File textFile) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(textFile, Basket.class);
    }

    public void addToCart(int productNum, int amount, Settings settings) {
        if ((productNum > prices.length) || (amount < 0)) {
            return;
        }
        basket[productNum - 1] += amount;
        if(settings.getSaveEnabled()) {
            try {
                if (settings.getSaveFileFormat().equals("json")) {
                    saveJSON();
                } else if (settings.getSaveFileFormat().equals("txt")) {
                    saveTxt();
                }

            } catch (IOException exception) {
                System.out.println("Ошибка записи");
            }
        }
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

    public void saveJSON() throws IOException{
        if(!out.exists()){
            out.createNewFile();
        }
       ObjectMapper mapper = new ObjectMapper();
       mapper.writeValue(out, this);
    }
    public boolean saveTxt() throws IOException {
        if(!out.exists()){
            out.createNewFile();
        }
        try (PrintWriter out = new PrintWriter(this.out)) {

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

    public long[] getPrices() {
        return prices;
    }

    public void setPrices(long[] prices) {
        this.prices = prices;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public long[] getBasket() {
        return basket;
    }

    public void setBasket(long[] basket) {
        this.basket = basket;
    }

    public void setFile(File in) {
        this.out = in;
    }

}
