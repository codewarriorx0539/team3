
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.filters.algorithms.CosineSimilarity;
import edu.uis.csc478b.team3.config.ConfigWordFrequency;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * WordFrequency vectorizes the words of two documents and computes the cosine similarity. 
 * https://en.wikipedia.org/wiki/Cosine_similarity 
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public class WordFrequency implements PlagiarismFilter
{
    final private float frequencyLowerBound;
    final private float frequencyUpperBound;
    final private float cosineSimilarityThreshold;
    final private CosineSimilarity cosineSimilarity;
    
    ConfigWordFrequency config;
    
    final protected String TAB = "\t";
    final protected String CLASSIFIER =  "CLASSIFIER: WORD FREQUENCY";
    final protected String FOUND = "Word Frequency: PLAGIARISM FOUND";
    final protected String NOT_FOUND = "Word Frequency: PLAGIARISM NOT FOUND";
    final protected String OUTSIDE = "Word Difference: OUTSIDE BOUND";
    final protected String INSIDE = "Word Difference: INSIDE BOUND";
    final protected String CONFIGURATION = "CONFIGURATION:";
    final protected String COUNT1 = "Word count file1: ";
    final protected String COUNT2 = "Word count file2: ";
    final protected String SIMILAR_COUNT = "Similar word count: ";
    final protected String COSINE = "Cosine Similarity: ";
   
    /**
     * Setup initial values
     * 
     * @param configWordFrequency 
     * @throws java.lang.Exception 
     */
    public WordFrequency( ConfigWordFrequency configWordFrequency ) throws Exception
    {
        frequencyLowerBound = configWordFrequency.getFrequencyLowerBound();
        frequencyUpperBound = configWordFrequency.getFrequencyUpperBound();
        cosineSimilarityThreshold = configWordFrequency.getCosineSimilarityThreshold();
        
        cosineSimilarity = new CosineSimilarity();
        
        config = new ConfigWordFrequency( configWordFrequency );
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

        float angle = cosineSimilarity.calcCosineSimilarity(map1, map2);
        
        String result = TAB + CLASSIFIER + System.lineSeparator();
        
        float ratioWords = (float) total1 / (float) total2;
        
        if( angle <= cosineSimilarityThreshold )
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
        result = result + TAB + COSINE + angle + System.lineSeparator();
        result = result + TAB + CONFIGURATION + System.lineSeparator();
        result = result + config.getConfigSetup();
            
        return result;
    }
    
}
