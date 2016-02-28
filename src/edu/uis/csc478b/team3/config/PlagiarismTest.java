
package edu.uis.csc478b.team3.config;

import edu.uis.csc478b.team3.filters.PlagiarismFilter;
import java.util.ArrayList;
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
    ArrayList< String > files;
    
    String commonWordsFile;
    
    ArrayList< PlagiarismFilter > sentenceFilters = new ArrayList<  > ();
    ArrayList< PlagiarismFilter > wordFilters = new ArrayList<  > ();
    
    
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

    public void setSentenceFilters(ArrayList<PlagiarismFilter> sentenceFilters) 
    {
        this.sentenceFilters = sentenceFilters;
    }

    public ArrayList<PlagiarismFilter> getWordFilters() 
    {
        return wordFilters;
    }

    public void setWordFilters(ArrayList<PlagiarismFilter> wordFilters) 
    {
        this.wordFilters = wordFilters;
    }
 
}
