
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

public class ConfigSentenceSimilarity extends Config
{
    /// Test sentences N to the left and right.
    int SENTENCE_SIMILARITY_RANGE;
    float SENTENCE_SIMILARITY_THRESHOLD;
    int TOTAL_SIMILAR_SENTENCES;
    
    ConfigEditDistance configEditDistance; 
    
    public ConfigSentenceSimilarity()
    {
        SENTENCE_SIMILARITY_RANGE = 3;
        SENTENCE_SIMILARITY_THRESHOLD = 10;
        TOTAL_SIMILAR_SENTENCES = 3;       
    }

    public ConfigEditDistance getConfigEditDistance() {
        return configEditDistance;
    }

    public void setConfigEditDistance(ConfigEditDistance configEditDistance) {
        this.configEditDistance = configEditDistance;
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
    
    @Override
    public String getConfigSetup() 
    {
        String setup;

        setup = "Sentence Similarity";
        setup = setup + "SENTENCE_SIMILARITY_RANGE: " + SENTENCE_SIMILARITY_RANGE + System.lineSeparator();
        setup = setup + "SENTENCE_SIMILARITY_THRESHOLD: " + SENTENCE_SIMILARITY_THRESHOLD + System.lineSeparator();
        setup = setup + configEditDistance.getConfigSetup();
        
        return setup;
    }
}
