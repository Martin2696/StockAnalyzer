package stockanalyzer.ctrl;

import yahooApi.YahooFinanceException;
import yahoofinance.Stock;

import java.io.IOException;

public class Controller {

	Stock stock = null;

	public void process(String ticker) throws YahooFinanceException {

		try {
			stock = yahoofinance.YahooFinance.get(ticker);

			var result = stock.getHistory().stream()
					.mapToDouble(q -> q.getClose().doubleValue())
					.max()
					.orElse(0.0);

			stock.getHistory().forEach(q -> System.out.println(q));

			System.out.println();
			System.out.println(ticker);
			System.out.println();

			System.out.println("* * * * Max Price * * * * *");
			System.out.println(result);

			var result2 = stock.getHistory().stream()
					.mapToDouble(q -> q.getClose().doubleValue())
					.average()
					.orElse(0.0);

			System.out.println();
			System.out.println("* * * * Average Price * * * * *");
			System.out.println(result2);

			var result3 = stock.getHistory().stream()
					.mapToDouble(q -> q.getClose().doubleValue())
					.count();

			System.out.println();
			System.out.println("* * * * Amount * * * * *");
			System.out.println(result3);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
