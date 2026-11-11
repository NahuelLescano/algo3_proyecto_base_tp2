package edu.fiuba.algo3.modelo.excepciones;

public abstract class JuegoException extends RuntimeException {
    public JuegoException(String mensaje) {
        super(mensaje);
    }
}
