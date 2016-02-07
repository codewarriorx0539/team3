
package edu.uis.csc478b.team3;

import java.util.ArrayList;
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
    public float countFrequency( ArrayList< ArrayList<String> > compare, ArrayList< ArrayList<String> > master)
    {
        float percent = 0.0f;
        
        for( ArrayList<String> sentence : compare)
        {
            for( String word : sentence)
            {
                if( compareMap.containsKey(word) == true)
                {
                    int total = compareMap.get(word);
                    total++;
                    compareMap.put(word, total);
                }
                else
                {
                    compareMap.put(word, 1);
                }
            }
        }
        
        for( ArrayList<String> sentence : master)
        {
            for( String word : sentence)
            {
                if( masterMap.containsKey(word) == true)
                {
                    int total = masterMap.get(word);
                    total++;
                    masterMap.put(word, total);
                }
                else
                {
                    masterMap.put(word, 1);
                }
            }
        }
        
        /*
            Compare maps
        */
        
        
        return percent;
    }
}
