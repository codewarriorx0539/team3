
package edu.uis.csc478b.team3;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Plagiarism 
{
    Configuration config;
    
    public ArrayList<ConfigComparison> getDocumentsToScan()
    {
        return config.getConfigs();
    }
    
    /**
     * 
     * @param fileName
     * @throws JAXBException 
     */
    public void readXmlConfiguration(String fileName) throws JAXBException
    {
        File file = new File(fileName);
        JAXBContext jaxbContext = JAXBContext.newInstance( Configuration.class );

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        config = (Configuration) jaxbUnmarshaller.unmarshal(file);
    }
    
    public void scanDoc(ConfigComparison config)
    {
        String masterFile = config.getMasterFile();
        String suspectFile = config.getSuspectFile();
        ArrayList<ArrayList<String> > mWords = new ArrayList<ArrayList<String> >();
        ArrayList<ArrayList<String> > sWords = new ArrayList<ArrayList<String> >();
        ArrayList<String> mSentences = new ArrayList<String>();
        ArrayList<String> sSentences = new ArrayList<String>();
        
        
        FileProcessor mProcessor = new FileProcessor();
        mProcessor.getSentences(masterFile, mSentences);
        mProcessor.getWordsOfSentences(masterFile, mWords);
        
        FileProcessor sProcessor = new FileProcessor();
        mProcessor.getSentences(suspectFile, sSentences);
        sProcessor.getWordsOfSentences(suspectFile, sWords);
        
        
        /////////////////////// Compute Word Frequency
        WordFrequency freq = new WordFrequency();
        
        if(freq.frequency(sWords, mWords) == true)
        {
            System.out.println("Plagairism detected in file: " + suspectFile);
            return;
        }
        ///////////////////////////////////////
        
        
        /////////////////////// Compute Sentence Similarity
        SentenceSimilarity sentenceSim = new SentenceSimilarity();
        
        if( sentenceSim.similarity(sSentences, mSentences) == true )
        {
            System.out.println("Plagairism detected in file: " + suspectFile);
            return;
        }
        ///////////////////////////////////////
        
        
    }
    
    /**
     * 
     * @param fileName
     * @throws JAXBException 
     */
    public void writeXmlConfiguration(String fileName) throws JAXBException
    {
        File file = new File(fileName);
        JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(config, file);
        jaxbMarshaller.marshal(config, System.out);
    }
    

    public static void main(String[] args) 
    {
        String fileName = args[0];
        Plagiarism plag = new Plagiarism();
        
        try 
        {
            plag.readXmlConfiguration( fileName );
            ArrayList<ConfigComparison> configs = plag.getDocumentsToScan();
            
            for(ConfigComparison config : configs)
            {
                plag.scanDoc(config);/// This can kick off new threads to complete in parallel.
            }
            
        } catch (JAXBException ex) 
        {
            Logger.getLogger(Plagiarism.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
