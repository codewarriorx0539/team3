
package edu.uis.csc478b.team3;


import edu.uis.csc478b.team3.config.PlagiarismTest;
import edu.uis.csc478b.team3.filters.PlagiarismFilter;
import edu.uis.csc478b.team3.filters.SentenceSimilarity;
import edu.uis.csc478b.team3.filters.WordFrequency;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * <p>
 * <h3>Class:</h3> Plagiarism
 * <h3>Project:</h3> Plagiarism
 * <h3>Description:</h3>
 * Main point of entry into the program. Implements Runnable so it cab be used in Java threading.
 * </p>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Programmer: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class Plagiarism implements Runnable 
{
    final private String COMMA = ",";
    final private String REGEX_NEWLINE = "\\r\\n";
    
    final HashSet<String> commonWords = new HashSet<>();        
    
    
    PlagiarismTest config;
    
    final String fileName1;
    final String fileName2;
    
    ArrayList< PlagiarismFilter > sentenceFilters;
    ArrayList< PlagiarismFilter > wordFilters;
    
    public Plagiarism( String fileName1, String fileName2, ArrayList< PlagiarismFilter > sentenceFilters, ArrayList< PlagiarismFilter > wordFilters  )
    {
        this.fileName1 = fileName1;
        this.fileName2 = fileName2;
        
        this.sentenceFilters = sentenceFilters;
        this.wordFilters = wordFilters;
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
    
    /**
     * Main entry point for a thread. The files will be opened in the thread and all filters will be run.
     * Output is synchronized.
     */
    @Override
    public void run()
    {
        try 
        {
            FileData dataSet1 = new FileData( fileName1, commonWords );
            FileData dataSet2 = new FileData( fileName2, commonWords );
            
            Results results = new Results();
            
            for( PlagiarismFilter filter : wordFilters)
            {
                results.wordResults.add( filter.exec( dataSet1.getWords() , dataSet2.getWords() )  );
            }
            
            for( PlagiarismFilter filter : sentenceFilters)
            {
                results.sentenceResults.add( filter.exec( dataSet1.getSentences() , dataSet2.getSentences() )  );
            }
            
            synchronized(System.out)
            {
                System.out.println( "Test Results" );
                System.out.println( "\tFile Name: " + fileName1);
                System.out.println( "\tFile Name: " + fileName2 + System.lineSeparator());
                
                System.out.println( "Word Filters:" );
                
                for(String output : results.wordResults)
                {
                    System.out.println(output + System.lineSeparator());
                }
                
                System.out.println( "Sentence Filters:" );
                
                for(String output : results.sentenceResults)
                {
                    System.out.println(output + System.lineSeparator());
                } 
                
                System.out.println();
            }
   
        } catch (Exception ex) 
        {
            Logger.getLogger(Plagiarism.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     * Main entry for program. If zero arguments then a sample configuration file is create if not then processing continues assuming an XML
     * configuration file
     * @param args 
     */
    public static void main(String[] args) 
    {
        try
        {
            ArrayList< String > files = new ArrayList<>();
            PlagiarismTest config = new PlagiarismTest();
            
            files.add("master.txt");
            files.add("suspect.txt");
            
            ArrayList< PlagiarismFilter > sentenceFilters = new ArrayList<  > ();
            ArrayList< PlagiarismFilter > wordFilters = new ArrayList<  > ();
            
            sentenceFilters.add( new SentenceSimilarity(config) );
            wordFilters.add( new WordFrequency(config) );
                    
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

            executor.execute( new Plagiarism( files.get(0), files.get(1), sentenceFilters, wordFilters  ) );
            
            executor.shutdown();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
    }
 
}
