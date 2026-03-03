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
                if (parts.length < 3) {
                    continue; // skip malformed lines
                }
                // first three columns: number,name,type1
                int number = 0;
                try {
                    number = Integer.parseInt(parts[0].trim());
                } catch (NumberFormatException nfe) {
                    // leave as 0
                }
                String pokeName = parts[1].trim();
                String type1 = parts[2].trim();
                dataList[recordCount++] = new Data(number, pokeName, type1);
            }
        } catch (java.io.FileNotFoundException fnf) {
            System.err.println("CSV file not found: " + file.getPath());
            return;
        }

        // simple analysis methods (min, max, average of 'number' field)
        double minVal = findMinNumber(dataList, recordCount);
        double maxVal = findMaxNumber(dataList, recordCount);
        double avgVal = computeAverageNumber(dataList, recordCount);

        // print insights
        System.out.println("Rows loaded: " + recordCount);
        System.out.println("min number = " + minVal);
        System.out.println("max number = " + maxVal);
        System.out.println("avg number = " + avgVal + "\n");

        // a placeholder answer for guiding question
        System.out.println("Guiding question answer: <your analysis here>");

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

}