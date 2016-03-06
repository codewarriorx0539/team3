
package edu.uis.csc478b.team3.config;

import edu.uis.csc478b.team3.filters.PlagiarismFilter;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * <h3>Class:</h3> PlagiarismTest <br>
 * <h3>Project:</h3> Plagiarism <br>
 * <h3>Description:</h3> <br>
 * PlagiarismTest contains all the information to test a set of files against each other.<br>
 * 
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
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
     *
     */
    public PlagiarismTest()
    {        
        commonWordsFile = "";
        
        sentenceFilters = new ArrayList<  > ();
        wordFilters = new ArrayList<  > ();
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getFiles() 
    {
        return files;
    }

    /**
     *
     * @param files
     */
    @XmlElement
    public void setFiles(ArrayList<String> files) 
    {
        this.files = files;
    }
    
    /**
     *
     * @return
     */
    public String getCommonWordsFile()
    {
        return commonWordsFile;
    }

    /**
     *
     * @param commonWordsFile
     */
    @XmlElement
    public void setCommonWordsFile(String commonWordsFile) 
    {
        this.commonWordsFile = commonWordsFile;
    }

    /**
     *
     * @return
     */
    public ArrayList<PlagiarismFilter> getSentenceFilters() 
    {
        return sentenceFilters;
    }

    /**
     *
     * @param sentenceFilters
     */
    @XmlElement
    public void setSentenceFilters(ArrayList<PlagiarismFilter> sentenceFilters) 
    {
        this.sentenceFilters = sentenceFilters;
    }

    /**
     *
     * @return
     */
    public ArrayList<PlagiarismFilter> getWordFilters() 
    {
        return wordFilters;
    }

    /**
     *
     * @param wordFilters
     */
    @XmlElement
    public void setWordFilters(ArrayList<PlagiarismFilter> wordFilters) 
    {
        this.wordFilters = wordFilters;
    }
 
}
