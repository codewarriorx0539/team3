
package edu.uis.csc478b.team3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * FileData opens a file pre-filters the data cleaning syntax and creates two
 * arrays. One array contains the words of the file and the other array is the sentences of the file.
 * The data from master and suspect files go through an initial pre filter.  
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 *  Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 *  Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
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
     * The constructor that opens the file preprocesses the data and fills it into a set of arrays.
     * All input is normalized to remove trivial noise from the file. This noise can be capitalization,
     * punctuation, and extra whitespace. Once allocated all processing transpires 
     * and we have the raw data (words and sentences) in memory. Optionally a common 
     * words file can be given to filter out common words to provide additional uniqueness.
     * 
     * @param fileName File name of the file to open.
     * @param commonWords optional can be null skip if null
     * @throws IOException 
     */
    public FileData( String fileName, HashSet<String> commonWords) throws IOException
    {
        if(fileName == null)
        {
            throw new NullPointerException("FileData::FileData fileName is null");
        }
        
        this.fileName = fileName;
        this.commonWords = commonWords;
        
        sentences = new ArrayList<>();
        words = new ArrayList<>();
        
        // Read entire file in as a single String
        String text = new String( Files.readAllBytes( Paths.get(this.fileName) ), StandardCharsets.UTF_8);
        
        // Make the String lowercase
        String firstPass = text.toLowerCase();
        
        // Split the string into sentences. This can be an issue with acronymns but acceptable per customer
        String [] textSentences = firstPass.split( REGEX_PERIOD );
        
        // Parse each sentence
        for(String s : textSentences)
        {
            // Remove syntax
            String formatedSentence = s.replaceAll( REGEX_NON_ALPHANUMERIC_SYNTAX, "").trim();
            
            // Split up document up into words
            String [] textWords = formatedSentence.split( REGEX_WHITESPACE );
            String sentence = "";
            
            // If a dictionary of common words was given
            if(this.commonWords != null)
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
    
    /**
     * The sentences of the file pre-processed.
     * 
     * @return List of sentences
     */
    public ArrayList< String > getSentences()
    {
        return sentences;
    }
      
    /**
     * The words of the file pre-processed.
     *
     * @return List of words
     */
    public ArrayList< String > getWords()
    {
        return words;
    }
    
}
