
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

public class ConfigWordFrequency extends Config
{
    /// Are the frequencies of uncommon words similar
    float FREQUENCY_DIFFERENCE_THRESHOLD;
    
    int DIFFERENCE_THRESHOLD;
    
    public ConfigWordFrequency()
    {
        FREQUENCY_DIFFERENCE_THRESHOLD  = .7f;
        
        DIFFERENCE_THRESHOLD  = 1000;
    }

    public int getDIFFERENCE_THRESHOLD() 
    {
        return DIFFERENCE_THRESHOLD;
    }

    @XmlElement
    public void setDIFFERENCE_THRESHOLD(int DIFFERENCE_THRESHOLD) 
    {
        this.DIFFERENCE_THRESHOLD = DIFFERENCE_THRESHOLD;
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
        
        setup = "DIFFERENCE_THRESHOLD: " + DIFFERENCE_THRESHOLD + System.lineSeparator();
        setup = setup + "FREQUENCY_DIFFERENCE_THRESHOLD: " + FREQUENCY_DIFFERENCE_THRESHOLD + System.lineSeparator();
        
        return setup;
    }
}
