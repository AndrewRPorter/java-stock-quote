package com.aporter.main;

import org.junit.Test;

/*
 * JUnit test: Test to make sure all the information is stored
 * correctly and can be retreived when creating a new Stock.
 */
public class StockTest {
	
	@Test
	public void test() {
		
		//Testing with default tags
		Stock apple = StockFetcher.getStock( "AAPL" );
		System.out.println( "Price: " + apple.getPrice() );
		
		//Testing with user defined tags
	}
}
