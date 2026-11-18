package edu.fiuba.algo3.modelo;

import java.util.Map;
import java.util.function.Supplier;
import java.util.HashMap;
public class ParserTablero {
    private final Map<Character, Supplier<Terreno>> creadores = new HashMap<>();

    public ParserTablero() {
        creadores.put('-', Suelo::new);
        creadores.put('M', Montania::new);
        creadores.put('A', Agua::new);
        creadores.put('X', Acido::new);
        creadores.put('E', () -> new Edificio(1));
    }

    public Terreno convertirCaracterATerreno(char caracter) {
        Supplier<Terreno> creador = creadores.get(caracter);
        if (creador == null) {
            throw new IllegalArgumentException("Caracter de terreno inválido: " + caracter);
        }
        return creador.get();
    }
}