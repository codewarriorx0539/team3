
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.config.ConfigWordFrequency;
import edu.uis.csc478b.team3.config.PlagiarismTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Compare word frequencies and return the percentage of exact matching words
 * 
 * @author Jake
 */
public class WordFrequency extends Filter
{
    Map<String, Integer> compareMap;
    Map<String, Integer> masterMap;
    
    ConfigWordFrequency config;
    
    public WordFrequency()
    {
    
    }
    
    public WordFrequency( PlagiarismTest testConfig )
    {
        super(testConfig);
        
        compareMap = new TreeMap<>();
        masterMap = new TreeMap<>();
        
        config = testConfig.getConfigWordFrequency();
    }
    
    /**
     * 
     * @param compare
     * @param master
     * @return 
     */
    @Override
    public String runPlagiarismTest()
    {
        String result = "";
        try 
        {
            
            int DIFFERENCE_THRESHOLD = 0;
            int SIMILAR_WORDS_THRESHOLD = 0;
            int masterTotal = 0;
            int suspectTotal = 0;
            
            String master = fileProcessor.fileAsAString(masterFile);
            String suspect = fileProcessor.fileAsAString(suspectFile);
            
            int mTotalWords = fileProcessor.getWordsOfSentences(master, mWords);
            int sTotalWords = fileProcessor.getWordsOfSentences(suspect, sWords);
            
            
            
            for( ArrayList<String> sentence : sWords)
            {
                for( String word : sentence)
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
            }
            
            for( ArrayList<String> sentence : mWords)
            {
                for( String word : sentence)
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
            }
            
            float similarWords = 0;
            
            for(Map.Entry<String,Integer> entry : compareMap.entrySet())
            {
                if( masterMap.containsKey(entry.getKey()) == true)
                {
                    similarWords = similarWords + entry.getValue();
                }
            }
            
            
            if( DIFFERENCE_THRESHOLD > Math.abs( masterTotal - suspectTotal)  )
            {
                result = result + "WordDifference: PLAGIARISM NOT FOUND" + System.lineSeparator();
            }
            
            
            float percentageSimilar = ( similarWords / masterTotal );
            
            if( percentageSimilar >= SIMILAR_WORDS_THRESHOLD )
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
            
        } catch (IOException ex) 
        {
            Logger.getLogger(WordFrequency.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
}
