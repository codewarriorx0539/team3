
package edu.uis.csc478b.team3;


import edu.uis.csc478b.team3.config.PlagiarismTest;
import edu.uis.csc478b.team3.config.Configuration;
import edu.uis.csc478b.team3.filters.Filter;
import edu.uis.csc478b.team3.filters.SentenceSimilarity;
import edu.uis.csc478b.team3.filters.WordFrequency;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
    ArrayList<Filter> filters;
    String masterFile;
    String suspectFile;
    PlagiarismTest config;
    
    /**
     * De-serialized XML configuration sent as input.
     * @param config 
     */
    public Plagiarism( PlagiarismTest config )
    {
        this.config = config;
        this.masterFile = config.getMasterFile();
        this.suspectFile = config.getSuspectFile();
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
            
            filters = new ArrayList<>();
               
            FileProcessor master = new FileProcessor( masterFile, config.getCommonWordsFile(), config.getFilterCommonWords() );
            FileProcessor suspect = new FileProcessor( suspectFile, config.getCommonWordsFile(), config.getFilterCommonWords());

            filters.add(new WordFrequency( config, master, suspect));
            filters.add(new SentenceSimilarity( config, master, suspect ));
               
            String output;
            
            output = "PLAGIARISM TEST:" + System.lineSeparator();
            output = output + "MASTER FILE: " + masterFile + System.lineSeparator();
            output = output + "SUSPECT FILE: " + suspectFile + System.lineSeparator();
            output = output + "COMMON WORDS FILE: " + config.getCommonWordsFile() + System.lineSeparator() + System.lineSeparator();
            
            for(Filter filter : filters)
            {
                output = output + filter.runPlagiarismTest();
            }
           
            output = output + System.lineSeparator();
            
            synchronized(System.out)
            {
                
                System.out.println(output);
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
        if( args.length == 0)
        {
            try 
            {
                File file = new File("configuration.xml");
                
                Configuration configuration = new Configuration();
                PlagiarismTest plag = new PlagiarismTest();
                ArrayList<PlagiarismTest> list = new ArrayList<>();
                list.add(plag);
                configuration.setConfigs(list);
                
                JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                
                jaxbMarshaller.marshal(configuration, file);
                jaxbMarshaller.marshal(configuration, System.out);
            
            } catch (JAXBException ex) 
            {
                Logger.getLogger(Plagiarism.class.getName()).log(Level.SEVERE, null, ex);
            }
 
            return;  
        }
            
        try
        {
            
            String fileName = args[0];
            File file = new File(fileName);
            
            JAXBContext jaxbContext = JAXBContext.newInstance( Configuration.class );

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Configuration configuration = (Configuration) jaxbUnmarshaller.unmarshal(file);

            ArrayList<PlagiarismTest> configs = configuration.getConfigs();

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            
            // Each configuration is a test and pushed onto the threadpool
            for(PlagiarismTest config : configs)
            {
               executor.execute( new Plagiarism( config ) );
            }
            
            executor.shutdown();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
    }
 
}
