
package edu.uis.csc478b.team3;

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
    
    public boolean similarity(String compare, String master)
    {
        if( threshold >= distance.getDistance(compare, master))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
