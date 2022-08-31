import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public abstract class MetodoNumerico {

    protected final double approximationError;
    protected DecimalFormatSymbols simbolos;
    protected DecimalFormat df;
    protected double[] intervals, fx; //TODO Verificar que fx no se esté creando como variable final
    protected ArrayList<double[]> data;
    protected double xi, xiAux, fxi, es;

    {
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        df = new DecimalFormat("####.000000000", simbolos);
        fx = new double[2];
        data = new ArrayList<>();
        xiAux = 0;
        es = 100;
    }

    public MetodoNumerico(double approximationError, double intervalA, double intervalB){
        this.approximationError = approximationError;
        intervals = new double[] {intervalA, intervalB};
    }
    public abstract double defineFormula(double var);//Método abstracto para definir la función por utilizar
    public abstract void calculate();
    protected void calculateFX(){ //Realiza el cálculo de la fórmula para f(a) y f(b)
        for (int i = 0; i < intervals.length; i++) {
            fx[i] = Double.parseDouble(df.format(this.useFormula(intervals[i])));
        }
    }
    public double useFormula(double var) { //Método usado para poder invocar la función definida en useFormula()
        return defineFormula(var);
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
