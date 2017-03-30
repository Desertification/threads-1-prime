import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Prime extends Thread {
    private int largestNumber;

    public Prime(int largestNumber) {
        this.largestNumber = largestNumber;
    }

    @Override
    public void run() {
        // http://stackoverflow.com/a/31897836
        for (int i = 1; i <= largestNumber; ++i) {
            // checks if the number is a prime or not
            boolean isPrime = true;
            for (int check = 2; check < i; ++check) {
                if (i % check == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        String error = "a number is required as argument";
        if (args.length == 1) {
            try {
                int number = Integer.parseInt(args[0]);
                Prime prime = new Prime(number);
                prime.start();
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
                System.out.println("prime started at " + LocalDateTime.now().format(dateTimeFormat));
                try {
                    prime.join();
                    System.out.println("prime stopped at " + LocalDateTime.now().format(dateTimeFormat));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (NumberFormatException e) {
                System.out.printf(error);
            }
        } else {
            System.out.printf(error);
        }
    }
}
