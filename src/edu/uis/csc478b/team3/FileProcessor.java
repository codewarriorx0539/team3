
package edu.uis.csc478b.team3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

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
    
    final private String COMMA = ",";
    
    private boolean commonFilter;
    private HashSet<String> commonWords;
    
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
    
    public void setCommonWords( HashSet<String> commonWords)
    {
        this.commonWords = commonWords;
    }
    
    public String fileAsAString(String file) throws IOException
    {
        return new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
    }
    
    public String removePuncuation( String text )
    {
        return text.replaceAll( REGEX_NON_ALPHANUMERIC_SYNTAX, "" );
    }
    
    public HashSet<String> readCsvFile( String file ) throws IOException
    {
        HashSet<String> set = new HashSet<>();

        String text = fileAsAString( file );
         
        String [] words = text.toLowerCase().split(COMMA);
        
        for(String word : words)
        {
            set.add( word.trim() );
        }
        
        return set;
    }
    
    
    public int getSentences( String text, ArrayList<String> listOfSentences) throws IOException
    {
        if(listOfSentences == null)
        {
            throw new NullPointerException();
        }
        
        int totalSentences = 0;
        String firstPass = text.toLowerCase();
        String [] sentences = firstPass.split( REGEX_PERIOD );
        
        for(String s : sentences)
        {
            String formatedSentence = s.replaceAll( REGEX_NON_ALPHANUMERIC_SYNTAX, "").trim();
            String [] words = formatedSentence.split( REGEX_WHITESPACE );
            String sentence = "";
            
            totalSentences++;
            
            if(commonFilter)
            {
                for(String word : words)
                {
                    if(commonWords.contains(word) == false)
                    {
                       sentence = sentence + word + " ";
                    }
                }
            }
            else
            {
                for(String word : words)
                {
                    sentence = sentence + word + " ";
                }
            }
            
            listOfSentences.add( sentence );
        }
        
        return totalSentences;
    }
    
    
    public int getWordsOfSentences( String text, ArrayList< ArrayList<String> >  listOfWordsOfSentences) throws IOException
    {
        if(listOfWordsOfSentences == null)
        {
            throw new NullPointerException();
        }
        
        int totalWords = 0;
        String firstPass = text.toLowerCase();
        String [] sentences = firstPass.split( REGEX_PERIOD );
        
        for(String s : sentences)
        {
            String [] words = s.replaceAll( REGEX_NON_ALPHANUMERIC_SYNTAX, "").trim().split( REGEX_WHITESPACE );
            ArrayList<String> wordsInSentence = new ArrayList<String>();
            totalWords = totalWords + words.length;
            
            if(commonFilter)
            {
                for(String word : words)
                {
                    if(commonWords.contains(word) == false)
                    {
                        wordsInSentence.add(word);
                    }
                }
            }
            else
            {
                for(String word : words)
                {
                    wordsInSentence.add(word);
                }
            }
            
            listOfWordsOfSentences.add( wordsInSentence );
        }
        
        return totalWords;
    }
}
