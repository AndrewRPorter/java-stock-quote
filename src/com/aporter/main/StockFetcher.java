package com.aporter.main;

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
 * can be used to extract certain information from a CSV file. The tags 
 * and what they mean can be found in tags.txt.
 * 
 * This project was inspired by the following URL:
 * http://www.canbike.org/information-technology/yahoo-finance-url-download-to-a-csv-file.html
 * 
 * @author Andrew
 *
 */
public class StockFetcher {
	
	private static Stock stock  = null;
	private Scanner scan        = null;
	private Vector<String> data = null;
	private static final String DEFAULT_TAGS = "l1ghdyjkm3m4j1b4ej4rva2x";

	/*
	 * Trys to open and download from yahoo-finance. 
	 * A Stock is then created and informaton is stored.
	 */
	private StockFetcher( String ticker, String tags )
	{
		try
		{
			URL url = new URL( "http://download.finance.yahoo.com/d/quotes.csv?s=" + 
					ticker + "&f=" + tags + "&e=.csv" );
			
			stock = new Stock( ticker.toUpperCase() );
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
	
	/*
	 * Returns a Stock with the default tags:
	 * 
	 * ticker, price, dayLow, dayHigh, dividend, yield, yearLow, 
	 * yearHigh, fifty_day_avg, two_hundred_day_avg, marketCap, 
	 * priceBook, EPS, EBITDA, pe, volume, avgDailyVolume, exchange;
	 * 
	 * @param ticker String
	 */
	public static Stock getStock( String ticker ) {
		new StockFetcher( ticker, DEFAULT_TAGS );
		return stock;
	}
	
	/*
	 * Parses the CSV file, adding the data to a vector of strings.
	 */
	private void parseFile() {
		for( String s: scan.next().split(",") )
		{
			//Removes any quotes that may be in the CSV file
			s = s.replace( "\"", "" );
			data.add( s );
		}
	}
	
	/*
	 * Sets ticker information for each index in the Vector of Strings.
	 * The values added in the Vector are in the same order that the
	 * tags are in.
	 */
	private void initStock() {
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
		stock.setPE              ( data.get( 13 ) );
		stock.setVolume          ( data.get( 14 ) );
		stock.setAvgDailyVolume  ( data.get( 15 ) );
		stock.setExchange        ( data.get( 16 ) );
	}
}
