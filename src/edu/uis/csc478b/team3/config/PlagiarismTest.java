
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * <p>
 * <h3>Class:</h3> PlagiarismTest
 * <h3>Project:</h3> Plagiarism
 * <h3>Description:</h3>
 * PlagiarismTest contains all the information to test a suspect document against a master document
 * </p>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Programmer: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class PlagiarismTest 
{
    String suspectFile;         
    String masterFile;          
    
    String commonWordsFile; 
    boolean filterCommonWords;
    
    ConfigSentenceSimilarity configSentenceSimilarity;
    ConfigWordFrequency configWordFrequency;
    
    public PlagiarismTest()
    {
        suspectFile = "";         
        masterFile = "";         
        commonWordsFile = "";
        filterCommonWords = false;
        
        configSentenceSimilarity = new ConfigSentenceSimilarity();
        configWordFrequency = new ConfigWordFrequency();
    }

    public ConfigSentenceSimilarity getConfigSentenceSimilarity() 
    {
        return configSentenceSimilarity;
    }

    @XmlElement
    public void setConfigSentenceSimilarity(ConfigSentenceSimilarity configSentenceSimilarity) {
        this.configSentenceSimilarity = configSentenceSimilarity;
    }

    public ConfigWordFrequency getConfigWordFrequency() 
    {
        return configWordFrequency;
    }

    @XmlElement
    public void setConfigWordFrequency(ConfigWordFrequency configWordFrequency) 
    {
        this.configWordFrequency = configWordFrequency;
    }
    
    public String getCommonWordsFile()
    {
        return commonWordsFile;
    }

    @XmlElement
    public void setCommonWordsFile(String commonWordsFile) 
    {
        this.commonWordsFile = commonWordsFile;
    }

    public String getSuspectFile() 
    {
        return suspectFile;
    }

    @XmlElement
    public void setSuspectFile(String suspectFile) 
    {
        this.suspectFile = suspectFile;
    }

    public String getMasterFile() 
    {
        return masterFile;
    }

    @XmlElement
    public void setMasterFile(String masterFile) 
    {
        this.masterFile = masterFile;
    }

    public boolean getFilterCommonWords() 
    {
        return filterCommonWords;
    }

    @XmlElement
    public void setFilterCommonWords(boolean filterCommonWords) 
    {
        this.filterCommonWords = filterCommonWords;
    }
    
}
