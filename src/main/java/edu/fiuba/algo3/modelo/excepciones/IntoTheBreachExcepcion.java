package edu.fiuba.algo3.modelo.excepciones;

public class IntoTheBreachExcepcion extends RuntimeException {
    public IntoTheBreachExcepcion() {
        super("Error al realizar una accion");
    }

    public IntoTheBreachExcepcion(String mensaje) {
        super(mensaje);
    }
}
