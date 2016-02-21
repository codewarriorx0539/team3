
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.FileProcessor;
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
public class WordFrequency extends Filter
{
    Map<String, Integer> compareMap;
    Map<String, Integer> masterMap;
    
    // As percentages
    float frequencyLowerBound;
    float frequencyUpperBound;
    float frequencyDifferenceThreshold;
    
    public WordFrequency( PlagiarismTest testConfig , FileProcessor master, FileProcessor suspect )
    {
        super(testConfig.getConfigWordFrequency(), master,  suspect);
        
        compareMap = new TreeMap<>();
        masterMap = new TreeMap<>();
        
        frequencyLowerBound = testConfig.getConfigWordFrequency().getFrequencyLowerBound();
        frequencyUpperBound = testConfig.getConfigWordFrequency().getFrequencyUpperBound();
        frequencyDifferenceThreshold = testConfig.getConfigWordFrequency().getFrequencyDifferenceThreshold();
    }
    
    /**
     * Add words to HashMaps and then compare against each other. Frequency threshold is represented as a ratio
     * 
     * @return 
     */
    @Override
    public String runPlagiarismTest()
    {
        String result;
        
        int masterTotal = 0;
        int suspectTotal = 0;

        int mTotalWords = master.getNumWords();
        int sTotalWords = suspect.getNumWords();

        ArrayList<String> mWords = master.getWords();
        ArrayList<String> sWords = suspect.getWords();

        for( String word : sWords)
        {
            if( compareMap.containsKey(word) == true)
            {
                int wordTotal = compareMap.get(word);
                wordTotal++;
                compareMap.put(word, wordTotal);
            }
            else
            {
                compareMap.put(word, 1);
            }

            suspectTotal++;
        }

        for( String word : mWords)
        {
            if( masterMap.containsKey(word) == true)
            {
                int wordTotal = masterMap.get(word);
                wordTotal++;
                masterMap.put(word, wordTotal);
            }
            else
            {
                masterMap.put(word, 1);
            }

            masterTotal++;
        }


        float similarWords = 0;

        for(Map.Entry<String,Integer> entry : compareMap.entrySet())
        {
            if( masterMap.containsKey(entry.getKey()) == true)
            {
                similarWords = similarWords + entry.getValue();
            }
        }

        float ratioWords = (float) suspectTotal / (float) masterTotal;
        float percentageSimilar = ( similarWords / masterTotal );
        
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
            
        return result;
    }
    
}
