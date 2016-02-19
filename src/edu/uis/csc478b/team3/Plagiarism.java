
package edu.uis.csc478b.team3;


import edu.uis.csc478b.team3.config.PlagiarismTest;
import edu.uis.csc478b.team3.config.Configuration;
import edu.uis.csc478b.team3.filters.DocumentSimilarity;
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

public class Plagiarism implements Runnable 
{
    ArrayList<Filter> filters;
    String masterFile;
    String suspectFile;
    PlagiarismTest config;
    
    public Plagiarism( PlagiarismTest config )
    {
        this.config = config;
        this.masterFile = config.getMasterFile();
        this.suspectFile = config.getSuspectFile();
    }
    
    @Override
    public void run()
    {
        try 
        {
            
            filters = new ArrayList<>();
               
            FileProcessor master = new FileProcessor( masterFile );
            FileProcessor suspect = new FileProcessor( suspectFile );

            filters.add(new WordFrequency( config, master, suspect));
            //testSet.add(new DocumentSimilarity( config, master, suspect ));
            filters.add(new SentenceSimilarity( config, master, suspect ));
               
            String output = "";

            output = "MASTER FILE: " + masterFile + System.lineSeparator();
            output = output + "SUSPECT FILE: " + suspectFile + System.lineSeparator();
            
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
   
    public static void main(String[] args) 
    {
        
        if( args.length == 0)
        {
            try 
            {
                File file = new File("configuration.xml");
                
                Configuration configuration = new Configuration();
                PlagiarismTest plag = new PlagiarismTest();
                ArrayList<PlagiarismTest> list = new ArrayList<PlagiarismTest>();
                list.add(plag);
                list.add(plag);
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
            // Read in XML config file
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance( Configuration.class );

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Configuration configuration = (Configuration) jaxbUnmarshaller.unmarshal(file);

            ArrayList<PlagiarismTest> configs = configuration.getConfigs();

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            
            for(PlagiarismTest config : configs)
            {
               executor.execute( new Plagiarism( config) );
            }
            
            executor.shutdown();
        }
        catch(Exception ex)
        {
            
        }
        
    }
 
}
