
package edu.uis.csc478b.team3.filters.algorithms;

import java.util.Map;

/**
 * CosineSimilarity: characterize words as vector components and compute the 
 * angle between the two vectors. https://en.wikipedia.org/wiki/Cosine_similarity
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public class CosineSimilarity 
{
    /**
     * Calculate the Cosine Similarity and return the angle between the vectors.
     * 
     * @param wordFrequency1 n dimensional vector of words
     * @param wordFrequency2 n dimensional vector of words
     * @return SimilarityResults between vectors
     * @throws java.lang.Exception
     */
    public SimilarityResults calcCosineSimilarity( Map<String, Integer> wordFrequency1, Map<String, Integer> wordFrequency2 ) throws Exception
    {
         float sum1 = 0;
         float sum2 = 0;
         float dot = 0;
         
        if( wordFrequency1 == null || wordFrequency2 == null )
        {
           throw new NullPointerException("CosineSimilarity::calcCosineSimilarity");
        }

        // Calculate Cosine Similarity
        for (Map.Entry<String, Integer> entry : wordFrequency1.entrySet()) 
        {
           String key = entry.getKey();
           int value = entry.getValue();

           if(wordFrequency2.containsKey(key))
           { 
               dot += (value * wordFrequency2.get(key));
           }

           sum1 += (value * value);
        }

        for (Map.Entry<String, Integer> entry : wordFrequency2.entrySet()) 
        {
           int value = entry.getValue();

           sum2 += (value * value);
        }

        if(sum1 == 0 || sum2 == 0)
        {
            throw new Exception("CosineSimilarity::calcCosineSimilarity divide by zero");
        }

        // The scaling below takes into account the word count of the documents. 
        // If they are the same its like multipling by 1. The farther apart the
        // more dissimilar they are. First find the smaller count and use as the numerator.
        float size1 = sum1;
        float size2 = sum2;
        float scale = 0;
        
        if(sum1 > sum2)
        {
            scale = size2/size1;
        }
        else
        {
            scale = size1/size2;
        }
        
        float angle = dot/(float)(Math.sqrt(sum1) * Math.sqrt(sum2));
        
        return new SimilarityResults( angle, angle * scale ); 
    }
    
}
