
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

public class ConfigSentenceSimilarity extends Config
{
    /// Test sentences N to the left and right.
    int SENTENCE_SIMILARITY_RANGE;
    float SENTENCE_SIMILARITY_THRESHOLD;
    float TOTAL_SENTENCE_THRESHOLD;
    int CONSECUTIVE_SENTENCES;
    
    ConfigEditDistance configEditDistance; 
    
    public ConfigSentenceSimilarity()
    {
        SENTENCE_SIMILARITY_RANGE = 3;  // 3 behind 3 ahead
        SENTENCE_SIMILARITY_THRESHOLD = .70f; // if sentece is 85% 
        TOTAL_SENTENCE_THRESHOLD = .30f; // If 80% of sentences in document are similar
        CONSECUTIVE_SENTENCES = -1;     // (-1 ignore blocks as a trigger) or (num consecutive or total sentence that are similar)
        
        configEditDistance = new ConfigEditDistance();
    }

    public ConfigEditDistance getConfigEditDistance() {
        return configEditDistance;
    }

    @XmlElement
    public void setConfigEditDistance(ConfigEditDistance configEditDistance) {
        this.configEditDistance = configEditDistance;
    }

    public float getTOTAL_SENTENCE_THRESHOLD() {
        return TOTAL_SENTENCE_THRESHOLD;
    }

    @XmlElement
    public void setTOTAL_SENTENCE_THRESHOLD(float TOTAL_SENTENCE_THRESHOLD) {
        this.TOTAL_SENTENCE_THRESHOLD = TOTAL_SENTENCE_THRESHOLD;
    }

    public int getCONSECUTIVE_SENTENCES() 
    {
        return CONSECUTIVE_SENTENCES;
    }

    @XmlElement
    public void setCONSECUTIVE_SENTENCES(int CONSECUTIVE_SENTENCES) 
    {
        this.CONSECUTIVE_SENTENCES = CONSECUTIVE_SENTENCES;
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

        setup = "SENTENCE_SIMILARITY_RANGE: " + SENTENCE_SIMILARITY_RANGE + System.lineSeparator();
        setup = setup + "SENTENCE_SIMILARITY_THRESHOLD: " + SENTENCE_SIMILARITY_THRESHOLD + System.lineSeparator();
        setup = setup + "TOTAL_SENTENCE_THRESHOLD: " + TOTAL_SENTENCE_THRESHOLD + System.lineSeparator();
        setup = setup + "CONSECUTIVE_SENTENCES: " + CONSECUTIVE_SENTENCES + System.lineSeparator();

        setup = setup + configEditDistance.getConfigSetup();
        
        return setup;
    }
}
