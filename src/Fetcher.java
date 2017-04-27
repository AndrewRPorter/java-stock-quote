import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;


/**
 * Downloads a csv file from yahoo-finance and then
 * creates a Stock object holding its information.
 * 
 * URL's that follow the following format:
 * http://download.finance.yahoo.com/d/quotes.csv?s=[ticker]&f=[tags]4&e=.csv
 * can be used to extract certain information into a CSV file. The tags 
 * and what they mean can be found in tags.txt.
 * 
 * @author Andrew
 *
 */
public class Fetcher {
	
	private Stock   stock       = null;
	private Scanner scan        = null;
	private Vector<String> data = null;

	//http://www.canbike.org/information-technology/yahoo-finance-url-download-to-a-csv-file.html
	
	public Fetcher( String ticker )
	{
		try
		{
			ticker = ticker.toUpperCase();
			URL url = new URL( "http://download.finance.yahoo.com/d/quotes.csv?s=" + 
					ticker + "&f=l1ghdyjkm3m4j1b4ej4&e=.csv" );
			
			stock = new Stock( ticker );
			data  = new Vector<String>();
			scan  = new Scanner( url.openStream() );
			parseFile();
			initStock();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public Fetcher( String[] tickers ) {
		for( String ticker: tickers )
		{
			new Fetcher( ticker );
		}
	}
	
	public void parseFile() {
		for( String s: scan.next().split(",") )
		{
			//Removes any quotes that may be in the CSV download
			s = s.replace( "\"", "" );
			data.add( s );
		}
	}
	
	public void initStock() {
		stock.setPrice           ( data.get( 0 ) );
		stock.setDayLow          ( data.get( 1 ) );
		stock.setDayHigh         ( data.get( 2 ) );
		stock.setDividend        ( data.get( 3 ) );
		stock.setYield           ( data.get( 4 ) );
		stock.setYearHigh        ( data.get( 5 ) );
		stock.setYearLow         ( data.get( 6 ) );
		stock.setFiftyDayAvg     ( data.get( 7 ) );
		stock.setTwoHundredDayAvg( data.get( 8 ) );
		stock.setMarketCap       ( data.get( 9 ) );
		stock.setPriceBook       ( data.get( 10 ) );
		stock.setEPS             ( data.get( 11 ) );
		stock.setEBITDA          ( data.get( 12 ) );
	}
	
	public static void main( String[] args ) {
		Fetcher fetch = new Fetcher( "aapl" );
		
		/*
		String[] tickers = { "aapl", "gevo", "f", "bac" };
		Fetcher test = new Fetcher( tickers );
		*/
	}
}
