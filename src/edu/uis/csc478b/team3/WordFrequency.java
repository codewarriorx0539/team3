
package edu.uis.csc478b.team3;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Compare word frequencies and return the percentage of exact matching words
 * 
 * @author Jake
 */
public class WordFrequency 
{
    
    TreeMap<String, Integer> compareMap;
    TreeMap<String, Integer> masterMap;
   
    
    /**
     * 
     * @param compare
     * @param master
     * @return 
     */
    public WordFrequencyResults frequency( ArrayList< ArrayList<String> > compare, ArrayList< ArrayList<String> > master)
    {
        float masterTotal = 0;
        float compareTotal = 0;
        
        if( compare == null || master == null )
        {
            throw new NullPointerException();
        }
        
        for( ArrayList<String> sentence : compare)
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
        
        for( ArrayList<String> sentence : master)
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
        
        return new WordFrequencyResults(masterTotal, compareTotal, similarWords);
    }
    
    
}
