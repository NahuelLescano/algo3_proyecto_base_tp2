package edu.fiuba.algo3.modelo;

public class Acido implements Terreno {
    public void recibir(Unidad unidad, Destino casilla){
        casilla.alojar(unidad);
        unidad.sufrir(new EfectoAcido());
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
