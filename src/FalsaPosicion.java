
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JOptionPane;
import java.util.*;
public abstract class FalsaPosicion {
//más cambios que deben servir
    //TODO Agregar variables de xi, xiAux, fxi y es como parámetros del constructor
    public FalsaPosicion(){
        System.out.println("HOLA");
    }

    public abstract void metodo();

    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("####.000000000", simbolos);

        double xi;
        double xiAux = 0;
        double fxi;
        int contador = 0;
        int iteracion;
        double es = 100;

        double a = Double.parseDouble(JOptionPane.showInputDialog(null, "Escribe tu intervalo a"));
        double b = Double.parseDouble(JOptionPane.showInputDialog(null, "Escribe tu intervalo b"));

        double fa = (double) ((a * Math.log10(a)) - 10);
        double fb = (double) ((b * Math.log10(b)) - 10);
        fa = Double.parseDouble(df.format(fa));
        fb = Double.parseDouble(df.format(fb));

        while (es > .05) {
            contador = contador + 1;

            fa = (double) ((a * Math.log10(a)) - 10);
            fb = (double) ((b * Math.log10(b)) - 10);
            fa = Double.parseDouble(df.format(fa));
            fb = Double.parseDouble(df.format(fb));

            xi = ((a * fb) - (b * fa)) / (fb - fa);
            xi = Double.parseDouble(df.format(xi));
            if (contador >= 2) {
                es = ((xi - xiAux) / xi) * 100;
            }

            fxi = (double) ((xi * Math.log10(xi)) - 10);
            fxi = Double.parseDouble(df.format(fxi));
            System.out.print(contador + "   ");
            System.out.print(a + "   ");
            System.out.print(b + "   ");
            System.out.print(fa + "   ");
            System.out.print(fb + "   ");
            System.out.print(xi + "   ");
            System.out.print(fxi + "   ");
            System.out.print(es + "   ");
            System.out.println();

            if ((fxi > 0 & fa > 0) ||(fxi < 0 & fa < 0)) {
                a = xi;
            }
            if ((fxi > 0 & fb > 0) || (fxi < 0 & fb < 0)) {
                b = xi;
            }
            xiAux = xi;
        }
    }
}