package test;

import org.junit.Test;

import com.aporter.main.StockFetcher;
import com.aporter.main.Stock;

/*
 * JUnit test: Test to make sure all the information is stored
 * correctly and can be retreived when creating a new Stock.
 */
public class StockTest {
	
	@Test
	public void test() {
		//Testing with default tags
		Stock apple = StockFetcher.getStock( "AAPL" );
		System.out.println( "Ticker: " + apple.getTicker() );
		System.out.println( "Price: " + apple.getPrice() );
		System.out.println( "Day Low: " + apple.getDayLow() );
		System.out.println( "Day High: " + apple.getDayHigh() );
		System.out.println( "Volume: " + apple.getVolume() );
		System.out.println( "Average Daily Volume: " + apple.getAvgDailyVolume() );
		System.out.println( "Dividend: " + apple.getDividend() );
		System.out.println( "Yield: " + apple.getYield() );
		System.out.println( "52 Week Low: " + apple.getYearLow() );
		System.out.println( "52 Week High: " + apple.getYearHigh() );
		System.out.println( "50 Day Moving Average: " + apple.getFiftyDayAvg() );
		System.out.println( "200 Day Moving Average: " + apple.getTwoHundredDayAvg() );
		System.out.println( "Market Cap: " + apple.getMarketCap() );
		System.out.println( "Price Book: " + apple.getPriceBook() );
		System.out.println( "Earnings Per Share: " + apple.getEPS() );
		System.out.println( "EBITDA: " + apple.getEBITDA() );
		System.out.println( "P/E: " + apple.getPE() );
		System.out.println( "Exchange: " + apple.getExchange() );
	}
}
