
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.CosineSimilarity;
import edu.uis.csc478b.team3.config.PlagiarismTest;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * <p>
 * <h3>Class:</h3> WordFrequency
 * <h3>Project:</h3> Plagiarism
 * <h3>Description:</h3>
 * WordFrequency compares the ratio of similar words in the master and suspect to classify plagiarism.
 * </p>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Programmer: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class WordFrequency implements PlagiarismFilter
{
    final private float frequencyLowerBound;
    final private float frequencyUpperBound;
    final private float frequencyDifferenceThreshold;
    final private CosineSimilarity cosineSimilarity;
    
    public WordFrequency( PlagiarismTest testConfig )
    {
        frequencyLowerBound = testConfig.getConfigWordFrequency().getFrequencyLowerBound();
        frequencyUpperBound = testConfig.getConfigWordFrequency().getFrequencyUpperBound();
        frequencyDifferenceThreshold = testConfig.getConfigWordFrequency().getFrequencyDifferenceThreshold();
        
        cosineSimilarity = new CosineSimilarity();
    }

    @Override
    public String exec( ArrayList< String > list1, ArrayList< String > list2)
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
                similarWords = similarWords + entry.getValue();
            }
        }

        float angle = cosineSimilarity.calcCosineSimilarity(map1, map2);
        
        String result = "\tAngle: " + angle;
        
        
        
        /*
        float ratioWords = (float) total1 / (float) total2;
        float percentageSimilar = ( similarWords / total2 );
        
        result = "CLASSIFIER: WORD FREQUENCY" + System.lineSeparator();
       
         if( percentageSimilar >= frequencyDifferenceThreshold )
        {
            result = result + "Word Frequency: PLAGIARISM FOUND" + System.lineSeparator();
        }
        else
        {
            result = result + "Word Frequency: PLAGIARISM NOT FOUND" + System.lineSeparator();
        }
         
        // Means the ratio of words is absurdly differently like one document as 1000 words and the other 2 words. Tested as a percentages/ratio
        if( (frequencyUpperBound < ratioWords)  || (frequencyLowerBound > ratioWords) )
        {
            result = result + "Word Difference: OUTSIDE BOUND" + System.lineSeparator();
        }
        else
        {
            result = result + "Word Difference: INSIDE BOUND" + System.lineSeparator();
        }

        result = result + "CONFIGURATION:" + System.lineSeparator();
        result = result + "Master word count: " + mTotalWords + System.lineSeparator();
        result = result + "Suspect word count: " + sTotalWords + System.lineSeparator();
        result = result + "Similar word count: " + similarWords + System.lineSeparator();
        result = result + "Ratio of Suspect to Master words: " + ratioWords + System.lineSeparator();
        result = result + "Frequency of Similar Words: " + percentageSimilar + System.lineSeparator();
        result = result + configSetup + System.lineSeparator();
        */
            
        return result;
    }
    
}
