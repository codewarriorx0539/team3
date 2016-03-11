
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

/**
 * Configuration for the word frequency classifier.
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 * 
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
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
        cosineSimilarityThreshold  = .7f;
        frequencyUpperBound  = 3.0f;
        frequencyLowerBound  = .3f;
    }
    
    /**
     * Copy constructor
     * 
     * @param config 
     * @throws java.lang.Exception 
     */
    public ConfigWordFrequency(ConfigWordFrequency config) throws Exception
    {
        cosineSimilarityThreshold = config.getCosineSimilarityThreshold();   
        frequencyLowerBound = config.getFrequencyLowerBound();
        frequencyUpperBound = config.getFrequencyUpperBound();
        
        // BOUNDS CHECK
        if(frequencyLowerBound < 0 || cosineSimilarityThreshold < -1 || cosineSimilarityThreshold > 1)
        {
            throw new Exception("ConfigWordFrequency::ConfigWordFrequency value out of bounds");
        }
    }

    public float getFrequencyLowerBound() 
    {
        return frequencyLowerBound;
    }

    @XmlElement
    public void setFrequencyLowerBound(float frequencyLowerBound) 
    {
        this.frequencyLowerBound = frequencyLowerBound;
    }

    public float getFrequencyUpperBound() 
    {
        return frequencyUpperBound;
    }

    @XmlElement
    public void setFrequencyUpperBound(float frequencyUpperBound)
    {
        this.frequencyUpperBound = frequencyUpperBound;
    }

    public float getCosineSimilarityThreshold() 
    {
        return cosineSimilarityThreshold;
    }

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
