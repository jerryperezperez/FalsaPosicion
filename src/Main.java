import java.util.Vector;

public class Main {



    public static void main(String[] args) {

//        FalsaPosicion falsa = new FalsaPosicion(.05, 8, 11) {
//            @Override
//            public double defineFormula(double var) {
//                return (var * Math.log10(var)) - 10;
//            }
//        };

//        FalsaPosicion falsa = new FalsaPosicion(.05, 6,7) {
//            @Override
//            public double defineFormula(double var) {
//                return (-.5*(var*var))+(2.5*var)+4.5;
//            }
//        };
//        falsa.calculate();
//        System.out.println(falsa.toString());

        Biseccion biseccion = new Biseccion(.05, 1, 1.6) {
            @Override
            public double defineFormula(double var) {
                return (4 * (var * var)) - (5 * var);
            }
        };
//        biseccion.calculate();
//        System.out.println(biseccion.toString());





    }

}
