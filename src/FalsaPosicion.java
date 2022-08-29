
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

    public abstract double defineFormula(double var);

    public double useFormula(double var) {
        return defineFormula(var);
    }

    private void calculateFX(){
        for (int i = 0; i < intervals.length; i++) {
            fx[i] = Double.parseDouble(df.format(this.useFormula(intervals[i])));
        }
    }

    public void calculate(){
        this.calculateFX();
        for (int counter = 1; es > approximationError; counter++) {
          this.calculateFX();
            xi = Double.parseDouble(df.format(((intervals[0] * fx[1]) - (intervals[1] * fx[0])) / (fx[1] - fx[0])));
            if (counter >= 2) {
                es = ((xi - xiAux) / xi) * 100;
            }
            fxi = Double.parseDouble(df.format(useFormula(xi)));
            data.add(new double[]{counter, intervals[0], intervals[1], fx[0], fx[1], xi, fxi, es});
            for (int i = 0; i < intervals.length; i++) {
                if ((fxi > 0 & fx[i] > 0) || (fxi < 0 & fx[i] < 0)) {
                    intervals[i] = xi;
                }
            }
            xiAux = xi;
        }
    }

    public double getXi() {
        return xi;
    }

    public ArrayList<double[]> getData() {
        return data;
    }

    public double getEs() {
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