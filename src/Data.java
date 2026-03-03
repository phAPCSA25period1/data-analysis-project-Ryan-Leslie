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
 * (pokemon.csv) includes many columns; we only store a few here
 * to keep the exercise simple.
 */
public class Data {

    // three attributes from the CSV
    private int number;       // Pokédex number
    private String name;      // Pokémon name
    private String type1;     // primary type

    /**
     * Constructs a Data object using the columns we care about.
     *
     * @param number Pokédex number (first column)
     * @param name   Pokémon name (second column)
     * @param type1  primary type (third column)
     */
    public Data(int number, String name, String type1) {
        this.number = number;
        this.name = name;
        this.type1 = type1;
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

    @Override
    public String toString() {
        return "Data{number=" + number + ", name='" + name + "', type1='" + type1 + "'}";
    }
}