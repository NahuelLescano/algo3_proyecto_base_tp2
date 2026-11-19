package edu.fiuba.algo3.modelo.tablero;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import edu.fiuba.algo3.modelo.terreno.Acido;
import edu.fiuba.algo3.modelo.terreno.Agua;
import edu.fiuba.algo3.modelo.terreno.Edificio;
import edu.fiuba.algo3.modelo.terreno.Montania;
import edu.fiuba.algo3.modelo.terreno.Suelo;
import edu.fiuba.algo3.modelo.terreno.Terreno;

public class ParserTablero {
    private final Map<Character, Supplier<Terreno>> creadores = new HashMap<>();

    public ParserTablero() {
        this.creadores.put('-', Suelo::new);
        this.creadores.put('M', Montania::new);
        this.creadores.put('A', Agua::new);
        this.creadores.put('X', Acido::new);
        this.creadores.put('E', () -> new Edificio(1));
    }

    public Terreno convertirCaracterATerreno(char caracter) {
        Supplier<Terreno> creador = this.creadores.get(caracter);
        if (creador == null) {
            throw new IllegalArgumentException("Caracter de terreno inválido: " + caracter);
        }
        return creador.get();
    }
}
