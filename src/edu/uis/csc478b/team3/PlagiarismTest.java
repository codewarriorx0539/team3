
package edu.uis.csc478b.team3;

import javax.xml.bind.annotation.XmlElement;

public class PlagiarismTest 
{
    String suspectFile;         // Relative Path
    String masterFile;          // Relative Path
    String commonWordsFile;     // Relative Path

    boolean filterCommonWords;
    
    ConfigDocumentSimilarity configDocumentSimilarity;
    ConfigSentenceSimilarity configSentenceSimilarity;
    ConfigWordFrequency configWordFrequency;

    /// How many different words can the documents differ by
    int DIFFERENCE_THRESHOLD;
    
    /*
        TEST CLUMPS. PARTS OF DOCUEMNTS MIGHT BE CUT AND PASTES BUT WE WANT TO AVOID FALSE POSITVE CITATION
    
        CLUMPS CONSIST OF % OF DOCUMENT. IF THESE BLOCKS ARE PREDOMINANT IN CLUMPS WE HAVE A CUT AND PASTER.
        POSITIONALLY ALIGN CLUMPS SINCE PLAGIARSM TRYS TO FOLLOW SAME FLOW
    
        ALSO LOOK FOR PEPPER PLAGIARIZERS trigger no trigger trigger no trigger
    
    */

    public ConfigDocumentSimilarity getConfigDocumentSimilarity() 
    {
        return configDocumentSimilarity;
    }

    @XmlElement
    public void setConfigDocumentSimilarity(ConfigDocumentSimilarity configDocumentSimilarity) 
    {
        this.configDocumentSimilarity = configDocumentSimilarity;
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

    public int getDIFFERENCE_THRESHOLD() 
    {
        return DIFFERENCE_THRESHOLD;
    }

    @XmlElement
    public void setDIFFERENCE_THRESHOLD(int DIFFERENCE_THRESHOLD) 
    {
        this.DIFFERENCE_THRESHOLD = DIFFERENCE_THRESHOLD;
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
