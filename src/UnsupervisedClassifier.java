import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * UnsupervisedClassifier handles the k-means clustering algorithm
 */
public class UnsupervisedClassifier {

    /**
     * This runs the kMeans Clustering algorithm and returns a HashMap of Cluster names and pointts
     * @param data_path dataset path
     * @param n_clusters number of clusters to use
     * @return HashMap with cluster names as keys, points in cluster as values
     */
    public static HashMap<String, ArrayList<Double[]>> kMeans(String data_path, Integer n_clusters)
    {
        // How the algorithm works:
        // pick n random points to be the centroids of each cluster
        // assign every point to the nearest cluster using euclidean distance
        // compute the new centroid position by taking the average of all the cluster points
        // repeat until convergence clusters don't change

        // read the dataset
        ArrayList<Double[]> data = DataReader.readDatasetToDouble(data_path);

        // initialize clusters with random points in dataset
        Random random_generator = new Random();

        // initialize hashmap that will store clusters and centroids
        HashMap<String, Double[]> cluster_centroids = new HashMap<>();

        // initialize has that will store the initial nearest cluster for each point
        HashMap<Double[], String> nearest_clusters_map = new HashMap<>();

        // create a hashmap that stores the points for each cluster
        HashMap<String, ArrayList<Double[]>> cluster_points = new HashMap<>();

        // randomly sample n cluster centroids
        for (int i = 0; i < n_clusters; i++)
        {
            String clusterName = "cluster"+(i+1);
            int random_index = random_generator.nextInt(data.size());
            Double[] centroid = data.get(random_index);

            // populate the centroid hash map
            cluster_centroids.put(clusterName, centroid);
        }

        // initialize the nearest cluster for each point as cluster1
        for (int i = 0; i < data.size(); i++)
        {
            nearest_clusters_map.put(data.get(i), "cluster1");
        }

        // this tracks the number of iterations
        int iter = 0;

        // we will loop through 30 times. After some research, 25-50 is the ideal iteration
        while (iter < 30)
        {
            // calculate the nearest cluster for each point and update hash map
            for (int i = 0; i < data.size(); i++)
            {
                // point we are processing
                Double[] point = data.get(i);

                // find the nearest cluster
                String nearest_cluster = getNearestCluster(point, cluster_centroids);

                // update the nearest cluster in the map
                nearest_clusters_map.put(point, nearest_cluster);
            }

            // clear the points in each cluster
            cluster_points.clear();

            // re populate the cluster map
            for (Double[] key : nearest_clusters_map.keySet()) {
                String cluster_name = nearest_clusters_map.get(key);

                // if the cluster we are processing does not exist in the cluster map yet
                if (!cluster_points.containsKey(cluster_name)) {
                    ArrayList<Double[]> points = new ArrayList<>();
                    points.add(key);
                    cluster_points.put(cluster_name, points);
                }
                // if the cluster does exist in the map, just append the new point onto the list
                else {
                    ArrayList<Double[]> points = cluster_points.get(cluster_name);
                    points.add(key);
                    cluster_points.put(cluster_name, points);
                }
            }

            // find the new centroids for each cluster
            for (String cluster_name : cluster_points.keySet())
            {
                // find new centroid
                Double[] new_centroid = computeCentroid(cluster_points.get(cluster_name));

                // update map
                cluster_centroids.put(cluster_name, new_centroid);
            }

            iter += 1;
        }

        // return the hashmap containing each cluster and points
        return cluster_points;
    }

    /**
     * Calculates the centroid of a cluster
     * @param cluster_points points in cluster
     * @return centroid of cluster
     */
    private static Double[] computeCentroid(ArrayList<Double[]> cluster_points)
    {
        // find length of collection of points
        Integer num_points_in_cluster = cluster_points.size();

        // initialize the summed point to be length of a data point in our data set and all zeroes
        Double[] summed_point = new Double[cluster_points.get(0).length];
        for (int i = 0; i < summed_point.length; i++)
        {
            summed_point[i] = 0.0;
        }

        // loop through points and sum them together
        for (int i = 0; i < cluster_points.size(); i++)
        {
            // loop through each value in every data point
            for (int j = 0; j < cluster_points.get(i).length; j++)
            {
                summed_point[j] += cluster_points.get(i)[j];
            }
        }

        // divide summed point by length for average
        Double[] average_point = new Double[summed_point.length];
        for (int i = 0; i < average_point.length; i++)
        {
            average_point[i] = summed_point[i]/num_points_in_cluster;
        }

        return average_point;
    }

    /**
     * Calculates the nearest cluster to a point
     * @param point point to check
     * @param clusters HashMap of cluster names and centroids
     * @return name of the nearest cluster
     */
    private static String getNearestCluster(Double[] point, HashMap<String, Double[]> clusters)
    {
        // set initial nearest distance to cluster 1
        Double nearest_distance = Similarity.euclideanDistance(point, clusters.get("cluster1"));
        String closest_cluster = "cluster1";

        // loop over all clusters
        for (String cluster_name : clusters.keySet())
        {
            // process next cluster distance
            Double test_distance = Similarity.euclideanDistance(point, clusters.get(cluster_name));

            // if the distance we processed is less than the current nearest, update the nearest cluster
            if (test_distance <= nearest_distance)
            {
                nearest_distance = test_distance;
                closest_cluster = cluster_name;
            }
        }

        return closest_cluster;
    }

    /**
     * Driver code
     * @param args Command line arguments
     */
    public static void main(String args[])
    {
        String path = "oral_exam1/S27_Machine_Learning_Hard/resources/S27-MLHard.csv";
        HashMap<String, ArrayList<Double[]>> clusters_and_points = kMeans(path, 4);

        // print get an ordering of the cluster names
        ArrayList<String> clusters = new ArrayList<String>();
        for (String cluster_name : clusters_and_points.keySet())
        {
            clusters.add(cluster_name);
        }

        // sort the cluster names
        Collections.sort(clusters);

        String output = "";
        for (String cluster_name : clusters)
        {
            output+= (cluster_name + ": " + clusters_and_points.get(cluster_name).size() + " data points\n");
        }
        System.out.println(output);
    }

}
