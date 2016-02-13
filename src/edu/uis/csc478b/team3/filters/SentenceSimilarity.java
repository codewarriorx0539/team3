
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.EditDistance;
import edu.uis.csc478b.team3.config.ConfigSentenceSimilarity;
import edu.uis.csc478b.team3.config.PlagiarismTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test similarity
 * 
 * @author Jake
 */
public class SentenceSimilarity extends Filter
{
    ConfigSentenceSimilarity config;
    EditDistance editDistance;

    public SentenceSimilarity()
    {
        editDistance = new EditDistance();
    }
    
    public SentenceSimilarity( PlagiarismTest testConfig)
    {
        super(testConfig);
        
        editDistance = new EditDistance();
        
        config = testConfig.getConfigSentenceSimilarity();
    }
    
    
    @Override
    public String runPlagiarismTest() 
    {
        String result = "";
        float threshold = config.getSENTENCE_SIMILARITY_THRESHOLD(); 
        int range = config.getSENTENCE_SIMILARITY_RANGE();
        int sentenceThreshold = config.getTOTAL_SIMILAR_SENTENCES();
        
        editDistance.setINSERT_COST(config.getINSERT_COST());
        editDistance.setDELETION_COST(config.getDELETION_COST());
        editDistance.setSUBSTITUTION_COST(config.getSUBSTITUTION_COST());
        
        try 
        {
            String master = fileProcessor.fileAsAString(masterFile);
            String suspect = fileProcessor.fileAsAString(suspectFile);
            
            int mTotalSentences = fileProcessor.getSentences(master, mSentences);
            int sTotalSentences = fileProcessor.getSentences(suspect, sSentences);
            
            
            
            float total = 0;
            
            for(int index = 0; index < mTotalSentences && index < sTotalSentences; index++ )
            {
                boolean foundSimilar = false;
                for(int i = index; (i < mTotalSentences) && (i < sTotalSentences) && (i - index <= range) && (foundSimilar != true) ; i++)
                {
                   if( threshold >= editDistance.getDistance(sSentences.get(i), mSentences.get(index)) )
                   {
                       foundSimilar = true;
                   }
                }

                if(foundSimilar == false)
                {
                    for(int i = index; (i >= 0) && (i > (index - range)) && (foundSimilar != true); i--)
                    {
                        if( threshold >= editDistance.getDistance(sSentences.get(i), mSentences.get(index)) )
                        {
                            foundSimilar = true;
                        }
                    }
                }
                
                if(foundSimilar == true)
                {
                    total++;
                }
 
            }
            
            if( total >= sentenceThreshold )
            {
                result = result + "SentenceSimilarity: PLAGIARISM FOUND" + System.lineSeparator();
            }
            else
            {
                result = result + "SentenceSimilarity: PLAGIARISM NOT FOUND" + System.lineSeparator();
            }
            result = result + "Total similar sentences: " + total + System.lineSeparator();
            result = result + config.getConfigSetup() + System.lineSeparator();
          
        } catch (IOException ex) 
        {
            Logger.getLogger(DocumentSimilarity.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return result;
    }

}
