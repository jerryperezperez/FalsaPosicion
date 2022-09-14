public abstract class Biseccion extends MetodoNumerico {
    public Biseccion(double approximationError, double intervalA, double intervalB) {
        super(approximationError, intervalA, intervalB);
    }


    @Override
    public void calculate() {
        this.calculateFX();
        for (int counter = 1; es > approximationError; counter++) {
            xi = Double.parseDouble(df.format((intervals[0] + intervals[1])/2));
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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
