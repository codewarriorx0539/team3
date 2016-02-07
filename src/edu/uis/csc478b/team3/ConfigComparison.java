/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uis.csc478b.team3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jake
 */
public class ConfigComparison 
{
    String suspectFile;
    String masterFile;
    String commonWordsFile;
    boolean filterCommonWords;
    
    float INSERT_COST;        
    float DELETION_COST;      
    float SUBSTITUTION_COST; 

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
