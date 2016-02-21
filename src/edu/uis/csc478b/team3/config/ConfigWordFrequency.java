
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * <p>
 * <h3>Class:</h3> ConfigWordFrequency
 * <h3>Project:</h3> Plagiarism
 * <h3>Description:</h3>
 * Configuration for the word frequency classifier.
 * </p>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Programmer: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class ConfigWordFrequency extends Config
{
    float frequencyDifferenceThreshold;
    
    float frequencyLowerBound;
    float frequencyUpperBound;
    
    public ConfigWordFrequency()
    {
        frequencyDifferenceThreshold  = .7f;
        
        frequencyUpperBound  = 3.0f;
        frequencyLowerBound  = .3f;
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

    public float getFrequencyDifferenceThreshold() 
    {
        return frequencyDifferenceThreshold;
    }

    @XmlElement
    public void setFrequencyDifferenceThreshold(float frequencyDifferenceThreshold) 
    {
        this.frequencyDifferenceThreshold = frequencyDifferenceThreshold;
    }
    
    @Override
    public String getConfigSetup() 
    {
        String setup;
        
        setup = "frequencyUpperBound: " + frequencyUpperBound + System.lineSeparator();
        setup = setup +  "frequencyLowerBound: " + frequencyLowerBound + System.lineSeparator();
        setup = setup + "frequencyDifferenceThreshold: " + frequencyDifferenceThreshold + System.lineSeparator();
        
        return setup;
    }
}
