
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

public class ConfigWordFrequency extends Config
{
    /// Are the frequencies of uncommon words similar
    float FREQUENCY_DIFFERENCE_THRESHOLD;
    
    float FREQUENCY_LOWER_BOUND;
    float FREQUENCY_UPPER_BOUND;
    
    public ConfigWordFrequency()
    {
        FREQUENCY_DIFFERENCE_THRESHOLD  = .7f;
        
        FREQUENCY_UPPER_BOUND  = 3.0f;
        FREQUENCY_LOWER_BOUND  = .3f;
    }

    public float getFREQUENCY_LOWER_BOUND() 
    {
        return FREQUENCY_LOWER_BOUND;
    }

    @XmlElement
    public void setFREQUENCY_LOWER_BOUND(float FREQUENCY_LOWER_BOUND) 
    {
        this.FREQUENCY_LOWER_BOUND = FREQUENCY_LOWER_BOUND;
    }

    public float getFREQUENCY_UPPER_BOUND() 
    {
        return FREQUENCY_UPPER_BOUND;
    }

    @XmlElement
    public void setFREQUENCY_UPPER_BOUND(float FREQUENCY_UPPER_BOUND)
    {
        this.FREQUENCY_UPPER_BOUND = FREQUENCY_UPPER_BOUND;
    }

    public float getFREQUENCY_DIFFERENCE_THRESHOLD() 
    {
        return FREQUENCY_DIFFERENCE_THRESHOLD;
    }

    @XmlElement
    public void setFREQUENCY_DIFFERENCE_THRESHOLD(float FREQUENCY_DIFFERENCE_THRESHOLD) 
    {
        this.FREQUENCY_DIFFERENCE_THRESHOLD = FREQUENCY_DIFFERENCE_THRESHOLD;
    }
    
    @Override
    public String getConfigSetup() 
    {
        String setup;
        
        setup = "FREQUENCY_UPPER_BOUND: " + FREQUENCY_UPPER_BOUND + System.lineSeparator();
        setup = setup +  "FREQUENCY_LOWER_BOUND: " + FREQUENCY_LOWER_BOUND + System.lineSeparator();
        setup = setup + "FREQUENCY_DIFFERENCE_THRESHOLD: " + FREQUENCY_DIFFERENCE_THRESHOLD + System.lineSeparator();
        
        return setup;
    }
}
