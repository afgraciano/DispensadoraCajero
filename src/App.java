/* ESTE ES PATRON DE Chain of Responsability o CADENA DE RESPONSABILIDAD */


public class App {
    public static void main(String[] args) {
        // Crear una cadena de dispensadores
        CadenaDispensadores cadena = new CadenaDispensadores();//DispenserChain chain = new DispenserChain();
        
        // Agregar los dispensadores en el orden correcto a la cadena
        cadena.agregarDispensador(new Dispensador(100000));//chain.addDispenser(new Dispenser(100000));
        cadena.agregarDispensador(new Dispensador(50000));//chain.addDispenser(new Dispenser(50000));
        cadena.agregarDispensador(new Dispensador(20000));//chain.addDispenser(new Dispenser(20000));
        cadena.agregarDispensador(new Dispensador(10000));//chain.addDispenser(new Dispenser(10000));
        cadena.agregarDispensador(new Dispensador(5000));//chain.addDispenser(new Dispenser(5000));
        
        // Monto a dispensar
        int montoADispensar = 75000;//int amountToDispense = 75000;
        
        // Verificar si el monto es un múltiplo de 5000
        if (montoADispensar % 5000 != 0) {//if (amountToDispense % 5000 != 0) {
            System.out.println("Error: El monto debe ser un múltiplo de 5000");//System.out.println("Error: Amount must be a multiple of 5000");
        } else {
            // Iniciar la dispensación a través de la cadena
            cadena.dispensar(montoADispensar);//chain.dispense(amountToDispense);
        }
    }
}

// Clase que representa un dispensador
class Dispensador {//class Dispenser {
    private int denominacion;//private int denomination;
    protected Dispensador siguienteDispensador;//protected Dispenser nextDispenser;//modificar la visibilidad de los campos nextDispenser en la clase 
                                     //Dispenser a protected en lugar de private. De esta manera, las subclases 
                                    //(en este caso, la clase DispenserChain) podrán acceder a estos campos.

    public Dispensador(int denominacion) { //public Dispenser(int denomination) {
        this.denominacion = denominacion;//this.denomination = denomination;
    }

    public void setSiguienteDispensador(Dispensador siguienteDispensador) {//public void setNextDispenser(Dispenser nextDispenser) {
        this.siguienteDispensador = siguienteDispensador;//this.nextDispenser = nextDispenser;
    }

    // Método para dispensar el monto dado
      public void dispensar(int monto) {//public void dispense(int amount) {
        int numBilletes = monto / denominacion;//int numNotes = amount / denomination;
         int resto = monto % denominacion; //int remainder = amount % denomination;

        // Si es posible, dispensar notas de esta denominación
         if (numBilletes > 0) {//if (numNotes > 0) {
             System.out.println("Dispensando " + numBilletes + " billetes de " + denominacion);//System.out.println("Dispensing " + numNotes + " notes of " + denomination);
        }

        // Pasar el monto restante al siguiente dispensador en la cadena si existe
                if (resto != 0 && siguienteDispensador != null) {//if (remainder != 0 && nextDispenser != null) {
                siguienteDispensador.dispensar(resto);//nextDispenser.dispense(remainder);
        }
    }
}

// Clase que maneja la cadena de dispensadores
class CadenaDispensadores {//class DispenserChain {
    private Dispensador cabeza;//private Dispenser head;

    // Agregar un dispensador a la cadena
    public void agregarDispensador(Dispensador dispensador) {//public void addDispenser(Dispenser dispenser) {
        if (cabeza == null) {//if (head == null) {
            cabeza = dispensador;//head = dispenser;
        } else {
             Dispensador actual = cabeza;//Dispenser current = head;
            while (actual.siguienteDispensador != null) {//while (current.nextDispenser != null) {
                 actual = actual.siguienteDispensador;//current = current.nextDispenser;
            }
            actual.setSiguienteDispensador(dispensador);//current.setNextDispenser(dispenser);
        }
    }

    // Iniciar la dispensación a través de la cadena
    public void dispensar(int monto) {//public void dispense(int amount) {
        if (cabeza != null) {//if (head != null) {
              cabeza.dispensar(monto);//head.dispense(amount);
        } else {
            System.out.println("No hay dispensadores disponibles");//System.out.println("No dispensers available");
        }
    }
}
