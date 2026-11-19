package edu.fiuba.algo3.modelo;


public class CasillaInexistente implements Casilla {
    public static final CasillaInexistente INSTANCIA = new CasillaInexistente();
    private CasillaInexistente() {}

    public Posicion getPosicion(){ 
        return new Posicion(-1, -1); 
    }
    public void ocupar(Unidad unidad){ 
        unidad.noPudeMoverme(); 
    }
    public void recibirEmpuje(Unidad empujada){ 

    }
    public void empujarHacia(Casilla destino){ 

    }
    public void recibirImpacto(int danio){ 

    }
    public void desocupar(){ 

    }
    public void alojar(Unidad unidad){ 

    }
    public Terreno getTerreno(){
        return null;
    }
}