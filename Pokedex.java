import java.io.*;
import java.util.*;

public class Pokedex {
    private PokemonBaseStats[] allPokemon;

    private static final int TOTAL_POKEMON = 151;

    public Pokedex(File pokemonInfo) throws FileNotFoundException{
        Scanner fileScan = new Scanner(pokemonInfo);
        fileScan.nextLine();
        fileScan.nextLine();
        fileScan.nextLine();
        allPokemon = new PokemonBaseStats[TOTAL_POKEMON];
        int indexCounter = 0;
        while(fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            if(line.length() > 0) {
                allPokemon[indexCounter] = fileToPokemon(line);
                indexCounter++;
            }
        }
    }

    private PokemonBaseStats fileToPokemon(String line) {
        Scanner lineScan = new Scanner(line);
        int indexNum = lineScan.nextInt();
        String name = "";
        while (!lineScan.hasNextInt()) {
            name += lineScan.next();
        }
        int health = lineScan.nextInt();
        int attack = lineScan.nextInt();
        int defense = lineScan.nextInt();
        int speed = lineScan.nextInt();
        int special = lineScan.nextInt();
        return new PokemonBaseStats(indexNum, name, health, attack, defense, speed, special);
    }

    public Pokemon getPokemon(int index) {
        return new Pokemon (allPokemon[index - 1]); // need to subtract because arrays are 0 based
    }


    // is a giant array bad?
    // need to test if pulling values out of file is correct

}

