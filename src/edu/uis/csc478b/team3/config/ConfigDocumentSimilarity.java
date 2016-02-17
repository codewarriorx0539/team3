
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

public class ConfigDocumentSimilarity extends Config
{
    ConfigEditDistance configEditDistance;
    int DOCUMENT_SIMILARITY_THRESHOLD;
    
    public ConfigDocumentSimilarity()
    {
        DOCUMENT_SIMILARITY_THRESHOLD = 200;
        configEditDistance = new ConfigEditDistance();
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

    public int getDOCUMENT_SIMILARITY_THRESHOLD() 
    {
        return DOCUMENT_SIMILARITY_THRESHOLD;
    }

    @XmlElement
    public void setDOCUMENT_SIMILARITY_THRESHOLD(int DOCUMENT_SIMILARITY_THRESHOLD) 
    {
        this.DOCUMENT_SIMILARITY_THRESHOLD = DOCUMENT_SIMILARITY_THRESHOLD;
    }

    @Override
    public String getConfigSetup() 
    {
        String setup;
        
        setup = "DOCUMENT_SIMILARITY_THRESHOLD: " + DOCUMENT_SIMILARITY_THRESHOLD + System.lineSeparator(); 
        setup = setup + configEditDistance.getConfigSetup();
        
        return setup;
    }
}
