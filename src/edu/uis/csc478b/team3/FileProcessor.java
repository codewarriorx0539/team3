
package edu.uis.csc478b.team3;

import java.util.ArrayList;
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
     * @param listOfSentences
     * @return 
     */
    public int getSentences( String fileAsAString, ArrayList<String> listOfSentences)
    {
        if(listOfSentences == null)
        {
            throw new NullPointerException();
        }
        
        int totalWords = 0;
        String firstPass = fileAsAString.toLowerCase();
        String [] sentences = firstPass.split( REGEX_PERIOD );
        
        for(String s : sentences)
        {
            String formatedSentence = s.replaceAll( REGEX_NON_ALPHANUMERIC_SYNTAX, "").trim();
            String [] words = formatedSentence.split( REGEX_WHITESPACE );
            
            totalWords = totalWords + words.length;
            
            for(String word : words)
            {
                if(commonWords.containsKey(word) == false)
                {
                   formatedSentence = formatedSentence.replace(word, "");
                }
            }
            listOfSentences.add( formatedSentence );
        }
        
        return totalWords;
    }
    
    /**
     * 
     * @param fileAsAString
     * @param listOfWordsOfSentences
     * @return 
     */
    public int getWordsOfSentences( String fileAsAString, ArrayList< ArrayList<String> >  listOfWordsOfSentences)
    {
        if(listOfWordsOfSentences == null)
        {
            throw new NullPointerException();
        }
        
        int totalWords = 0;
        String firstPass = fileAsAString.toLowerCase();
        String [] sentences = firstPass.split( REGEX_PERIOD );
        
        for(String s : sentences)
        {
            String [] words = s.replaceAll( REGEX_NON_ALPHANUMERIC_SYNTAX, "").trim().split( REGEX_WHITESPACE );
            ArrayList<String> wordsInSentence = new ArrayList<String>();
            totalWords = totalWords + words.length;
            
            for(String word : words)
            {
                if(commonWords.containsKey(word) == false)
                {
                    wordsInSentence.add(word);
                }
            }
            
            listOfWordsOfSentences.add( wordsInSentence );
        }
        
        return totalWords;
    }
}
