package stockanalyzer.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader {

    @Override
    public int process(List<String> tickers) {
        long timer = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(4);

        List<Future<String>> myList = new ArrayList<>();

        for (String ticker : tickers) {

            Callable<String> request = () -> saveJson2File(ticker);

            Future<String> fileName = executor.submit(request);

            myList.add(fileName);

        }

        for (Future<String> future : myList ){
            try {
                String fileName = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Parallel Time: " + (System.currentTimeMillis() - timer));
        return myList.size();

    }
}