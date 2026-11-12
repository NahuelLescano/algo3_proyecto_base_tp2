package edu.fiuba.algo3.modelo.excepciones;

public class PosicionFueraDeRangoException extends MovimientoInvalidoException {
    public PosicionFueraDeRangoException(String mensaje) {
        super(mensaje);
    }
}
