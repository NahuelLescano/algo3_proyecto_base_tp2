package edu.fiuba.algo3.modelo;

public class CasillaDelTablero implements Casilla {
    private final Posicion posicion;
    private Terreno terreno;
    private EstadoCasilla estado = new CasillaVacia();

    public Terreno getTerreno(){
        return this.terreno;
    }

    public CasillaDelTablero(Terreno terreno, Posicion posicion) {
        this.terreno = terreno;
        this.posicion = posicion;
    }

    public Posicion getPosicion() { return posicion; }

    public void ocupar(Unidad unidad)          { estado.ocupar(unidad, this); }
    public void recibirEmpuje(Unidad empujada) { estado.recibirEmpuje(empujada, this); }
    public void empujarHacia(Casilla destino)  { estado.empujarHacia(destino); }
    public void recibirImpacto(int danio)      { estado.recibirImpacto(danio, this); }
    public void desocupar()                    { estado = new CasillaVacia(); }

    @Override
    public void alojar(Unidad unidad) {        
        estado = new CasillaOcupada(unidad);
        unidad.ubicarseEn(this);
    }

    void consultarTerreno(Unidad u)    { terreno.recibir(u, this); }
    void empujarSobreTerreno(Unidad u) { terreno.recibirEmpuje(u, this); }
    void impactarTerreno(int danio)    { terreno = terreno.recibirImpacto(danio); }
}
