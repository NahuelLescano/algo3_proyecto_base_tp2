package edu.fiuba.algo3.modelo;

public class CasillaVacia implements EstadoCasilla {
    
    public void ocupar(Unidad unidad, CasillaDelTablero casilla)          { 
        casilla.consultarTerreno(unidad); 
    }
    public void recibirEmpuje(Unidad unidad, CasillaDelTablero casilla){ 
        casilla.empujarSobreTerreno(unidad); 
    }
    public void empujarHacia(Casilla destino){

    }
    public void recibirImpacto(int destino, CasillaDelTablero casilla){ 
        casilla.impactarTerreno(destino); 
    }

    @Override
    public Posicion avanzarDisparoEnLinea(CasillaDelTablero casilla, Posicion actual, Direccion direccion, Tablero tablero, int danio) {
        return casilla.getTerreno().avanzarDisparoEnLinea(actual, direccion, tablero, danio);
    }
}
