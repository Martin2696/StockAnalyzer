package stockanalyzer.downloader;

import yahooApi.YahooFinanceException;

import java.util.List;

public class ParallelDownloader extends Downloader {

    @Override
    public int process(List<String> tickers) throws YahooFinanceException {
        int count = 0;
        for (String ticker : tickers) {
            String fileName = saveJson2File(ticker);
            if(fileName != null)
                count++;
        }
        return count;
    }
}