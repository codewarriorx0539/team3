
package edu.uis.csc478b.team3.config;

import edu.uis.csc478b.team3.filters.PlagiarismFilter;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;

/**
 * PlagiarismTest contains all the information to test a set of files against each other.
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
    // Set of files to test
    ArrayList< String > files;
    
    // Path to common words file; can be empty or null
    String commonWordsFile;
    
    ArrayList< PlagiarismFilter > sentenceFilters = new ArrayList<  > ();
    ArrayList< PlagiarismFilter > wordFilters = new ArrayList<  > ();
    
    /**
     * Initialize values
     */
    public PlagiarismTest()
    {        
        commonWordsFile = "";
        
        sentenceFilters = new ArrayList<  > ();
        wordFilters = new ArrayList<  > ();
    }

    public ArrayList<String> getFiles() 
    {
        return files;
    }

    @XmlElement
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
