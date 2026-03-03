/**
 * Represents one row from your dataset.
 *
 * TODO:
 *  - Rename the class to match your dataset (e.g., Pokemon, StateData, CountryStat)
 *  - Add at least 3 private attributes based on your CSV columns
 *  - Write a constructor that initializes all attributes
 *  - Add getter methods for the attributes you need in your analysis
 *  - Override toString() to display the object's data
 *  - Add Javadoc comments for the class and all methods
 */
/**
 * Represents one Pokémon entry from the CSV file.  The dataset
 * (pokemon.csv) includes many columns; we store those needed for the
 * analysis question (types and attack stat).
 */
public class Data {

    // relevant attributes from the CSV
    private int number;       // Pokédex number
    private String name;      // Pokémon name
    private String type1;     // primary type
    private String type2;     // secondary type (may be empty)
    private int attack;       // attack stat value

    /**
     * Constructs a Data object using the required columns.
     *
     * @param number Pokédex number
     * @param name   Pokémon name
     * @param type1  primary type
     * @param type2  secondary type ("" if none)
     * @param attack attack stat
     */
    public Data(int number, String name, String type1, String type2, int attack) {
        this.number = number;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.attack = attack;
    }

    // getters
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public String toString() {
        return "Data{number=" + number + ", name='" + name + "', type1='" + type1 + "', type2='" + type2 + "', attack=" + attack + "}";
    }
}