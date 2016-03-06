
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * 
 * <h3>Class:</h3> ConfigSentenceSimilarity <br>
 * <h3>Project:</h3> Plagiarism <br>
 * <h3>Description:</h3> <br>
 * Configuration for sentence similarity classifier. <br>
 * 
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class ConfigSentenceSimilarity extends Config
{
    int sentenceSimilarityRange;
    float sentenceSimilarityThreshold;
    float totalSentenceThreshold;
    int consecutiveSentences;
    
    ConfigEditDistance configEditDistance; 
    
    /**
     *
     */
    final protected String RANGE = "sentenceSimilarityRange: ";

    /**
     *
     */
    final protected String SENTENCE_THRESHOLD = "sentenceSimilarityThreshold: ";

    /**
     *
     */
    final protected String TOTAL_THRESHOLD =  "totalSentenceThreshold: ";

    /**
     *
     */
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
     */
    public ConfigSentenceSimilarity( ConfigSentenceSimilarity config)
    {
        this.sentenceSimilarityRange = config.getSentenceSimilarityRange();            
        this.sentenceSimilarityThreshold = config.getSentenceSimilarityThreshold();     
        this.totalSentenceThreshold = config.getSentenceSimilarityThreshold();               
        this.consecutiveSentences = config.getConsecutiveSentences();                  
        
        this.configEditDistance = new ConfigEditDistance(config.getConfigEditDistance());
    }
    
    /**
     * Set individual values for sentence similarity.
     * 
     * @param sentenceSimilarityRange
     * @param sentenceSimilarityThreshold
     * @param totalSentenceThreshold
     * @param consecutiveSentences
     * @param configEditDistance 
     */
    public ConfigSentenceSimilarity(    int sentenceSimilarityRange, 
                                        float sentenceSimilarityThreshold, 
                                        float totalSentenceThreshold, 
                                        int consecutiveSentences, 
                                        ConfigEditDistance configEditDistance)
    {
        this.sentenceSimilarityRange = sentenceSimilarityRange;             
        this.sentenceSimilarityThreshold = sentenceSimilarityThreshold;      
        this.totalSentenceThreshold = totalSentenceThreshold;               
        this.consecutiveSentences = consecutiveSentences;                   
        
        this.configEditDistance = new ConfigEditDistance(configEditDistance);
    }
    
    /**
     *
     * @return
     */
    public ConfigEditDistance getConfigEditDistance() 
    {
        return configEditDistance;
    }

    /**
     *
     * @param configEditDistance
     */
    @XmlElement
    public void setConfigEditDistance(ConfigEditDistance configEditDistance) 
    {
        this.configEditDistance = configEditDistance;
    }

    /**
     *
     * @return
     */
    public float getTotalSentenceThreshold() 
    {
        return totalSentenceThreshold;
    }

    /**
     *
     * @param totalSentenceThreshold
     */
    @XmlElement
    public void setTotalSentenceThreshold(float totalSentenceThreshold) 
    {
        this.totalSentenceThreshold = totalSentenceThreshold;
    }

    /**
     *
     * @return
     */
    public int getConsecutiveSentences() 
    {
        return consecutiveSentences;
    }

    /**
     *
     * @param consecutiveSentences
     */
    @XmlElement
    public void setConsecutiveSentences(int consecutiveSentences) 
    {
        this.consecutiveSentences = consecutiveSentences;
    }

    /**
     *
     * @return
     */
    public int getSentenceSimilarityRange() 
    {
        return sentenceSimilarityRange;
    }

    /**
     *
     * @param sentenceSimilarityRange
     */
    @XmlElement
    public void setSentenceSimilarityRange(int sentenceSimilarityRange) 
    {
        this.sentenceSimilarityRange = sentenceSimilarityRange;
    }

    /**
     *
     * @return
     */
    public float getSentenceSimilarityThreshold() 
    {
        return sentenceSimilarityThreshold;
    }

    /**
     *
     * @param sentenceSimilarityThreshold
     */
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
