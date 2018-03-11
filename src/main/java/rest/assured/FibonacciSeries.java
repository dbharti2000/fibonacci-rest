package rest.assured;

import java.util.ArrayList;
import java.util.List;


public class FibonacciSeries {

    public static long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }


    public static List<Long> getFibonacciFormAndTo(int lower, int upper) {
        List<Long> fibList = new ArrayList<>();

        for (int index = lower; index < upper; index++) {
            if (index <= 1) {
                fibList.add(((long) index));
            } else {
                fibList.add(fibonacci(index - 1) + fibonacci(index - 2));
            }
        }

        return fibList;
    }


}

