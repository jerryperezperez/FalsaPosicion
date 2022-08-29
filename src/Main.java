import java.util.Collection;
import java.util.Collections;
import java.util.Vector;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        FalsaPosicion falsa = new FalsaPosicion(.05, 8, 11) {
            @Override
            public double defineFormula(double var) {
                return (var * Math.log10(var)) - 10;
            }
        };
        falsa.calculate();
        System.out.println(falsa.toString());
    }

}
