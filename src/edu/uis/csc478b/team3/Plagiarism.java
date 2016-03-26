
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
 * The Plagiarism class runs all filters on the two test files. Plagiarism implements 
 * Runnable so it is a thread of execution. A Plagiarism instance is pushed onto the cached 
 * thread pool in the main function in this class.
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
    // Used to split common words files that are comma delimited
    final private String COMMA = ",";
    // Used to split common words files that are newline delimited
    final private String REGEX_NEWLINE = "\\r\\n";
    
    // Data structure to hold the common words
    final HashSet<String> commonWords = new HashSet<>();        
    
    // The deserialized XML configuration 
    PlagiarismTest config;
    
    // Holds the two file names
    final String fileName1;
    final String fileName2;
    final String commonWordsFileName;
    
    // Lists of the filters to run on the files
    ArrayList< PlagiarismFilter > sentenceFilters;
    ArrayList< PlagiarismFilter > wordFilters;
    
    /**
     * Constructor: Set the test file names and send in the configuration
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
        this.commonWordsFileName = testCase.getCommonWordsFile();
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
    private  void readCommonWordFile( String file ) throws IOException
    {
        String commonText = new String( Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
         
        String [] formattedWords = commonText.split( REGEX_NEWLINE );
        
        for(String word : formattedWords)
        {
            commonWords.add( word.trim() );
        }
        
    }
    
    /**
     * Files will be opened from within the thread to reduce io delays on the main thread.
     * All filters are run on the sentences or words. Output is synchronized.
     */
    @Override
    public void run()
    {
        try 
        {
            // Read in common words if configured
            if( (commonWordsFileName != null) && (!commonWordsFileName.isEmpty()) )
            {
                readCommonWordCsvFile(commonWordsFileName);
            }
            
            // Clean up files from noise and optionally filter out common words
            FileData dataSet1 = new FileData( fileName1, commonWords );
            FileData dataSet2 = new FileData( fileName2, commonWords );
            
            // Holds the results from the filters
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
     * Main entry for the program.
     * 
     * @param args 
     */
    public static void main(String[] args) 
    {
        // Class names of the basic filters. These need to be declared since the base class
        // for all filters is abstract and XML needs to know how to build specific abstract XML types
        final String CONFIG_CLASS = "edu.uis.csc478b.team3.config.Configuration";
        final String SENTENCE_SIMILARITY_CLASS = "edu.uis.csc478b.team3.filters.SentenceSimilarity";
        final String CONFIG_WORD_SIMILARITY_CLASS = "edu.uis.csc478b.team3.filters.WordSimilarity";
        ArrayList<Class> listOfClasses = new ArrayList<>();
        
        // The runtime information below is used to scale the throughput of adding new test cases
        Runtime runtime = Runtime.getRuntime();
        // Number of bytes in a MegaByte
        final float MEGABYTE = 1024*1024;
        // Used to wait for new memory to be reclaimed by the JVM
        final int SLEEP_MILLISECONDS = 10;
        // The threshold of the last available MB free in the JVM before we wait for tasks to complete.
        final float REMAINING_HEAP_MB = 200;
        // Max size of the heap
        float heapMax = (runtime.maxMemory()/MEGABYTE);
        
        try
        {
            // Test pairs hold a combination of all files
            TestPairs testPairs = new TestPairs();
            // Serialization/Deserialization of the XML configuration
            XmlConfig xmlConfig = new XmlConfig();
            
            // If zero arguments create a sample configuration
            if(args.length == 0)
            {
                xmlConfig.createSampleProfile();
                return;
            }
            
            ClassFiles classFiles = new ClassFiles();
            String configFile;
            
            // If only the XML config file is given then use basic steup 
            if(args.length == 1)
            {
                listOfClasses.add( Class.forName(CONFIG_CLASS) );
                listOfClasses.add( Class.forName(SENTENCE_SIMILARITY_CLASS) );
                listOfClasses.add( Class.forName(CONFIG_WORD_SIMILARITY_CLASS) );
                
                        
                classFiles.setClasses((Class []) listOfClasses.toArray());
                configFile = args[0];

            }
            // If Class XML and Config XML are given
            else if(args.length == 2)
            {
                classFiles = xmlConfig.readXmlClassFiles(args[0]);
                configFile = args[1];
            }
            else
            {
                throw new Exception("main::main Incorrect Number of Arguments");
            }

            // Read in the XML config file
            Configuration configuration = xmlConfig.readXmlConfiguration( configFile, classFiles.getClasses());

            // Get all the test sets
            ArrayList< PlagiarismTest > tests = configuration.getTests();
            // Create a thread pool
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            
            // Iterate over the test sets
            for(PlagiarismTest testCase : tests)
            {
                // Get the files to test against
                ArrayList< String > files = testCase.getFiles();
                // Create all combinations of files to test for plagairism
                ArrayList<TestPair> pairs = testPairs.createPairs(files);

                // Push each test onto the queue which will be run as a thread 
                for(TestPair tp : pairs)
                {
                    // Test to see if we have enough memory
                    float heapFree = (runtime.freeMemory()/MEGABYTE);
                    float heapTotal = (runtime.totalMemory()/MEGABYTE);
                    int count = 0;
                    while( (heapMax - (heapTotal - heapFree))  < REMAINING_HEAP_MB)
                    {
                        count++;
                        // If we've paused for 1 second in total then try to force a garbage collection cycle
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
