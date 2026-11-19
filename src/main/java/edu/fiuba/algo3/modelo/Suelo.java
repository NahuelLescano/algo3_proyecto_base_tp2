package edu.fiuba.algo3.modelo;

public class Suelo implements  Terreno {
    @Override
    public void recibir(Unidad unidad, Destino casilla){
        casilla.alojar(unidad);
    }

    public void recibirEmpuje(Unidad empujada, Destino destino){
        recibir(empujada, destino);
    }

    public Terreno recibirImpacto(int danio){
        return this;
    }
    @Override
    public Posicion avanzarDisparoEnLinea(Posicion actual, Direccion direccion, Tablero tablero, int danio) {
        return actual.desplazar(direccion);
    }
}
