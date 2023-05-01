import java.util.*;

/**
 * SupervisedClassifier class handles the k-nearest neighbor classifier
 */
public class SupervisedClassifier {

    /**
     * K-Nearest driver code
     * @param args command line input
     */
    public static void main(String args[])
    {
        System.out.println("Enter data point value:");
        Scanner scanner = new Scanner(System.in);
        Double[] data_point = new Double[5];
        for (int i = 0; i < 5; i++)
        {
            data_point[i] = scanner.nextDouble();
        }
        String path = "oral_exam1/S27_Machine_Learning_Hard/resources/S27-MLMedium.csv";
        System.out.println("New data point belongs to "+kNearest(path, data_point, 5));
    }

    /**
     * Runs the k nearest neighbors algorithm and returns predicted class name
     * @param data_path dataset file path
     * @param data_point test data point
     * @param k k neighbors
     * @return predicted class name
     */
    public static String kNearest(String data_path, Double data_point[], Integer k)
    {
        // k-nearest algorithm finds the closest k points to the given data point using
        // euclidean distance and returns the most common shared class name between the neighbors

        // initialize list to hold array data and class data for each datapoint
        ArrayList<String[]> data = DataReader.readDatasetToString(data_path);

        // create a hashmap that maps each distance to one (or more) indices
        HashMap<Double, ArrayList<Integer>> datapoint_distances_map = new HashMap<Double, ArrayList<Integer>>();

        // loop through the dataset and find distances
        for (int i = 0; i < data.size(); i++)
        {
            // get the datapoint value information at index i
            Double[] data_values = new Double[5];
            for (int j = 0; j < 5; j++)
            {
                // grab the double version of datapoint at index i
                data_values[j] = Double.parseDouble(data.get(i)[j]);
            }

            // make sure we don't compare the same datapoint twice
            if (data_values != data_point)
            {
                // get distance, add distance and class to List
                Double dist = Similarity.euclideanDistance(data_point, data_values);

                // check if the distance already exists in the map
                if (datapoint_distances_map.containsKey(dist))
                {
                    // need to add the current index to the list of indices
                    ArrayList<Integer> indices_at_distance = datapoint_distances_map.get(dist);
                    indices_at_distance.add(i);
                    datapoint_distances_map.put(dist, indices_at_distance);
                }
                // if it doesn't already exist, the list of indices will only store the current index
                else
                {
                    ArrayList<Integer> index_at_distance = new ArrayList<>();
                    index_at_distance.add(i);
                    datapoint_distances_map.put(dist, index_at_distance);
                }
            }
        }

        // Create a list of distances and sort them in ascending order
        List<Double> distances_list = new ArrayList<>(datapoint_distances_map.keySet());
        Collections.sort(distances_list);

        // this array stores the class values for each datapoint (list of size k)
        String[] classes_list = new String[k];

        // find the class names for the k nearest neighbors
        int index = 0;

        // initialize count to 0
        int count = 0;

        // loop up to k neighbors
        for (int i = 0; i < k; i++)
        {
            // because each distance value may not be unique, need to check if there are
            // more than 1 index for each distance

            // initialize count to -1 so that we only increase i if we have more than 1 index
            // for current distance
            int multiple_indices = -1;
            for (Integer point_index : datapoint_distances_map.get(distances_list.get(i)))
            {
                // grab the string class from each row in data
                classes_list[index] = data.get(point_index)[5];
                multiple_indices += 1;
                count+=1;

                // break out if we have found k neighbors
                if (count == k)
                {
                    break;
                }
            }
            // increment i so that we account for multiple keys per value
            i+=multiple_indices;

            // increment index
            index+=1;

            // break out if we already found k neighbors
            if (count == k)
            {
                break;
            }
        }

        // now we need to find the most common string
        int highest_freq = 0;
        String predicted_class = "";

        // loop through the list of classes, update the most common class name based on frequency
        for (int i = 0; i < classes_list.length; i++)
        {
            // initialize the count corresponding to index i of list to 0
            int current_count = 0;

            // this will compare the value at i+n index of list
            for (int j = i+1; j < classes_list.length; j++)
            {
                // if the 2 indices match, increment count
                if (classes_list[i] == classes_list[j])
                {
                    current_count += 1;
                }
            }

            // if count is greater than the current frequency, update the frequency
            if (current_count >= highest_freq) {
                highest_freq = current_count;

                // update most frequent word
                predicted_class = classes_list[i];
            }
        }

        // return predicted class name (remove quotes)
        return predicted_class.replace("\"", "");
    }
}
