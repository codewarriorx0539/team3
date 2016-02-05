/*
    This class will parse all the words in a file (will remove all non word characters) and return them as an ArrayList 
 */
package edu.uis.csc478b;

import java.util.ArrayList;
import java.util.Arrays;

public class FileProcessor 
{
    private String fileAsAString;
    
    public FileProcessor(String fileAsAString)
    {
        this.fileAsAString = fileAsAString;
    }
    
    public ArrayList<String> returnWords()
    {
        ArrayList<String> listOfWords = new ArrayList<>();
        String firstPass = fileAsAString.toLowerCase();
        String [] sentences = firstPass.split("\\.");
        
        for(String s : sentences)
        {
            String [] words = s.replaceAll("[^a-zA-Z\\d\\s]", "").trim().split("\\s");
            listOfWords.addAll(Arrays.asList(words));
        }
        
        return listOfWords;
    }
}
