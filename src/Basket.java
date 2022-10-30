import java.io.*;
import java.util.Arrays;


public class Basket implements Serializable {
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

    static Basket loadFromBin(File textFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(textFile))) {
            return  (Basket)in.readObject();
        }

    }

    public boolean addToCart(int productNum, int amount) {
        if ((productNum > prices.length) || (amount < 0)) {
            return false;
        }
        basket[productNum - 1] += amount;
        try{
            saveBin();
        }catch(IOException exception){
            System.out.println("Ошибка записи");
            return false;
        }
        return true;
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

    public void saveBin(File textFile) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(textFile))) {
            out.writeObject(this);
        }
    }

    public void saveBin() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(in))) {
            out.writeObject(this);
        }
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
}
