/* ESTE ES PATRON DE Chain of Responsability o CADENA DE RESPONSABILIDAD */


public class App {
    public static void main(String[] args) {
        // Crear una cadena de dispensadores
        CadenaDispensadores cadena = new CadenaDispensadores();
        
        // Agregar los dispensadores en el orden correcto a la cadena
        cadena.agregarDispensador(new Dispensador(100000));
        cadena.agregarDispensador(new Dispensador(50000));
        cadena.agregarDispensador(new Dispensador(20000));
        cadena.agregarDispensador(new Dispensador(10000));
        cadena.agregarDispensador(new Dispensador(5000));
        
        // Monto a dispensar
        int montoADispensar = 75000;
        
        // Verificar si el monto es un múltiplo de 5000
        if (montoADispensar % 5000 != 0) {
            System.out.println("Error: El monto debe ser un múltiplo de 5000");
        } else {
            // Iniciar la dispensación a través de la cadena
            cadena.dispensar(montoADispensar);
        }
    }
}

// Clase que representa un dispensador
class Dispensador {
    private int denominacion;
    protected Dispensador siguienteDispensador;//modificar la visibilidad de los campos siguienteDispensador en la clase 
                                     //Dispensador a protected en lugar de private. De esta manera, las subclases 
                                    //(en este caso, la clase DispenserChain) podrán acceder a estos campos.

    public Dispensador(int denominacion) { 
        this.denominacion = denominacion;
    }

    public void setSiguienteDispensador(Dispensador siguienteDispensador) {
        this.siguienteDispensador = siguienteDispensador;
    }

    // Método para dispensar el monto dado
      public void dispensar(int monto) {
        int numBilletes = monto / denominacion;
         int resto = monto % denominacion; 

        // Si es posible, dispensar notas de esta denominación
         if (numBilletes > 0) {
             System.out.println("Dispensando " + numBilletes + " billetes de " + denominacion);
        }

        // Pasar el monto restante al siguiente dispensador en la cadena si existe
                if (resto != 0 && siguienteDispensador != null) {
                siguienteDispensador.dispensar(resto);
        }
    }
}

// Clase que maneja la cadena de dispensadores
class CadenaDispensadores {
    private Dispensador cabeza;

    // Agregar un dispensador a la cadena
    public void agregarDispensador(Dispensador dispensador) {
        if (cabeza == null) {
            cabeza = dispensador;
        } else {
             Dispensador actual = cabeza;
            while (actual.siguienteDispensador != null) {
                 actual = actual.siguienteDispensador;
            }
            actual.setSiguienteDispensador(dispensador);
        }
    }

    // Iniciar la dispensación a través de la cadena
    public void dispensar(int monto) {
        if (cabeza != null) {
              cabeza.dispensar(monto);
        } else {
            System.out.println("No hay dispensadores disponibles");
        }
    }
}