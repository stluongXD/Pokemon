public class PokemonBaseStats {

    private int index;
    private String name;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private int special;

    public PokemonBaseStats(int index, String name, int health, int attack, int defense, int speed, int special) {
        this.index = index;
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.special = special;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return Math.max(attack, special);
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }
}
