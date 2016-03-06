
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * <h3>Class:</h3> ConfigWordFrequency <br>
 * <h3>Project:</h3> Plagiarism <br>
 * <h3>Description:</h3> <br>
 * Configuration for the word frequency classifier.<br>
 * 
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class ConfigWordFrequency extends Config
{
    float cosineSimilarityThreshold;
    float frequencyLowerBound;
    float frequencyUpperBound;
    
    final protected String UPPER = "frequencyUpperBound: ";
    final protected String LOWER = "frequencyLowerBound: ";
    final protected String THRESHOLD = "cosineSimilarityThreshold: ";
   
    /**
     * Default values for Word frequency
     */
    public ConfigWordFrequency()
    {
        cosineSimilarityThreshold  = .3f;
        frequencyUpperBound  = 3.0f;
        frequencyLowerBound  = .3f;
    }
    
    /**
     * Copy constructor
     * 
     * @param config 
     */
    public ConfigWordFrequency(ConfigWordFrequency config)
    {
        cosineSimilarityThreshold = config.getCosineSimilarityThreshold();   
        frequencyLowerBound = config.getFrequencyLowerBound();
        frequencyUpperBound = config.getFrequencyUpperBound();
    }

    /**
     *
     * @return
     */
    public float getFrequencyLowerBound() 
    {
        return frequencyLowerBound;
    }

    /**
     *
     * @param frequencyLowerBound
     */
    @XmlElement
    public void setFrequencyLowerBound(float frequencyLowerBound) 
    {
        this.frequencyLowerBound = frequencyLowerBound;
    }

    /**
     *
     * @return
     */
    public float getFrequencyUpperBound() 
    {
        return frequencyUpperBound;
    }

    /**
     *
     * @param frequencyUpperBound
     */
    @XmlElement
    public void setFrequencyUpperBound(float frequencyUpperBound)
    {
        this.frequencyUpperBound = frequencyUpperBound;
    }

    /**
     *
     * @return
     */
    public float getCosineSimilarityThreshold() 
    {
        return cosineSimilarityThreshold;
    }

    /**
     *
     * @param cosineSimilarityThreshold
     */
    @XmlElement
    public void setCosineSimilarityThreshold(float cosineSimilarityThreshold) 
    {
        this.cosineSimilarityThreshold = cosineSimilarityThreshold;
    }
   
    @Override
    public String getConfigSetup() 
    {
        String setup = TAB + UPPER + frequencyUpperBound + System.lineSeparator();
        setup = setup + TAB + LOWER + frequencyLowerBound + System.lineSeparator();
        setup = setup + TAB + THRESHOLD + cosineSimilarityThreshold + System.lineSeparator();
        
        return setup;
    }
}
