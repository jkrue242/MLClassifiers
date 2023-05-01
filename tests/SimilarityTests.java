import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SimilarityTests tests Cosine Similarity, Euclidean Distance, and Hamming Distance
 */
public class SimilarityTests {

    /**
     * Tests cosine similarity of 2 test points
     */
    @Test
    void cosineSimilarityTest1()
    {
        Double test_v1[] = {1.0, 2.0, 3.0};
        Double test_v2[] = {2.0, 6.0, 3.0};
        assertTrue(Math.abs(0.8781 - Similarity.cosineSimilarity(test_v1, test_v2))<1e-3);
    }

    /**
     * Tests cosine similarity of 2 test points
     */
    @Test
    void cosineSimilarityTest2()
    {
        Double test_v1[] = {5.0, 6.0, 5.0, 4.0, 3.0};
        Double test_v2[] = {-1.0, 0.0, 3.0, 5.0, 4.0};
        assertTrue(Math.abs(0.558 - Similarity.cosineSimilarity(test_v1, test_v2))<1e-3);
    }

    /**
     * Tests cosine similarity of 2 test points
     */
    @Test
    void cosineSimilarityTest3()
    {
        Double test_v1[] = {-5.0, 6.0, 5.0, 4.0};
        Double test_v2[] = {-1.0, 0.0, -3.0, 5.0, 4.0};
        assertThrows(IllegalArgumentException.class, () -> {Similarity.cosineSimilarity(test_v1, test_v2);});
    }

    /**
     * Tests cosine similarity of 2 test points
     */
    @Test
    void cosineSimilarityTest4()
    {
        Double test_v1[] = {1.05, 17.5, -33.0, 0.0};
        Double test_v2[] = {-2.7, 6.0, 2.2, -11.0};
        assertTrue(Math.abs(0.0608 - Similarity.cosineSimilarity(test_v1, test_v2))<1e-3);
    }

    /**
     * Tests cosine similarity of 2 test points
     */
    @Test
    void cosineSimilarityTest5()
    {
        Double test_v1[] = {1.5, -1.5};
        Double test_v2[] = {-1.5, 1.5};
        assertTrue(Math.abs(-1.0 - Similarity.cosineSimilarity(test_v1, test_v2))<1e-3);
    }

    /**
     * Tests Euclidean distance of 2 test points
     */
    @Test
    void euclideanDistanceTest1()
    {
        Double test_v1[] = {1.0, 2.0, 3.0};
        Double test_v2[] = {2.0, 6.0, 3.0};
        assertTrue(Math.abs(4.12311 - Similarity.euclideanDistance(test_v1, test_v2))<1e-3);
    }

    /**
     * Tests Euclidean distance of 2 test points
     */
    @Test
    void euclideanDistanceTest2()
    {
        Double test_v1[] = {5.0, 6.0, 5.0, 4.0, 3.0};
        Double test_v2[] = {-1.0, 0.0, 3.0, 5.0, 4.0};
        assertTrue(Math.abs(8.83176 - Similarity.euclideanDistance(test_v1, test_v2))<1e-3);
    }

    /**
     * Tests Euclidean distance of 2 test points
     */
    @Test
    void euclideanDistanceTest3()
    {
        Double test_v1[] = {-5.0, 6.0, 5.0, 4.0, -3.0};
        Double test_v2[] = {-1.0, 0.0, -3.0, 5.0, 4.0};
        assertTrue(Math.abs(12.8841 - Similarity.euclideanDistance(test_v1, test_v2))<1e-3);
    }

    /**
     * Tests Euclidean distance of 2 test points
     */
    @Test
    void euclideanDistanceTest4()
    {
        Double test_v1[] = {1.05, 17.5, -33.0, 0.0};
        Double test_v2[] = {-2.7, 6.0};
        assertThrows(IllegalArgumentException.class, () -> {Similarity.euclideanDistance(test_v1, test_v2);});
    }

    /**
     * Tests Euclidean distance of 2 test points
     */
    @Test
    void euclideanDistanceTest5()
    {
        Double test_v1[] = {1.5, -1.5};
        Double test_v2[] = {-1.5, 1.5};
        assertTrue(Math.abs(4.24264 - Similarity.euclideanDistance(test_v1, test_v2))<1e-3);
    }

    /**
     * Tests Hamming distance of 2 test points
     */
    @Test
    void hammingTest1()
    {
        String test_s1 = "0110101";
        String test_s2 = "1110010";
        assertEquals(4, Similarity.hammingDistance(test_s1, test_s2));
    }

    /**
     * Tests Hamming distance of 2 test points
     */
    @Test
    void hammingTest2()
    {
        String test_s1 = "00000000";
        String test_s2 = "11111111";
        assertEquals(8, Similarity.hammingDistance(test_s1, test_s2));
    }

    /**
     * Tests Hamming distance of 2 test points
     */
    @Test
    void hammingTest3()
    {
        String test_s1 = "01010101";
        String test_s2 = "10101010";
        assertEquals(8, Similarity.hammingDistance(test_s1, test_s2));
    }

    /**
     * Tests Hamming distance of 2 test points
     */
    @Test
    void hammingTest4()
    {
        String test_s1 = "10101";
        String test_s2 = "1";
        assertThrows(IllegalArgumentException.class, () -> {Similarity.hammingDistance(test_s1, test_s2);});
    }

    /**
     * Tests Hamming distance of 2 test points
     */
    @Test
    void hammingTest5()
    {
        String test_s1 = "11100";
        String test_s2 = "10110";
        assertEquals(2, Similarity.hammingDistance(test_s1, test_s2));
    }
}
