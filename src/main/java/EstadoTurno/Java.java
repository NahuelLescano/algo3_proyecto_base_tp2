package EstadoTurno;

public class EstadoTurnoMech {
    private Posicion posicionInicial;
    private EstadoUnidad estadoInicial;
    private boolean seMovio;
    private boolean ataco;
    private boolean deshizoMovimiento;

    public EstadoTurnoMech() {
        this.reiniciar();
    }

    public void registrarMovimiento(Posicion origen, EstadoUnidad estadoActual) {
        if (this.seMovio) {
            throw new AccionInvalidaException("El Mech ya se movió en este turno.");
        }
        this.posicionInicial = origen;
        // Se guarda un snapshot del estado (efectos) antes de aplicar el movimiento
        this.estadoInicial = estadoActual.clonar();
        this.seMovio = true;
    }

    public void registrarAtaque() {
        if (this.ataco) {
            throw new AccionInvalidaException("El Mech ya atacó en este turno.");
        }
        this.ataco = true;
    }

    public boolean puedeDeshacer() {
        return this.seMovio && !this.ataco && !this.deshizoMovimiento;
    }

    public void deshacer(Mech mech, Tablero tablero) {
        if (!puedeDeshacer()) {
            throw new AccionInvalidaException("No es posible deshacer el movimiento en este momento.");
        }

        tablero.moverUnidad(mech.obtenerPosicion(), this.posicionInicial);
        mech.establecerPosicion(this.posicionInicial);

        mech.restaurarEstado(this.estadoInicial);

        this.seMovio = false;
        this.deshizoMovimiento = true;
    }

    public void reiniciar() {
        this.posicionInicial = null;
        this.estadoInicial = null;
        this.seMovio = false;
        this.ataco = false;
        this.deshizoMovimiento = false;
    }

    public boolean puedeMoverse() {
        return !this.seMovio;
    }

    public boolean puedeAtacar() {
        return !this.ataco;
    }
}
