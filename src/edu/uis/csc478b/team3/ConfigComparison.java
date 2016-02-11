
package edu.uis.csc478b.team3;

import javax.xml.bind.annotation.XmlElement;

public class ConfigComparison 
{
    String suspectFile;         // Relative Path
    String masterFile;          // Relative Path
    String commonWordsFile;     // Relative Path

    boolean filterCommonWords;
    

    /*
        TEST CLUMPS. PARTS OF DOCUEMNTS MIGHT BE CUT AND PASTES BUT WE WANT TO AVOID FALSE POSITVE CITATION
    
        CLUMPS CONSIST OF % OF DOCUMENT. IF THESE BLOCKS ARE PREDOMINANT IN CLUMPS WE HAVE A CUT AND PASTER.
        POSITIONALLY ALIGN CLUMPS SINCE PLAGIARSM TRYS TO FOLLOW SAME FLOW
    
        ALSO LOOK FOR PEPPER PLAGIARIZERS trigger no trigger trigger no trigger
    
    */
    
    
    
    /// How many different words can the documents differ by
    int DIFFERENCE_THRESHOLD;
    
    /// Are the frequencies of uncommon words similar
    int FREQUENCY_DIFFERENCE_THRESHOLD;
    
    /// Whats is the threshold if we edit distance both documents
    float DOCUMENT_SIMILARITY_THRESHOLD; 
    
    /// Test sentences N to the left and right.
    int SENTENCE_SIMILARITY_RANGE;
    float SENTENCE_SIMILARITY_THRESHOLD;
            
    float INSERT_COST;        
    float DELETION_COST;      
    float SUBSTITUTION_COST; 

    
    
    public float getINSERT_COST() 
    {
        return INSERT_COST;
    }

    @XmlElement
    public void setINSERT_COST(float INSERT_COST) 
    {
        this.INSERT_COST = INSERT_COST;
    }

    public float getDELETION_COST() 
    {
        return DELETION_COST;
    }

    @XmlElement
    public void setDELETION_COST(float DELETION_COST) 
    {
        this.DELETION_COST = DELETION_COST;
    }

    public float getSUBSTITUTION_COST() 
    {
        return SUBSTITUTION_COST;
    }

    @XmlElement
    public void setSUBSTITUTION_COST(float SUBSTITUTION_COST) 
    {
        this.SUBSTITUTION_COST = SUBSTITUTION_COST;
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
