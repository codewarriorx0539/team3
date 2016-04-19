
package edu.uis.csc478b.team3.config;

import edu.uis.csc478b.team3.filters.PlagiarismFilter;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;

/**
 * PlagiarismTest: contains all the information to test a set of files against each other.
 * Req 6.1.0, 6.2.0
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 * 
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public class PlagiarismTest 
{
    // Set of files to test Req 9.0.0, Req 9.1.0
    ArrayList< String > files;
    
    // Path to common words file; can be empty or null - Req 7.1.0, Req 11.0.0, Req 11.1.0, Req 15.1.0, Req 15.2.0
    String commonWordsFile;
    
    // Specific filters - Req 7.3.0, Req 7.4.0, Req 8.2.0, Req 8.3.0, Req 12.0.0, Req 13.0.0
    ArrayList< PlagiarismFilter > sentenceFilters;
    ArrayList< PlagiarismFilter > wordFilters;
    
    /**
     * Constructor: Initialize values
     */
    public PlagiarismTest()
    {        
        commonWordsFile = "";
    }

    public ArrayList<String> getFiles() 
    {
        return files;
    }

    @XmlElement(name = "file")
    public void setFiles(ArrayList<String> files) 
    {
        this.files = files;
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
    
    public ArrayList<PlagiarismFilter> getSentenceFilters() 
    {
        return sentenceFilters;
    }

    @XmlElement
    public void setSentenceFilters(ArrayList<PlagiarismFilter> sentenceFilters) 
    {
        this.sentenceFilters = sentenceFilters;
    }

    public ArrayList<PlagiarismFilter> getWordFilters() 
    {
        return wordFilters;
    }

    @XmlElement
    public void setWordFilters(ArrayList<PlagiarismFilter> wordFilters) 
    {
        this.wordFilters = wordFilters;
    }
 
}
