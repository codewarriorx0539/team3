
package edu.uis.csc478b.team3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The class FileData opens a file and pre-filters the data cleaning capitalization, 
 * punctuation, and extra whitespace. FileData holds two arrays of the filtered data. 
 * One array contains the words of the file and the other array holds the sentences 
 * of the file.  
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
    // Used to split the sentences. This can be an issue with acronymns but acceptable per customer
    final private String REGEX_SENTENCE_ENDING = "[\\.\\?!]";
    // Used to remove punctuation
    final private String REGEX_NON_ALPHANUMERIC_SYNTAX = "[^a-zA-Z\\d\\s]";
    // Used to split on whitespace boundaries
    final private String REGEX_WHITESPACE = "\\s";
    // File name of parsed files
    final private String fileName;
    // Optional set of common words that can be used in the filtering process
    final private HashSet<String> commonWords;

    // Data structures that hold the data
    final private ArrayList< String > sentences;
    final private ArrayList< String > words;
    
    
    /**
     * Constructor: Normalizes all input removing trivial noise from the file. 
     * This noise can be capitalization, punctuation, and extra whitespace. After pre-processing 
     * transpires we have the raw data (words and sentences) in memory. Optionally a common 
     * word file can be given to filter out common words to provide additional uniqueness.
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
        
        // Split the string into sentences. 
        String [] textSentences = firstPass.split( REGEX_SENTENCE_ENDING );
        
        // Parse each sentence
        for(String s : textSentences)
        {
            // Remove punctuation commas, apostrophes, dollar sign, dash, etc
            String formatedSentence = s.replaceAll( REGEX_NON_ALPHANUMERIC_SYNTAX, "").trim();
            
            // Split up document up into words (split on whitespace boundaries)
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
     * Get the sentences of the file.
     * 
     * @return List of sentences
     */
    public ArrayList< String > getSentences()
    {
        return sentences;
    }
      
    /**
     * Get the words of the file.
     *
     * @return List of words
     */
    public ArrayList< String > getWords()
    {
        return words;
    }

    /**
     * Get the file name of the processed file
     * 
     * @return File Name 
     */
    public String getFileName() 
    {
        return fileName;
    }
}
