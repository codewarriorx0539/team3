
package edu.uis.csc478b.team3;

import edu.uis.csc478b.team3.config.ClassFiles;
import edu.uis.csc478b.team3.config.Configuration;
import edu.uis.csc478b.team3.config.PlagiarismTest;
import edu.uis.csc478b.team3.filters.PlagiarismFilter;
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
 * Main point of entry into the program. Implements Runnable so it cab be used in 
 * Java's cached thread pool.
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
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
                results.wordResults.add( filter.exec( dataSet1 , dataSet2 )  );
            }
            
            // Sentence filters
            for( PlagiarismFilter filter : sentenceFilters)
            {
                results.sentenceResults.add( filter.exec( dataSet1 , dataSet2 )  );
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
            XmlConfig xmlConfig = new XmlConfig();
            
            if(args.length == 0)
            {
                xmlConfig.createSampleProfile();
                return;
            }
            
            ClassFiles classFiles = new ClassFiles();
            String configFile;
            
            if(args.length == 1)
            {
                Class[] classes = new Class[3];
                classes[0] = Class.forName( "edu.uis.csc478b.team3.config.Configuration");
                classes[1] = Class.forName( "edu.uis.csc478b.team3.filters.SentenceSimilarity");
                classes[2] = Class.forName( "edu.uis.csc478b.team3.filters.Similarity");
                        
                classFiles.setClasses(classes);
                configFile = args[0];

            }
            else if(args.length == 2)
            {
                classFiles = xmlConfig.readXmlClassFiles(args[0]);
                configFile = args[1];
            }
            else
            {
                throw new Exception("main::main Incorrect Number of Arguments");
            }

            Configuration configuration = xmlConfig.readXmlConfiguration( configFile, classFiles.getClasses());

            // Get all the test sets
            ArrayList< PlagiarismTest > tests = configuration.getTests();
            // Create a thread pool
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

            Runtime runtime = Runtime.getRuntime();
            final float MEGABYTE = 1024*1024;
            final int SLEEP_MILLISECONDS = 10;
            final float REMAINING_HEAP_MB = 200;
            float heapMax = (runtime.maxMemory()/MEGABYTE);

            // Iterate over a test set
            for(PlagiarismTest testCase : tests)
            {
                // Get the files to test against
                ArrayList< String > files = testCase.getFiles();
                // Create all combinations of files to test for plagairism
                ArrayList<TestPair> pairs = testPairs.createPairs(files);

                // Push each test onto the queue which will be run as a thread 
                for(TestPair tp : pairs)
                {
                    float heapFree = (runtime.freeMemory()/MEGABYTE);
                    float heapTotal = (runtime.totalMemory()/MEGABYTE);
                    int count = 0;
                    while( (heapMax - (heapTotal - heapFree))  < REMAINING_HEAP_MB)
                    {
                        count++;
                        // If we've paused for 1 second
                        if(count > 100)
                        {
                            runtime.gc();
                        }

                        Thread.sleep(SLEEP_MILLISECONDS);
                    }
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
