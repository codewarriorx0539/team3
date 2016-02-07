
package edu.uis.csc478b.team3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    
    boolean commonFilter;
    HashMap<String,Integer> commonWords;
    
    public FileProcessor()
    {
       
    }
    
    public boolean getCommonFilter() 
    {
        return commonFilter;
    }

    public void setCommonFilter(boolean commonFilter) 
    {
        this.commonFilter = commonFilter;
    }
    
    public void setCommonWords( HashMap<String,Integer> commonWords)
    {
        this.commonWords = commonWords;
    }
    
    /**
     * 
     * @param fileAsAString
     * @return 
     */
    public ArrayList<String> getSentences( String fileAsAString )
    {
        ArrayList<String> listOfSentences = new ArrayList<>();
        
        String firstPass = fileAsAString.toLowerCase();
        String [] sentences = firstPass.split( REGEX_PERIOD );
        
        for(String s : sentences)
        {
            String formatedSentence = s.replaceAll( REGEX_NON_ALPHANUMERIC_SYNTAX, "").trim();
            String [] words = formatedSentence.split( REGEX_WHITESPACE );
            for(String word : words)
            {
                if(commonWords.containsKey(word) == false)
                {
                   formatedSentence = formatedSentence.replace(word, "");
                }
            }
            listOfSentences.add( formatedSentence );
        }
        
        return listOfSentences;
    }
    
    /**
     * 
     * @param fileAsAString
     * @return 
     */
    public ArrayList< ArrayList<String> > getWordsOfSentences( String fileAsAString )
    {
        ArrayList< ArrayList<String> > listOfWordsOfSentences = new ArrayList<>();
        
        String firstPass = fileAsAString.toLowerCase();
        String [] sentences = firstPass.split( REGEX_PERIOD );
        
        for(String s : sentences)
        {
            String [] words = s.replaceAll( REGEX_NON_ALPHANUMERIC_SYNTAX, "").trim().split( REGEX_WHITESPACE );
            ArrayList<String> wordsInSentence = new ArrayList<String>();
            
            for(String word : words)
            {
                if(commonWords.containsKey(word) == false)
                {
                    wordsInSentence.add(word);
                }
            }
            
            listOfWordsOfSentences.add( wordsInSentence );
        }
        
        return listOfWordsOfSentences;
    }
}
