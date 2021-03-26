package tqsua.StocksPortfolio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StockPortfolioTest {

    // Mock to substitute the remote service
    @Mock
    private IStockMarket market;

    @InjectMocks
    StockPortfolio portfolio = new StockPortfolio();

    @BeforeEach
    void setUp() {
        // Create an instance of the SuT and use the mock to set the service instance
        market = Mockito.mock(IStockMarket.class);
        portfolio.setMarketService(market);
    }

    @Test
    void getTotalValueTest() {
        // Load the mock with the proper expectations
        Mockito.when( market.getPrice("Apple")).thenReturn(4.0);
        Mockito.when( market.getPrice("eBay")).thenReturn(1.5);

        // Execute the test
        portfolio.addStock(new Stock("Apple", 2));
        portfolio.addStock(new Stock("eBay", 4));

        // Verify the result and the use of the mock
        assertThat(portfolio.getTotalValue(), is(14.0));
        Mockito.verify(market, Mockito.times(2)).getPrice(Mockito.anyString());
    }

}
