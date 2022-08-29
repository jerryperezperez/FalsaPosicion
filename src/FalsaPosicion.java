
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
//TODO Mejorar posiblemente la estructura
//TODO Organizar variables
//TODO Mejorar el uso de DecimalFormat
public abstract class FalsaPosicion {
    private final double approximationError;
    private DecimalFormatSymbols simbolos;
    private DecimalFormat df;
    private final double[] intervals, fx; //TODO Verificar que fx no se esté creando como variable final
    private ArrayList<double[]> data;
    private double xi, xiAux, fxi, es;

    {
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        df = new DecimalFormat("####.000000000", simbolos);
        fx = new double[2];
        data = new ArrayList<>();
        xiAux = 0;
        es = 100;
    }
    public FalsaPosicion(double approximationError, double intervalA, double intervalB){
        this.approximationError = approximationError;
        intervals = new double[] {intervalA, intervalB};
    }

    public abstract double defineFormula(double var);//Método abstracto para definir la función por utilizar

    public double useFormula(double var) { //Método usado para poder invocar la función definida en useFormula()
        return defineFormula(var);
    }

    private void calculateFX(){ //Realiza el cálculo de la fórmula para f(a) y f(b)
        for (int i = 0; i < intervals.length; i++) {
            fx[i] = Double.parseDouble(df.format(this.useFormula(intervals[i])));
        }
    }

    public void calculate(){ //Método que se emplea para resolver e iterar el proceso del cálculo
        this.calculateFX();
        for (int counter = 1; es > approximationError; counter++) {
          this.calculateFX();
            xi = Double.parseDouble(df.format(((intervals[0] * fx[1]) - (intervals[1] * fx[0])) / (fx[1] - fx[0])));
            if (counter >= 2) { //Condición que se puede realizar solamente cuando está en la segunda iteración
/*                La razón para counter-2 es porque counter comienza desde 1, por lo que se debe acceder ala posición de
                memoria anterior, implicando que deba ser 2 para acceder a la verdadera posición anterior de memoria*/
                es = ((xi - data.get(counter-2)[5]) / xi) * 100; //Define es, ya que en la primera iteración esta operación no se puede realizar
            }
            fxi = Double.parseDouble(df.format(useFormula(xi)));
            data.add(new double[]{counter, intervals[0], intervals[1], fx[0], fx[1], xi, fxi, es}); //Se agrega la fila en la matriz data
            for (int i = 0; i < intervals.length; i++) { //Se asignan los nuevos valores a intervals[] en caso de cumpir la condción
                if ((fxi > 0 & fx[i] > 0) || (fxi < 0 & fx[i] < 0)) {
                    intervals[i] = xi;
                }
            }
        }
    }

    public double getXi() { //Retorna el resultado de la última iteración calculada
        return xi;
    }

    public ArrayList<double[]> getData() { //Retorna una matriz con todos los datos de operación
        return data;
    }

    public double getEs() { //Retorna el último error de aproximación calculado
        return es;
    }

    @Override
    public String toString() { //TODO Emplear printf para mejorar el formato de impresión de la tabla
        StringBuilder sb = new StringBuilder();
        System.out.println("TAMAÑO DE LA TABLA: " + data.size());
        for (double[] row : data) {
            for (double value : row) {
                sb.append(value + "   ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}