import java.io.File;

/**
 * Main application for the Data Analysis Mini‑Project.
 *
 * TODO:
 *  - Update the path to your dataset file
 *  - Read the CSV file using Scanner
 *  - Parse each row and extract the correct columns
 *  - Construct Data objects from each row
 *  - Store them in an array
 *  - Write methods to analyze the dataset (min, max, average, filters, etc.)
 *  - Print insights and answer your guiding question
 *  - Add Javadoc comments for any methods you create
 */
public class App {

    public static void main(String[] args) {

        // TODO: Update this with your CSV file path
        File file = new File("data/pokemon.csv");

        // create an array to hold the records we will read from the CSV
        // the size here is arbitrary; you can adjust it or switch to a
        // collection (ArrayList) once you know how many rows are in your file.
        Data[] dataList = new Data[1000];
        int recordCount = 0; // keeps track of how many entries we've added

        // read file using Scanner and populate the dataList array
        try (java.util.Scanner in = new java.util.Scanner(file)) {
            if (in.hasNextLine()) {
                in.nextLine(); // skip header row
            }
            while (in.hasNextLine() && recordCount < dataList.length) {
                String line = in.nextLine();
                String[] parts = line.split(",");
                if (parts.length < 6) {
                    continue; // skip malformed lines
                }
                // columns: 0=Number,1=Pokemon,2=Type 1,3=Type 2,4=HP,5=Attack,...
                int number = 0;
                try {
                    number = Integer.parseInt(parts[0].trim());
                } catch (NumberFormatException nfe) {
                    // ignore
                }
                String pokeName = parts[1].trim();
                String type1 = parts[2].trim();
                String type2 = parts[3].trim();
                int attack = 0;
                try {
                    attack = Integer.parseInt(parts[5].trim());
                } catch (NumberFormatException nfe) {
                    // default 0
                }
                dataList[recordCount++] = new Data(number, pokeName, type1, type2, attack);
            }
        } catch (java.io.FileNotFoundException fnf) {
            System.err.println("CSV file not found: " + file.getPath());
            return;
        }

        // guiding question text
        String guidingQuestion = "Are Pokémon with one typing more powerful (attack stat) than Pokémon with two typings?";

        // simple analysis methods (min, max, average of 'number' field)
        double minVal = findMinNumber(dataList, recordCount);
        double maxVal = findMaxNumber(dataList, recordCount);
        double avgVal = computeAverageNumber(dataList, recordCount);

        // new analysis for guiding question
        double avgAttackSingle = computeAverageAttackByTypeCount(dataList, recordCount, 1);
        double avgAttackDual = computeAverageAttackByTypeCount(dataList, recordCount, 2);
        String guidance;
        if (avgAttackSingle > avgAttackDual) {
            guidance = "Yes – single-type Pokémon have higher average attack (" + avgAttackSingle + " vs " + avgAttackDual + ").";
        } else if (avgAttackSingle < avgAttackDual) {
            guidance = "No – dual-type Pokémon have higher average attack (" + avgAttackDual + " vs " + avgAttackSingle + ").";
        } else {
            guidance = "They have equal average attack (" + avgAttackSingle + ").";
        }

        // print insights
        System.out.println("Guiding question: " + guidingQuestion + "\n");
        System.out.println("Rows loaded: " + recordCount);
        System.out.println("min number = " + minVal);
        System.out.println("max number = " + maxVal);
        System.out.println("avg number = " + avgVal + "\n");

        System.out.println("Avg attack (single-type): " + avgAttackSingle);
        System.out.println("Avg attack (dual-type): " + avgAttackDual + "\n");
        System.out.println("Answer: " + guidance);

    }

    /**
     * Finds the minimum "number" in the data array (ignoring nulls).
     */
    public static double findMinNumber(Data[] list, int count) {
        if (count == 0) return 0;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < count; i++) {
            if (list[i] != null && list[i].getNumber() < min) {
                min = list[i].getNumber();
            }
        }
        return min == Double.MAX_VALUE ? 0 : min;
    }

    /**
     * Finds the maximum "number" in the data array (ignoring nulls).
     */
    public static double findMaxNumber(Data[] list, int count) {
        if (count == 0) return 0;
        double max = Double.MIN_VALUE;
        for (int i = 0; i < count; i++) {
            if (list[i] != null && list[i].getNumber() > max) {
                max = list[i].getNumber();
            }
        }
        return max == Double.MIN_VALUE ? 0 : max;
    }

    /**
     * Computes the average of the "number" field in the data array.
     */
    public static double computeAverageNumber(Data[] list, int count) {
        if (count == 0) return 0;
        double sum = 0;
        for (int i = 0; i < count; i++) {
            if (list[i] != null) {
                sum += list[i].getNumber();
            }
        }
        return sum / count;
    }

    /**
     * Computes the average attack value for Pokémon with a given number
     * of typings (1 or 2). Rows with other counts are ignored.
     *
     * @param list  array of Data objects
     * @param count number of valid entries in array
     * @param typeCount number of typings to filter by (1 or 2)
     * @return average attack stat for matching entries (0 if none)
     */
    public static double computeAverageAttackByTypeCount(Data[] list, int count, int typeCount) {
        if (count == 0) return 0;
        double sum = 0;
        int matched = 0;
        for (int i = 0; i < count; i++) {
            Data d = list[i];
            if (d == null) continue;
            int types = 0;
            if (d.getType1() != null && !d.getType1().isEmpty()) types++;
            if (d.getType2() != null && !d.getType2().isEmpty()) types++;
            if (types == typeCount) {
                sum += d.getAttack();
                matched++;
            }
        }
        return matched == 0 ? 0 : sum / matched;
    }

}