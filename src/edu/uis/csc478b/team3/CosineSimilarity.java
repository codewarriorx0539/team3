
package edu.uis.csc478b.team3;

import java.util.Map;

/**
 * 
 * <p>
 * <h3>Class:</h3> CosineSimilarity
 * <h3>Project:</h3> Plagiarism
 * <h3>Description:</h3>
 * Characterize the words as vector components and compute the angle between the </br> 
 * two vectors (word frequency in each file). The smaller the angle the more similar </br>
 * the vector. https://en.wikipedia.org/wiki/Cosine_similarity </br>
 * </p>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Programmer: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class CosineSimilarity 
{
    /**
     * Calculate the Cosine Similarity and return the angle between the vectors.
     * @param wordFrequency1
     * @param wordFrequency2
     * @return 
     */
    public float calcCosineSimilarity( Map<String, Integer> wordFrequency1, Map<String, Integer> wordFrequency2 )
    {
         float sum1 = 0;
         float sum2 = 0;
         float dot = 0;

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

         return dot/(float)(Math.sqrt(sum1) + Math.sqrt(sum2) );
    }
    
}
