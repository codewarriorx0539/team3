
package edu.uis.csc478b.team3;

import javax.xml.bind.annotation.XmlElement;

public class ConfigDocumentSimilarity 
{
    /// Whats is the threshold if we edit distance both documents
    float DOCUMENT_SIMILARITY_THRESHOLD; 
    
    float INSERT_COST;        
    float DELETION_COST;      
    float SUBSTITUTION_COST; 

    public float getDOCUMENT_SIMILARITY_THRESHOLD() 
    {
        return DOCUMENT_SIMILARITY_THRESHOLD;
    }

    @XmlElement
    public void setDOCUMENT_SIMILARITY_THRESHOLD(float DOCUMENT_SIMILARITY_THRESHOLD) 
    {
        this.DOCUMENT_SIMILARITY_THRESHOLD = DOCUMENT_SIMILARITY_THRESHOLD;
    }

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
}
