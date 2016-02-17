
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
    
    String fileName;
    String text;
    
    ArrayList< String > sentences;
    ArrayList< String > words;
  
    public FileProcessor(String fileName) throws IOException
    {
       this.fileName = fileName;
       
       sentences = new ArrayList< >();
       words = new ArrayList< >();
       
       readFile();
    }

    public String getFileName() 
    {
        return fileName;
    }

    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getText() 
    {
        return text;
    }

    public void setText(String text) 
    {
        this.text = text;
    }

    public ArrayList<String> getSentences() 
    {
        return sentences;
    }

    public void setSentences(ArrayList<String> sentences) 
    {
        this.sentences = sentences;
    }

    public ArrayList<String> getWords() 
    {
        return words;
    }

    public void setWords(ArrayList<String> words) 
    {
        this.words = words;
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
    
    public int getNumWords()
    {
        return words.size();
    }
    
    public int getNumSentences()
    {
        return sentences.size();
    }
    
    public void readFile() throws IOException
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
    
    public ArrayList<String> readCsvFile( String fileName ) throws IOException
    {
        ArrayList<String> list = new ArrayList<>();

        String csvText = new String( Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
         
        String [] csvWords = csvText.toLowerCase().split(COMMA);
        
        for(String word : csvWords)
        {
            list.add( word.trim() );
        }
        
        return list;
    }
    
    public HashSet<String> readUniqueCsvFile( String file ) throws IOException
    {
        HashSet<String> set = new HashSet<>();

        String csvText = new String( Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
         
        String [] csvWords = csvText.toLowerCase().split(COMMA);
        
        for(String word : csvWords)
        {
            set.add( word.trim() );
        }
        
        return set;
    }
}
