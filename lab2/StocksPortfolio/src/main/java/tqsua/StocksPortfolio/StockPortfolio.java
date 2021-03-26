package tqsua.StocksPortfolio;

import java.util.ArrayList;

public class StockPortfolio {
    public String name;
    private IStockMarket marketService;
    private final ArrayList<Stock> stocks = new ArrayList<>();

    public IStockMarket getMarketService() {
        return this.marketService;
    }

    public void setMarketService(IStockMarket marketService){
        this.marketService = marketService;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalValue() {
        double total = 0;
        for (Stock s: this.stocks) {
            total += s.getQuantity() * this.marketService.getPrice(s.getName());
        }
        return total;
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }
}
