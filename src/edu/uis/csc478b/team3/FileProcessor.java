
package edu.uis.csc478b.team3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parse a file and return the data as a list of sentences which are a list of words
 * 
 * @author Jake
 */
public class FileProcessor 
{
    final private String REGEX_PERIOD = "\\.";
    final private String REGEX_NON_ALPHANUMERIC_SYNTAX = "[^a-zA-Z\\d\\s]";
    final private String REGEX_WHITESPACE = "\\s";
    
    public FileProcessor()
    {
       
    }
    
    /**
     * 
     * @param fileAsAString
     * @return 
     */
    public ArrayList< ArrayList<String> > getSentences( String fileAsAString )
    {
        ArrayList< ArrayList<String> > listOfSentences = new ArrayList<>();
        
        String firstPass = fileAsAString.toLowerCase();
        String [] sentences = firstPass.split( REGEX_PERIOD );
        
        for(String s : sentences)
        {
            String [] words = s.replaceAll( REGEX_NON_ALPHANUMERIC_SYNTAX, "").trim().split( REGEX_WHITESPACE );
            ArrayList<String> wordsInSentence = new ArrayList<String>();
            
            wordsInSentence.addAll( Arrays.asList(words) );
            listOfSentences.add( wordsInSentence );
        }
        
        return listOfSentences;
    }
}
