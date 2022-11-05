import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class Basket {
    private long[] prices;
    private String[] names;
    private long[] basket;
    private File in;

    public Basket(long[] prices, String[] names) throws IOException {
        if (prices.length != names.length) {
            return;
        }
        this.prices = prices;
        this.names = names;
        basket = new long[prices.length];
    }

    public Basket() throws IOException {

    }


    public static Basket loadJSON(File textFile) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(textFile, Basket.class);
    }

    public void addToCart(int productNum, int amount) {
        if ((productNum > prices.length) || (amount < 0)) {
            return;
        }
        basket[productNum - 1] += amount;
        try{
            saveJSON();
        }catch(IOException exception){
            System.out.println("Ошибка записи");
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
       ObjectMapper mapper = new ObjectMapper();
       mapper.writeValue(in, this);
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

    public void setPrices(long[] prices) {
        this.prices = prices;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public void setBasket(long[] basket) {
        this.basket = basket;
    }
}
