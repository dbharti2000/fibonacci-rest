package academy.dd.fibonacci;

/**
 * Created by stevewalton on 20/07/2017.
 */
public final class Maths {

    private static final double PHI = 1.6180339;
    private static final double RECIPROCAL_PHI = -0.6180339;

    public static long fibonacci(int index) {
        return Math.round((Math.pow(PHI, index) - Math.pow(RECIPROCAL_PHI, index)) / Math.sqrt(5)) ;
    }
}
