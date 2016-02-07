
package edu.uis.csc478b.team3;

/**
 * 
 * @author Jake
 */
public class WordFrequencyResults 
{
    float masterTotal;
    float compareTotal;
    float similarWords;
    
    /**
     * 
     * @param masterTotal
     * @param compareTotal
     * @param similarWords 
     */
    public WordFrequencyResults(float masterTotal, float compareTotal, float similarWords)
    {
        this.masterTotal = masterTotal;
        this.compareTotal = compareTotal;
        this.similarWords = similarWords;
    }

    public float getMasterTotal() 
    {
        return masterTotal;
    }

    public void setMasterTotal(float masterTotal) 
    {
        this.masterTotal = masterTotal;
    }

    public float getCompareTotal() {
        return compareTotal;
    }

    public void setCompareTotal(float compareTotal) 
    {
        this.compareTotal = compareTotal;
    }

    public float getSimilarWords() 
    {
        return similarWords;
    }

    public void setSimilarWords(float similarWords)
    {
        this.similarWords = similarWords;
    }
    
    
}
