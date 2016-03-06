
package edu.uis.csc478b.team3;

import edu.uis.csc478b.team3.config.ConfigSentenceSimilarity;
import edu.uis.csc478b.team3.config.ConfigWordFrequency;
import edu.uis.csc478b.team3.config.Configuration;
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
 * 
 * <h3>Class:</h3> Plagiarism <br>
 * <h3>Project:</h3> Plagiarism <br>
 * <h3>Description:</h3> <br>
 * Main point of entry into the program. Implements Runnable so it cab be used in <br> 
 * Java's cached thread pool. <br>
 * 
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
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
    
    /**
     * Set the files to test and set the filters to run against those files. 
     * 
     * @param fileName1
     * @param fileName2
     * @param testCase 
     */
    public Plagiarism( String fileName1, String fileName2, PlagiarismTest testCase )
    {
        this.fileName1 = fileName1;
        this.fileName2 = fileName2;
             
        this.sentenceFilters = testCase.getSentenceFilters();
        this.wordFilters = testCase.getWordFilters();
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
    public  void readCommonWordFile( String file ) throws IOException
    {
        String commonText = new String( Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
         
        String [] formattedWords = commonText.split( REGEX_NEWLINE );
        
        for(String word : formattedWords)
        {
            commonWords.add( word.trim() );
        }
        
    }
    
    /**
     * Main entry point for a thread. The files will be opened and processed in the 
     * thread. All filters are run on the sentences or words. Output is synchronized.
     */
    @Override
    public void run()
    {
        try 
        {
            FileData dataSet1 = new FileData( fileName1, commonWords );
            FileData dataSet2 = new FileData( fileName2, commonWords );
            
            Results results = new Results();
            
            // Word filters
            for( PlagiarismFilter filter : wordFilters)
            {
                results.wordResults.add( filter.exec( dataSet1.getWords() , dataSet2.getWords() )  );
            }
            
            // Sentence filters
            for( PlagiarismFilter filter : sentenceFilters)
            {
                results.sentenceResults.add( filter.exec( dataSet1.getSentences() , dataSet2.getSentences() )  );
            }
            
            // Print output
            synchronized(System.out)
            {
                System.out.println( "Test Results:" );
                System.out.println( "\tFile Name: " + fileName1);
                System.out.println( "\tFile Name: " + fileName2);
                
                System.out.println( "Word Filters:" );
                
                for(String output : results.wordResults)
                {
                    System.out.print(output);
                }
                
                System.out.println( "Sentence Filters:" );
                
                for(String output : results.sentenceResults)
                {
                    System.out.print(output);
                } 
                
                System.out.println();
            }
   
        } catch (Exception ex) 
        {
            Logger.getLogger(Plagiarism.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     * Main entry for program.
     * 
     * @param args 
     */
    public static void main(String[] args) 
    {
        try
        {
            TestPairs testPairs = new TestPairs();
            
            ///////////////////////
            // Programmically set configuration
            Configuration config = new Configuration();
            ArrayList< PlagiarismTest > list = new ArrayList<>();
            PlagiarismTest pt = new PlagiarismTest();
            ArrayList< String > files = new ArrayList<>();
            
            files.add("master.txt");
            files.add("suspect.txt");
            files.add("other.txt");
            
            SentenceSimilarity sent = new SentenceSimilarity(new ConfigSentenceSimilarity());
            ArrayList< PlagiarismFilter > sentenceFilters = new ArrayList< >();
            sentenceFilters.add(sent);
            
            WordFrequency wfreq = new WordFrequency( new ConfigWordFrequency());
            ArrayList< PlagiarismFilter > wordFilters = new ArrayList< >();
            wordFilters.add(wfreq);
            
            pt.setFiles(files);
            pt.setSentenceFilters(sentenceFilters);
            pt.setWordFilters(wordFilters);
            
            list.add(pt);
            config.setTests(list);
            
            ArrayList< PlagiarismTest > tests = config.getTests();         
            ///////////////////////

            // Create a thread pool
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            
            // Iterate over a test set
            for(PlagiarismTest testCase : tests)
            {
                // Get the files to test against
                files = testCase.getFiles();
                // Create all combinations of files to test for plagairism
                ArrayList<TestPair> pairs = testPairs.createPairs(files);
                
                // Push each test onto the queue which will be run as a thread 
                for(TestPair tp : pairs)
                {
                    executor.execute( new Plagiarism( tp.file1 , tp.file2, testCase  ) );
                }
            }
            
            // Close threadpool after all test suites are complete
            executor.shutdown();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
    }
 
}
