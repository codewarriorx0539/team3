
package edu.uis.csc478b.team3;

import java.util.ArrayList;

/**
 * Test similarity
 * 
 * @author Jake
 */
public class SentenceSimilarity 
{
    private float threshold;
    EditDistance distance;

    public SentenceSimilarity()
    {
        distance = new EditDistance();
    }
    
    public float getThreshold() 
    {
        return threshold;
    }

    public void setThreshold(float threshold) 
    {
        this.threshold = threshold;
    }
    
    public boolean similarity(ArrayList<String> suspectSentences, ArrayList<String> masterSentences)
    {
       
        return true;
    }
}
