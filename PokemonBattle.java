import java.io.*;
import java.util.*;
public class PokemonBattle {

    private static final int TOTAL_POKEMON = 151;
    private static final int MAX_POKEMON_ON_TEAM = 2;

    public static void main(String[]args) throws FileNotFoundException{
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        Pokedex allPokemon = new Pokedex(new File("kanto.txt"));
        intro();
        gameMain(input, random, allPokemon);
    }

    private static void intro() {
        System.out.println("Welcome to a Pokemon Battle");
        System.out.println("You can choose any Pokemon from the first generation");
        System.out.println("and battle against a computer!");
        System.out.println();
    }

    private static void chooseTeam(Scanner input, Pokedex allPokemon, List<Pokemon> team) {
        int remainingSpots = MAX_POKEMON_ON_TEAM;
        while(remainingSpots > 0) {
            System.out.print("Please type in the official Pokedex index number of the Pokemon you want for your team. ");
            int pokemonChoice = input.nextInt();
            if(pokemonChoice <= TOTAL_POKEMON && pokemonChoice > 0) {
                team.add(allPokemon.getPokemon(pokemonChoice));
                remainingSpots--;
            }
        }
    }

    private static void computerTeam(Random random, Pokedex allPokemon, List<Pokemon> team) {
        for(int i = 0; i < MAX_POKEMON_ON_TEAM; i++) {
            team.add(allPokemon.getPokemon(random.nextInt(TOTAL_POKEMON + 1)));
        }
    }

    public static void gameMain(Scanner input, Random random, Pokedex allPokemon) {
        boolean quitGame = false;
        while(!quitGame) {
            List<Pokemon> myTeam = new LinkedList<>();
            List<Pokemon> opponentTeam = new LinkedList<>();
            startOfGame(input, random, allPokemon, myTeam, opponentTeam);
            menu(myTeam, opponentTeam, input);
            System.out.print("Press \"Q\" if you would like to quit. Otherwise enter any other key to start a new match. " );
            if(input.next().equalsIgnoreCase("q")) {
                quitGame = true;
            }
        }
    }

    public static void startOfGame(Scanner input, Random random, Pokedex allPokemon, List<Pokemon> playerTeam, List<Pokemon> opponentTeam) {
        chooseTeam(input, allPokemon, playerTeam);
        computerTeam(random, allPokemon, opponentTeam);
        System.out.println("Your team: ");
        printTeam(playerTeam);
        System.out.println();
        System.out.println("Your opponent's team: ");
        printTeam(opponentTeam);
        System.out.println();
    }

    public static void printTeam(List<Pokemon> team) {
        for(int i = 0; i < team.size(); i++) {
            System.out.println( (i + 1) + ". " + team.get(i));
        }
    }

    public static void menu(List<Pokemon> playerTeam, List<Pokemon> opponentTeam, Scanner input) {
        boolean quitMatch = false;
        while ((playerTeam.size() > 0 && opponentTeam.size() > 0) && !quitMatch) {
            String firstLetter = "";
            while (!firstLetter.equalsIgnoreCase("F") && !firstLetter.equalsIgnoreCase("S") &&
                    !firstLetter.equalsIgnoreCase("Q")) {
                System.out.print("Would you like to (F)ight, (S)witch Pokemon, or (Q)uit? ");
                firstLetter = input.next().substring(0, 1);
            }
            if (firstLetter.equalsIgnoreCase("F")) {
                fight(playerTeam, opponentTeam);
            } else if (firstLetter.equalsIgnoreCase("S")) {
                switchPokemon(playerTeam, input);
            } else {
                quitMatch = true;
            }
            System.out.println();
        }
    }


    public static void fight(List<Pokemon> playerTeam, List<Pokemon> opponentTeam) {
        Pokemon faster;
        Pokemon slower;
        if(playerTeam.get(0).getSpeed() >= opponentTeam.get(0).getSpeed()) {
            faster = playerTeam.get(0);
            slower = opponentTeam.get(0);
        } else {
            faster = opponentTeam.get(0);
            slower = playerTeam.get(0);
        }
        faster.reduceHealth(slower);
        if(slower.isFainted()) {
            removeFainted(slower, playerTeam, opponentTeam);
        } else {
            slower.reduceHealth(faster);
            if(faster.isFainted()) {
                removeFainted(faster, playerTeam, opponentTeam);
            }
        }
        if(playerTeam.size() > 0 && opponentTeam.size() > 0) {
            System.out.println("Your Pokemon: " + playerTeam.get(0));
            System.out.println("Opponent's Pokemon: " + opponentTeam.get(0));
        }
    }

    private static void removeFainted(Pokemon fainted, List<Pokemon> playerTeam, List<Pokemon> opponentTeam) {
        if(playerTeam.contains(fainted)) {
            playerTeam.remove(fainted);
        } else {
            opponentTeam.remove(fainted);
        }
    }

    public static void switchPokemon(List<Pokemon> team, Scanner input) {
        System.out.println("Your team: ");
        printTeam(team);
        System.out.print("Please type in the number that is next to the name of your Pokemon. "); // need to word this better
        int currentBattler = input.nextInt() - 1;
        Pokemon temp = team.remove(currentBattler);
        team.add(0, temp);
        System.out.println("my team: " + team);
        System.out.println();
    }
}
