
package edu.uis.csc478b.team3;

import java.io.File;
import java.io.IOException;
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
    
    public void scanDoc(ConfigComparison config) throws IOException
    {
        String masterFile = config.getMasterFile();
        String suspectFile = config.getSuspectFile();
        int DIFFERENCE_THRESHOLD = 0;
        int FREQUENCY_DIFFERENCE_THRESHOLD = 0;
        
        float SIMILAR_WORDS_THRESHOLD = 0;
        float DOCUMENT_SIMILARITY_THRESHOLD = 0;
        
        ArrayList<ArrayList<String> > mWords = new ArrayList<ArrayList<String> >();
        ArrayList<ArrayList<String> > sWords = new ArrayList<ArrayList<String> >();
        
        ArrayList<String> mSentences = new ArrayList<String>();
        ArrayList<String> sSentences = new ArrayList<String>();
        
        
        FileProcessor fileProcessor = new FileProcessor();
        
        String mText = fileProcessor.fileAsAString(masterFile);
        fileProcessor.getSentences(mText, mSentences);
        fileProcessor.getWordsOfSentences(mText, mWords);
        
        String sText = fileProcessor.fileAsAString(suspectFile);
        fileProcessor.getSentences(sText, sSentences);
        fileProcessor.getWordsOfSentences(sText, sWords);
        
        
        /////////////////////// Compute Word Frequency
        WordFrequency freq = new WordFrequency();
        
        WordFrequencyResults results = freq.frequency(sWords, mWords);
        
        if( DIFFERENCE_THRESHOLD > Math.abs( results.getMasterTotal() - results.getCompareTotal())  )
        // This means that if there is a large difference in the number of words between documents then it is unlikely to be similar in any way
        {
            System.out.println("Plagairism is NOT detected in file: " + suspectFile);
            return;
        }
        
        if( FREQUENCY_DIFFERENCE_THRESHOLD > Math.abs( results.getMasterTotal() - results.getCompareTotal()) )
        // This is specific to word frequency
        {
            float percentageSimilar = ( results.getSimilarWords() / results.getMasterTotal() );

            if( percentageSimilar >= SIMILAR_WORDS_THRESHOLD )
            {
                System.out.println("Plagairism detected in file: " + suspectFile);
                return;
            }
        }
        ///////////////////////
        
        
        /////////////////////// Compute Document Similarity
        EditDistance dist = new EditDistance();
        
        String cleanMasterText = fileProcessor.removePuncuation(mText);
        String cleanSuspectText = fileProcessor.removePuncuation(sText);
        
        
        if( DOCUMENT_SIMILARITY_THRESHOLD >= dist.getDistance(cleanMasterText, cleanSuspectText) )
        {
            System.out.println("Plagairism detected in file: " + suspectFile);
            return;
        }
        ///////////////////////
        
        
        
        /////////////////////// Compute Sentence Similarity
        SentenceSimilarity sentenceSim = new SentenceSimilarity();
        
        if( sentenceSim.similarity(sSentences, mSentences) == true )
        {
            System.out.println("Plagairism detected in file: " + suspectFile);
            return;
        }
        ///////////////////////////////////////
        
        
        System.out.println("We were unable to classify: " + suspectFile + " Please inspect manually.");
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
        } catch (IOException ex) {
            Logger.getLogger(Plagiarism.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
