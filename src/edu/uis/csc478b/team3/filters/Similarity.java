
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.filters.algorithms.CosineSimilarity;
import edu.uis.csc478b.team3.filters.algorithms.SimilarityResults;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Similarity vectorizes the words of two documents and computes the cosine similarity. 
 * https://en.wikipedia.org/wiki/Cosine_similarity 
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public class Similarity extends PlagiarismFilter
{
    float frequencyLowerBound;
    float frequencyUpperBound;
    float cosineSimilarityThreshold;
    CosineSimilarity cosineSimilarity;
    
    final protected String TAB = "\t";
    final protected String CLASSIFIER =  "CLASSIFIER: SIMILARITY";
    final protected String FOUND = "Similarity: PLAGIARISM FOUND";
    final protected String NOT_FOUND = "Similarity: PLAGIARISM NOT FOUND";
    final protected String OUTSIDE = "Word Difference: OUTSIDE BOUND";
    final protected String INSIDE = "Word Difference: INSIDE BOUND";
    final protected String CONFIGURATION = "CONFIGURATION:";
    final protected String COUNT1 = "Word count file1: ";
    final protected String COUNT2 = "Word count file2: ";
    final protected String SIMILAR_COUNT = "Similar word count: ";
    final protected String COSINE = "Cosine Similarity: ";
    final protected String SCALED_COSINE = "Scaled Cosine Similarity: ";
    
    final protected String UPPER = "frequencyUpperBound: ";
    final protected String LOWER = "frequencyLowerBound: ";
    final protected String THRESHOLD = "cosineSimilarityThreshold: ";
   
    
    /**
     * Default values for Word frequency
     */
    public Similarity()
    {
        cosineSimilarityThreshold  = .7f;
        frequencyUpperBound  = 3.0f;
        frequencyLowerBound  = .3f;
        
        cosineSimilarity = new CosineSimilarity();
    }

    public Similarity(  float cosineSimilarityThreshold,
                        float frequencyLowerBound,
                        float frequencyUpperBound) throws Exception
    {
        this.cosineSimilarityThreshold = cosineSimilarityThreshold;   
        this.frequencyLowerBound = frequencyLowerBound;
        this.frequencyUpperBound = frequencyUpperBound;
        
        // BOUNDS CHECK
        if(frequencyLowerBound < 0 || cosineSimilarityThreshold < -1 || cosineSimilarityThreshold > 1)
        {
            throw new Exception("Similarity::Similarity value out of bounds");
        }
    }

    /**
     * Compute the cosine similarity
     * 
     * @param list1
     * @param list2
     * @return
     */
    @Override
    public String exec( ArrayList< String > list1, ArrayList< String > list2) throws Exception
    {
        int total1 = 0;
        int total2 = 0;
        
        Map<String, Integer> map1 = new TreeMap<>();
        Map<String, Integer> map2 = new TreeMap<>();
        
        for( String word : list1)
        {
            if( map1.containsKey(word) == true)
            {
                int wordTotal = map1.get(word);
                wordTotal++;
                map1.put(word, wordTotal);
            }
            else
            {
                map1.put(word, 1);
            }

            total1++;
        }

        for( String word : list2)
        {
            if( map2.containsKey(word) == true)
            {
                int wordTotal = map2.get(word);
                wordTotal++;
                map2.put(word, wordTotal);
            }
            else
            {
                map2.put(word, 1);
            }

            total2++;
        }

        float similarWords = 0;

        for(Map.Entry<String,Integer> entry : map1.entrySet())
        {
            if( map2.containsKey(entry.getKey()) == true)
            {
                int value = map2.get(entry.getKey());
                
                if( value > entry.getValue())
                {
                    similarWords = similarWords + entry.getValue();
                }
                else
                {
                    similarWords = similarWords + value;
                }
                
            }
        }

        SimilarityResults cosineResults = cosineSimilarity.calcCosineSimilarity(map1, map2);
        
        String result = TAB + CLASSIFIER + System.lineSeparator();
        
        float ratioWords = (float) total1 / (float) total2;
        
        if( cosineSimilarityThreshold < cosineResults.angle )
        {
            result = result + TAB + FOUND + System.lineSeparator();
        }
        else
        {
            result = result + TAB + NOT_FOUND + System.lineSeparator();
        }
         
        // Means the ratio of words is absurdly differently like one document as 
        // 1000 words and the other 2 words. Tested as a percentages/ratio
        if( (frequencyUpperBound < ratioWords)  || (frequencyLowerBound > ratioWords) )
        {
            result = result + TAB + OUTSIDE + System.lineSeparator();
        }
        else
        {
            result = result + TAB + INSIDE + System.lineSeparator();
        }

        result = result + TAB + COUNT1 + total1 + System.lineSeparator();
        result = result + TAB + COUNT2 + total2 + System.lineSeparator();
        result = result + TAB + SIMILAR_COUNT + similarWords + System.lineSeparator();
        result = result + TAB + COSINE + cosineResults.angle + System.lineSeparator();
        result = result + TAB + SCALED_COSINE + cosineResults.scaledAngle + System.lineSeparator();
        result = result + TAB + CONFIGURATION + System.lineSeparator();
        result = result + getConfigSetup();
            
        return result;
    }
    
    public float getFrequencyLowerBound() 
    {
        return frequencyLowerBound;
    }

    public void setFrequencyLowerBound(float frequencyLowerBound) 
    {
        this.frequencyLowerBound = frequencyLowerBound;
    }

    public float getFrequencyUpperBound() 
    {
        return frequencyUpperBound;
    }

    public void setFrequencyUpperBound(float frequencyUpperBound)
    {
        this.frequencyUpperBound = frequencyUpperBound;
    }

    public float getCosineSimilarityThreshold() 
    {
        return cosineSimilarityThreshold;
    }

    public void setCosineSimilarityThreshold(float cosineSimilarityThreshold) 
    {
        this.cosineSimilarityThreshold = cosineSimilarityThreshold;
    }
    
    @Override
    public String getConfigSetup() 
    {
        String setup = TAB + UPPER + frequencyUpperBound + System.lineSeparator();
        setup = setup + TAB + LOWER + frequencyLowerBound + System.lineSeparator();
        setup = setup + TAB + THRESHOLD + cosineSimilarityThreshold + System.lineSeparator();
        
        return setup;
    }
    
}
