
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

public class FileProcessor 
{
    final private String REGEX_PERIOD = "\\.";
    final private String REGEX_NON_ALPHANUMERIC_SYNTAX = "[^a-zA-Z\\d\\s]";
    final private String REGEX_WHITESPACE = "\\s";
    final private String REGEX_NEWLINE = "\\r\\n";
    
    final private String COMMA = ",";
    
    private final boolean commonFilter;
    private final HashSet<String> commonWords;
    
    private final String fileName;
    private final String commonFile;
    private String text;
    
    private final ArrayList< String > sentences;
    private final ArrayList< String > words;
  
    /**
     * The only constructor. Once allocated all processing transpires and we have the raw data (words and sentences) in memory.
     * 
     * @param fileName
     * @param commonFile
     * @param commonFilter
     * @throws IOException 
     */
    public FileProcessor(String fileName, String commonFile, boolean commonFilter) throws IOException
    {
        sentences = new ArrayList<>();
        words = new ArrayList<>();
        commonWords = new HashSet<>();

        this.fileName = fileName;
        this.commonFilter = commonFilter;
        this.commonFile = commonFile;

        if(this.commonFilter == true)
        {
            readCommonWordFile( this.commonFile );
        }

        readFile();
    }

    /**
     * Processes the data and filters non essential punctuation and makes all letters lower case to simplify filtering.
     * Optionally a common words file to provide an additional filter on input.
     * @throws IOException 
     */
    private void readFile() throws IOException
    {
        text = new String( Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        
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
    
    /**
     * Reads in the common words file in CSV format
     * 
     * @param file
     * @throws IOException 
     */
    private void readCommonWordCsvFile( String file ) throws IOException
    {

        String csvText = new String( Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
         
        String [] csvWords = csvText.toLowerCase().split(COMMA);
        
        for(String word : csvWords)
        {
            commonWords.add( word.trim() );
        }
        
    }
    
    /**
     * Reads in the common words file. Values separated by a newline.
     * 
     * @param file
     * @throws IOException 
     */
    private void readCommonWordFile( String file ) throws IOException
    {
        String commonText = new String( Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
         
        String [] formattedWords = commonText.split( REGEX_NEWLINE );
        
        for(String word : formattedWords)
        {
            commonWords.add( word.trim() );
        }
        
    }
    
    public String getFileName() 
    {
        return fileName;
    }

    public String getText() 
    {
        return text;
    }

    public ArrayList<String> getSentences() 
    {
        return sentences;
    }

    public ArrayList<String> getWords() 
    {
        return words;
    }

    public boolean getCommonFilter() 
    {
        return commonFilter;
    }
    
    public int getNumWords()
    {
        return words.size();
    }
    
    public int getNumSentences()
    {
        return sentences.size();
    }
}
