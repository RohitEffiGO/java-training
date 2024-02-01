import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SquareCalculator implements Runnable {
    private int number;

    public SquareCalculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        long result = number * number;
        System.out.println("Square of " + number + " is: " + result);
    }
}

public class ThreadsExecutorService {
    public static void main(String[] args) {
        // Create an ExecutorService with a fixed pool of threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit tasks to the ExecutorService
        for (int i = 1; i <= 5; i++) {
            executorService.submit(new SquareCalculator(i));
        }

        // Shutdown the ExecutorService
        executorService.shutdown();

        try {
            // Wait for all tasks to complete or a timeout of 5 seconds
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed.");
    }
}
