import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassifierTests tests the k-nearest neighbors and k-means clustering algorithms
 */
public class ClassifierTests {

    /**
     * Test k-nearest neighbors with test datapoint
     */
    @Test
    void kNearestTest1()
    {
        Double[] test_dp = {1.5, 3.5, 2.0, 2.0, 8.0};
        assertEquals("class1", SupervisedClassifier.kNearest("resources/S27-MLMedium.csv",
                test_dp, 5));
    }

    /**
     * Test k-nearest neighbors with test datapoint
     */
    @Test
    void kNearestTest2()
    {
        Double[] test_dp = {3.0, 3.0, 2.0, 2.0, 1.0};
        assertEquals("class2", SupervisedClassifier.kNearest("resources/S27-MLMedium.csv",
                test_dp, 5));
    }

    /**
     * Test k-nearest neighbors with test datapoint
     */
    @Test
    void kNearestTest3()
    {
        Double[] test_dp = {-0.1659, 0.7573, 4.4277, 5.0438, 3.8174};
        assertEquals("class1", SupervisedClassifier.kNearest("resources/kNearestTestFile.csv", test_dp, 5));
    }

    /**
     * Test k-nearest neighbors with test datapoint
     */
    @Test
    void kNearestTest4()
    {
        Double[] test_dp = {1.6686, 2.3490, 2.1185, 3.4246, 4.6686};
        assertEquals("class1", SupervisedClassifier.kNearest("resources/kNearestTestFile.csv", test_dp, 5));
    }

    /**
     * Test k-nearest neighbors with test datapoint
     */
    @Test
    void kNearestTest5()
    {
        Double[] test_dp = {6.8803, 4.4321, 2.8626, 1.7508, 1.1608};
        assertEquals("class2", SupervisedClassifier.kNearest("resources/kNearestTestFile.csv", test_dp, 5));
    }

    /**
     * Test k-means classifier with test dataset
     */
    @Test
    void kMeansTest1()
    {
        HashMap<String, ArrayList<Double[]>> test_clusters = UnsupervisedClassifier.kMeans("resources/kMeansTestFile1.csv", 2);
        assertTrue(test_clusters.get("cluster1").size() == 50 && test_clusters.get("cluster2").size() == 50);
    }

    /**
     * Test k-means classifier with test dataset
     */
    @Test
    void kMeansTest2()
    {
        HashMap<String, ArrayList<Double[]>> test_clusters = UnsupervisedClassifier.kMeans("resources/kMeansTestFile2.csv", 2);
        assertTrue((test_clusters.get("cluster1").size() == 50 || test_clusters.get("cluster1").size() == 35) &&
                (test_clusters.get("cluster2").size() == 35 || test_clusters.get("cluster2").size() == 50));
    }

    /**
     * Test k-means classifier with test dataset
     */
    @Test
    void kMeansTest3()
    {
        HashMap<String, ArrayList<Double[]>> test_clusters = UnsupervisedClassifier.kMeans("resources/kMeansTestFile3.csv", 2);
        assertTrue((test_clusters.get("cluster1").size() == 30 || test_clusters.get("cluster1").size() == 50) &&
                (test_clusters.get("cluster2").size() == 50 || test_clusters.get("cluster2").size()==30));
    }

}
