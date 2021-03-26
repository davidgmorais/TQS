package tqsua.StocksPortfolio;

public class Stock {
    private String name;
    private int quantity;

    public Stock(String name, int qty) {
        this.name = name;
        this.quantity = qty;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
