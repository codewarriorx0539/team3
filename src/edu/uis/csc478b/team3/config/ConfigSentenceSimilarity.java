
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

public class ConfigSentenceSimilarity extends Config
{
    /// Test sentences N to the left and right.
    int SENTENCE_SIMILARITY_RANGE;
    float SENTENCE_SIMILARITY_THRESHOLD;
    int TOTAL_SIMILAR_SENTENCES;
    
    float INSERT_COST;        
    float DELETION_COST;      
    float SUBSTITUTION_COST; 
    
    public ConfigSentenceSimilarity()
    {
        SENTENCE_SIMILARITY_RANGE = 3;
        SENTENCE_SIMILARITY_THRESHOLD = 10;
        TOTAL_SIMILAR_SENTENCES = 3;
                
        INSERT_COST = 1.0f;        
        DELETION_COST = 1.0f;    
        SUBSTITUTION_COST = 1.5f;
    }

    public int getTOTAL_SIMILAR_SENTENCES() 
    {
        return TOTAL_SIMILAR_SENTENCES;
    }

    @XmlElement
    public void setTOTAL_SIMILAR_SENTENCES(int TOTAL_SIMILAR_SENTENCES) 
    {
        this.TOTAL_SIMILAR_SENTENCES = TOTAL_SIMILAR_SENTENCES;
    }

    public int getSENTENCE_SIMILARITY_RANGE() 
    {
        return SENTENCE_SIMILARITY_RANGE;
    }

    @XmlElement
    public void setSENTENCE_SIMILARITY_RANGE(int SENTENCE_SIMILARITY_RANGE) 
    {
        this.SENTENCE_SIMILARITY_RANGE = SENTENCE_SIMILARITY_RANGE;
    }

    public float getSENTENCE_SIMILARITY_THRESHOLD() 
    {
        return SENTENCE_SIMILARITY_THRESHOLD;
    }

    @XmlElement
    public void setSENTENCE_SIMILARITY_THRESHOLD(float SENTENCE_SIMILARITY_THRESHOLD) 
    {
        this.SENTENCE_SIMILARITY_THRESHOLD = SENTENCE_SIMILARITY_THRESHOLD;
    }
    
    public float getINSERT_COST() 
    {
        return INSERT_COST;
    }

    @XmlElement
    public void setINSERT_COST(float INSERT_COST) 
    {
        this.INSERT_COST = INSERT_COST;
    }

    public float getDELETION_COST() 
    {
        return DELETION_COST;
    }

    @XmlElement
    public void setDELETION_COST(float DELETION_COST) 
    {
        this.DELETION_COST = DELETION_COST;
    }

    public float getSUBSTITUTION_COST() 
    {
        return SUBSTITUTION_COST;
    }

    @XmlElement
    public void setSUBSTITUTION_COST(float SUBSTITUTION_COST) 
    {
        this.SUBSTITUTION_COST = SUBSTITUTION_COST;
    }
    
    @Override
    public String getConfigSetup() 
    {
        String setup;
        
        setup = "SENTENCE_SIMILARITY_RANGE: " + SENTENCE_SIMILARITY_RANGE + System.lineSeparator();
        setup = setup + "SENTENCE_SIMILARITY_THRESHOLD: " + SENTENCE_SIMILARITY_THRESHOLD + System.lineSeparator();
        
        setup = setup + "INSERT_COST: " + INSERT_COST + System.lineSeparator();
        setup = setup + "DELETION_COST: " + DELETION_COST + System.lineSeparator();
        setup = setup + "SUBSTITUTION_COST: " + SUBSTITUTION_COST + System.lineSeparator();
        
        return setup;
    }
}
