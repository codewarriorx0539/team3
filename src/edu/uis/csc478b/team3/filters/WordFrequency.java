
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.FileProcessor;
import edu.uis.csc478b.team3.config.ConfigWordFrequency;
import edu.uis.csc478b.team3.config.PlagiarismTest;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Compare word frequencies and return the percentage of exact matching words
 * 
 * @author Jake
 */
public class WordFrequency extends Filter
{
    Map<String, Integer> compareMap;
    Map<String, Integer> masterMap;
    
    // As percentages
    float FREQUENCY_LOWER_BOUND;
    float FREQUENCY_UPPER_BOUND;
    float FREQUENCY_DIFFERENCE_THRESHOLD;
    
    ConfigWordFrequency config;
    
    public WordFrequency( PlagiarismTest testConfig , FileProcessor master, FileProcessor suspect )
    {
        super(testConfig.getConfigWordFrequency(), master,  suspect);
        
        compareMap = new TreeMap<>();
        masterMap = new TreeMap<>();
        
        FREQUENCY_LOWER_BOUND = testConfig.getConfigWordFrequency().getFREQUENCY_LOWER_BOUND();
        FREQUENCY_LOWER_BOUND = testConfig.getConfigWordFrequency().getFREQUENCY_UPPER_BOUND();
        FREQUENCY_DIFFERENCE_THRESHOLD = testConfig.getConfigWordFrequency().getFREQUENCY_DIFFERENCE_THRESHOLD();
    }
    
    @Override
    public String runPlagiarismTest()
    {
        String result = "";
        
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

        float ratioWords = suspectTotal / masterTotal;
        
        // Means the ratio of words is absurdly differently like one document as 1000 words and the other 2 words. Tested as a percentages/ratio
        if( (FREQUENCY_UPPER_BOUND < ratioWords)  || (FREQUENCY_LOWER_BOUND > ratioWords) )
        {
            result = result + "WordDifference: PLAGIARISM NOT FOUND" + System.lineSeparator();
        }

        float percentageSimilar = ( similarWords / masterTotal );

        if( percentageSimilar >= FREQUENCY_DIFFERENCE_THRESHOLD )
        {
            result = result + "WordFrequency: PLAGIARISM FOUND" + System.lineSeparator();
        }
        else
        {
            result = result + "WordFrequency: PLAGIARISM NOT FOUND" + System.lineSeparator();
        }

        result = result + "Master word count: " + mTotalWords + System.lineSeparator();
        result = result + "Suspect word count: " + sTotalWords + System.lineSeparator();
        result = result + "Similar word count: " + similarWords + System.lineSeparator();
        result = result + config.getConfigSetup() + System.lineSeparator();
            
        return result;
    }
    
}
