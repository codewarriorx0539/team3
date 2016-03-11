
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

/**
 * Configuration for sentence similarity classifier.
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public class ConfigSentenceSimilarity extends Config
{
    int sentenceSimilarityRange;
    float sentenceSimilarityThreshold;
    float totalSentenceThreshold;
    int consecutiveSentences;
    
    ConfigEditDistance configEditDistance; 
    
    final protected String RANGE = "sentenceSimilarityRange: ";
    final protected String SENTENCE_THRESHOLD = "sentenceSimilarityThreshold: ";
    final protected String TOTAL_THRESHOLD =  "totalSentenceThreshold: ";
    final protected String CONSECUTIVE = "consecutiveSentences: ";
    
    /**
     * Default values
     */
    public ConfigSentenceSimilarity()
    {
        sentenceSimilarityRange = 3;        // 3 behind 3 ahead
        sentenceSimilarityThreshold = .70f; // if sentece is 70% 
        totalSentenceThreshold = .30f;      // If 30% of sentences in document are similar
        consecutiveSentences = -1;          // (-1 ignore blocks as a trigger) or (num consecutive or total sentence that are similar)
        
        configEditDistance = new ConfigEditDistance();
    }
    
    /**
     * Copy constructor
     * 
     * @param config 
     * @throws java.lang.Exception 
     */
    public ConfigSentenceSimilarity( ConfigSentenceSimilarity config) throws Exception
    {
        this.sentenceSimilarityRange = config.getSentenceSimilarityRange();            
        this.sentenceSimilarityThreshold = config.getSentenceSimilarityThreshold();     
        this.totalSentenceThreshold = config.getSentenceSimilarityThreshold();               
        this.consecutiveSentences = config.getConsecutiveSentences();                  
        
        this.configEditDistance = new ConfigEditDistance(config.getConfigEditDistance());
        
        // BOUNDS CHECK
        if( sentenceSimilarityRange == 0        ||
            sentenceSimilarityThreshold < 0     || 
            sentenceSimilarityThreshold > 1.0   || 
            totalSentenceThreshold < 0          || 
            totalSentenceThreshold > 1.0        ||
            consecutiveSentences < -1           ||
            consecutiveSentences == 0)
        {
            throw new Exception("ConfigSentenceSimilarity::ConfigSentenceSimilarity value out of bounds");
        }
    }
    
    /**
     * Set individual values for sentence similarity.
     * 
     * @param sentenceSimilarityRange
     * @param sentenceSimilarityThreshold
     * @param totalSentenceThreshold
     * @param consecutiveSentences
     * @param configEditDistance 
     * @throws java.lang.Exception 
     */
    public ConfigSentenceSimilarity(    int sentenceSimilarityRange, 
                                        float sentenceSimilarityThreshold, 
                                        float totalSentenceThreshold, 
                                        int consecutiveSentences, 
                                        ConfigEditDistance configEditDistance) throws Exception
    {
        this.sentenceSimilarityRange = sentenceSimilarityRange;             
        this.sentenceSimilarityThreshold = sentenceSimilarityThreshold;      
        this.totalSentenceThreshold = totalSentenceThreshold;               
        this.consecutiveSentences = consecutiveSentences;                   
        
        this.configEditDistance = new ConfigEditDistance(configEditDistance);
        
        // BOUNDS CHECK
        if( sentenceSimilarityRange == 0        ||
            sentenceSimilarityThreshold < 0     || 
            sentenceSimilarityThreshold > 1.0   || 
            totalSentenceThreshold < 0          || 
            totalSentenceThreshold > 1.0        ||
            consecutiveSentences < -1           ||
            consecutiveSentences == 0)
        {
            throw new Exception("ConfigSentenceSimilarity::ConfigSentenceSimilarity value out of bounds");
        }
    }
    
    public ConfigEditDistance getConfigEditDistance() 
    {
        return configEditDistance;
    }

    @XmlElement
    public void setConfigEditDistance(ConfigEditDistance configEditDistance) 
    {
        this.configEditDistance = configEditDistance;
    }

    public float getTotalSentenceThreshold() 
    {
        return totalSentenceThreshold;
    }

    @XmlElement
    public void setTotalSentenceThreshold(float totalSentenceThreshold) 
    {
        this.totalSentenceThreshold = totalSentenceThreshold;
    }

    public int getConsecutiveSentences() 
    {
        return consecutiveSentences;
    }

    @XmlElement
    public void setConsecutiveSentences(int consecutiveSentences) 
    {
        this.consecutiveSentences = consecutiveSentences;
    }

    public int getSentenceSimilarityRange() 
    {
        return sentenceSimilarityRange;
    }

    @XmlElement
    public void setSentenceSimilarityRange(int sentenceSimilarityRange) 
    {
        this.sentenceSimilarityRange = sentenceSimilarityRange;
    }

    public float getSentenceSimilarityThreshold() 
    {
        return sentenceSimilarityThreshold;
    }

    @XmlElement
    public void setSentenceSimilarityThreshold(float sentenceSimilarityThreshold) 
    {
        this.sentenceSimilarityThreshold = sentenceSimilarityThreshold;
    }
    
    @Override
    public String getConfigSetup() 
    {
        String setup = TAB + RANGE + sentenceSimilarityRange + System.lineSeparator();
        setup = setup + TAB + SENTENCE_THRESHOLD + sentenceSimilarityThreshold + System.lineSeparator();
        setup = setup + TAB + TOTAL_THRESHOLD + totalSentenceThreshold + System.lineSeparator();
        setup = setup + TAB +  CONSECUTIVE + consecutiveSentences + System.lineSeparator();

        setup = setup + configEditDistance.getConfigSetup();
        
        return setup;
    }
}
