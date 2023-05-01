/**
 * Similarity class handles Cosine Similarity, Euclidean Distance, and Hamming Distance
 */
public class Similarity {

    /**
     * Returns the cosine similarity between 2 vectors
     * @param a vector a
     * @param b vector b
     * @return cosine similarity
     */
    public static Double cosineSimilarity(Double a[], Double b[])
    {
        // cosine similarity is the dot product of 2 vectors divided by
        // the product of their magnitudes

        // check that arrays are same size
        if (a.length == b.length)
        {
            // get dot product
            Double dot_product = calculateDotProduct(a, b);

            // get magnitudes
            Double a_mag = calculateMagnitude(a);
            Double b_mag = calculateMagnitude(b);

            return (dot_product / (a_mag * b_mag));
        }
        else
        {
            throw new IllegalArgumentException("Array sizes do not match! [Cosine Similarity]");
        }

    }

    /**
     * Returns the Euclidean distance between 2 vectors
     * @param a vector a
     * @param b vector b
     * @return Euclidean distance
     */
    public static Double euclideanDistance(Double a[], Double b[])
    {
        // euclidean distance is the square root of the differences
        // between each vector at each index squared
        // check that the arrays are the same size
        if (a.length == b.length)
        {
            // initialize sum to 0
            Double sum = 0.0;

            // loop through array and increment sum with the difference of each array squared
            for (int i = 0; i < a.length; i++)
            {
                Double difference = b[i] - a[i];
                sum += (difference * difference);
            }
            // take square root of the sum
            return Math.sqrt(sum);
        }
        else
        {
            throw new IllegalArgumentException("Array sizes do not match! [Euclidean Distance]");
        }

    }

    /**
     * Returns the Hamming distance between 2 Strings
     * @param s1 String 1
     * @param s2 String 2
     * @return Hamming distance
     */
    public static Integer hammingDistance(String s1, String s2)
    {
        // hamming distance is the number of positions where the strings do not match

        // check that strings are the same size
        if (s1.length() == s2.length())
        {
            // initialize distance to 0
            Integer distance = 0;

            // loop over length of string 1 and check against string 2
            for (int i = 0; i < s1.length(); i++)
            {
                // if the strings do not match at an index, increment distance
                if(s1.charAt(i) != s2.charAt(i))
                {
                    distance += 1;
                }
            }

            return distance;
        }
        else
        {
            throw new IllegalArgumentException("String sizes do not match! [Hamming Distance]");
        }
    }

    /**
     * Calculates the dot product between 2 vectors
     * @param a vector a
     * @param b vector b
     * @return dot product
     */
    private static Double calculateDotProduct(Double a[], Double b[])
    {
        // initialize sum to 0
        Double total = 0.0;

        // loop over each vector, adding the product of each at the corresponding indices
        for (int i = 0; i < a.length; i++)
        {
            // increment total
            total += (a[i] * b[i]);
        }

        return total;
    }

    /**
     * Returns the magnitude of a vector
     * @param a vector a
     * @return magnitude
     */
    private static Double calculateMagnitude(Double a[])
    {
        // initialize total to 0
        Double total = 0.0;

        // loop over each index, squaring the value and adding to total
        for (int i = 0; i < a.length; i++)
        {
            total += (a[i]*a[i]);
        }

        // take sqaure root of answer
        return Math.sqrt(total);
    }
}
