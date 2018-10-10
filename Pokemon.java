public class Pokemon {

    private String name;
    private int health;
    private int mainAttack;
    private int defense;
    private int speed;
    private boolean fainted;

    public Pokemon(PokemonBaseStats pokemonStats) {
        this.name = pokemonStats.getName();
        this.health = pokemonStats.getHealth();
        this.mainAttack = pokemonStats.getAttack();
        this.defense = pokemonStats.getDefense();
        this.speed = pokemonStats.getSpeed();
        this.fainted = false;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isFainted() {
        return fainted;
    }

    public void reduceHealth(Pokemon opponent) {
        int difference = mainAttack - opponent.defense;
        if(difference > 0) {
            opponent.health -= difference;
        } else {
            opponent.health -= 1;
        }
        if(opponent.health <= 0) {
            opponent.fainted = true;
            System.out.println(opponent.name + " fainted. ");
        }
    }


    public String toString() {
        return name + " Health: " + health;
    }




}
