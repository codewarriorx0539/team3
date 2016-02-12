
package edu.uis.csc478b.team3.filters;

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
    TreeMap<String, Integer> compareMap;
    TreeMap<String, Integer> masterMap;
   
    int DIFFERENCE_THRESHOLD;
    
    public WordFrequency( ArrayList<ArrayList<String> > mWords, ArrayList<ArrayList<String> > sWords, ArrayList<String> mSentences , ArrayList<String> sSentences )
    {
        super(mWords, sWords, mSentences , sSentences);
    }
    
    /**
     * 
     * @param compare
     * @param master
     * @return 
     */
    public String runPlagiarismTest()
    {
        boolean result = false;
        int masterTotal = 0;
        int compareTotal = 0;
        
        if( mWords == null || sWords == null )
        {
            throw new NullPointerException();
        }
        
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
                
                compareTotal++;
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
        
        /*
        
        new WordFrequencyResults( masterTotal, compareTotal, similarWords).
        
        */
        
        return "";
    }
    
    /*
    if( DIFFERENCE_THRESHOLD > Math.abs( results.getMasterTotal() - results.getCompareTotal())  )
                // This means that if there is a large difference in the number of words between documents then it is unlikely to be similar in any way
            {
                output = "We were unable to classify: " + suspectFile + " Please inspect manually." + System.lineSeparator();
                printOutput(output);
                return;
            }
            
            output = "PASSED DIFFERENCE THRESHOLD" + System.lineSeparator();
    
    
     if( FREQUENCY_DIFFERENCE_THRESHOLD > Math.abs( results.getMasterTotal() - results.getCompareTotal()) )
                // This is specific to word frequency
            {
                float percentageSimilar = ( results.getSimilarWords() / results.getMasterTotal() );
                
                if( percentageSimilar >= SIMILAR_WORDS_THRESHOLD )
                {
                    output = output + "ALERT PLAGIARISM (FREQUENCY DIFFERENCE THRESHOLD): " + suspectFile + System.lineSeparator();
                    printOutput(output);
                    return;
                }
            }
            output = output + "PASSED FREQUENCY DIFFERENCE THRESHOLD" + System.lineSeparator();
            /////////////////////////////////////
    */

    @Override
    public void readData(String masterFile, String suspectFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
