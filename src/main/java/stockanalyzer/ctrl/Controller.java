package stockanalyzer.ctrl;

import yahooApi.YahooFinanceException;
import yahoofinance.Stock;

import java.io.IOException;


public class Controller {


	public void process(String ticker) throws YahooFinanceException {

		Stock stock = null;
		try {
			stock = yahoofinance.YahooFinance.get("AAPL");
			stock.getHistory().forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
