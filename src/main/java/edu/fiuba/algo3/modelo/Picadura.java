package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exceptions.*;

public class Picadura implements AtaqueVek {

    @Override
    public void atacar(Tablero tablero, Posicion origen, Posicion objetivo) {
        if (!origen.esAdyacenteA(objetivo)) {
            throw new AtaqueInvalidoException("Ataque invalido");
        }
        tablero.daniarEn(objetivo, 1);
    }
}