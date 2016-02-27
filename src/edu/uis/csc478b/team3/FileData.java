
package edu.uis.csc478b.team3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * <p>
 * <h3>Class:</h3> FileProcessor
 * <h3>Project:</h3> Plagiarism
 * <h3>Description:</h3>
 * FileProcessor opens master, suspect, or common words files and transfers the data from disk to memory. </br>
 * The data from master and suspect files go through an initial pre filter.</br>
 * </p>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Programmer: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class FileData 
{
    final private String REGEX_PERIOD = "\\.";
    final private String REGEX_NON_ALPHANUMERIC_SYNTAX = "[^a-zA-Z\\d\\s]";
    final private String REGEX_WHITESPACE = "\\s";
    
    final private String fileName;
    final private HashSet<String> commonWords;

    final private ArrayList< String > sentences;
    final private ArrayList< String > words;
    
    
    /**
     * Once allocated all processing transpires and we have the raw data (words and sentences) in memory.
     * Processes the data and filters non essential punctuation and makes all letters lower case to simplify filtering.
     * Optionally a common words file to provide an additional filter on input.
     * 
     * @param fileName
     * @param commonWords
     * @throws IOException 
     */
    public FileData( String fileName, HashSet<String> commonWords) throws IOException
    {
        this.fileName = fileName;
        this.commonWords = commonWords;
        
        sentences = new ArrayList<>();
        words = new ArrayList<>();
        
        boolean commonFilter = false;
        
        if(this.commonWords != null)
        {
            commonFilter = true;
        }
        
        String text = new String( Files.readAllBytes( Paths.get(this.fileName) ), StandardCharsets.UTF_8);
        
        String firstPass = text.toLowerCase();
        String [] textSentences = firstPass.split( REGEX_PERIOD );
        
        for(String s : textSentences)
        {
            String formatedSentence = s.replaceAll( REGEX_NON_ALPHANUMERIC_SYNTAX, "").trim();
            String [] textWords = formatedSentence.split( REGEX_WHITESPACE );
            String sentence = "";
            
            if(commonFilter)
            {
                for(String word : textWords)
                {
                    if(commonWords.contains(word) == false)
                    {
                       sentence = sentence + word + " ";  
                       words.add(word);
                    }  
                }
            }
            else
            {
                for(String word : textWords)
                {
                    sentence = sentence + word + " ";
                    words.add(word);
                }
            }
            
            sentences.add( sentence );
        }
        
    }
    
    public ArrayList< String > getSentences()
    {
        return sentences;
    }
      
    public ArrayList< String > getWords()
    {
        return words;
    }
    
}
