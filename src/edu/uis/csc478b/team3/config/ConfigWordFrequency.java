
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

public class ConfigWordFrequency 
{
    /// Are the frequencies of uncommon words similar
    int FREQUENCY_DIFFERENCE_THRESHOLD;

    public int getFREQUENCY_DIFFERENCE_THRESHOLD() 
    {
        return FREQUENCY_DIFFERENCE_THRESHOLD;
    }

    @XmlElement
    public void setFREQUENCY_DIFFERENCE_THRESHOLD(int FREQUENCY_DIFFERENCE_THRESHOLD) 
    {
        this.FREQUENCY_DIFFERENCE_THRESHOLD = FREQUENCY_DIFFERENCE_THRESHOLD;
    }
    
    
}
