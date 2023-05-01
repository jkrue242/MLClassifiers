import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * DataReader class handles reading input from a .csv dataset
 */
public class DataReader {

    /**
     * Converts the dataset to an ArrayList of Strings
     * @param path file path
     * @return ArrayList of Strings containing all data
     */
    public static ArrayList<String[]> readDatasetToString(String path)
    {
        ArrayList<String[]> data = new ArrayList<>();

        // read csv file
        try
        {
            InputStream input_stream= new FileInputStream(path);
            InputStreamReader input_reader = new InputStreamReader(input_stream);
            BufferedReader buffered_reader = new BufferedReader(input_reader);
            String line;

            // this loop processes each row in the dataset
            while ((line = buffered_reader.readLine()) != null)
            {
                // this splits each datapoint in csv delimited by comma and creates an array for the row
                String[] row_data = line.split(",");
                data.add(row_data);

            }
            buffered_reader.close();
        }
        // if the file could not be opened
        catch (Exception e)
        {
            System.out.println("The dataset file was not found.");
        }
        return data;
    }

    /**
     * Converts the dataset to an ArrayList of Doubles
     * @param path file path
     * @return ArrayList of Doubles containing data
     */
    public static ArrayList<Double[]> readDatasetToDouble(String path)
    {
        ArrayList<Double[]> data = new ArrayList<>();

        // read csv file
        try
        {
            InputStream input_stream= new FileInputStream(path);
            InputStreamReader input_reader = new InputStreamReader(input_stream);
            BufferedReader buffered_reader = new BufferedReader(input_reader);
            String line;

            // this loop processes each row in the dataset
            while ((line = buffered_reader.readLine()) != null) {
                // this splits each datapoint in csv delimited by comma and creates an array for the row
                String[] row_data = line.split(",");
                Double[] row_data_doubles = new Double[row_data.length];
                for (int i = 0; i < row_data.length; i++)
                {
                    row_data_doubles[i] = Double.parseDouble(row_data[i]);
                }
                data.add(row_data_doubles);
            }

            buffered_reader.close();
        }
        // if the file could not be opened
        catch (Exception e)
        {
            System.out.println("The dataset file was not found.");
        }
        return data;
    }

}
