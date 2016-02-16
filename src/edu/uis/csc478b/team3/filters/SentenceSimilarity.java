
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.EditDistance;
import edu.uis.csc478b.team3.FileProcessor;
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
    
    EditDistance editDistance;
    float threshold;
    int range;
    int sentenceThreshold;

    public SentenceSimilarity( PlagiarismTest testConfig, FileProcessor master, FileProcessor suspect )
    {
        super( testConfig.getConfigSentenceSimilarity() , master,  suspect);
        
        editDistance = new EditDistance(testConfig.getConfigSentenceSimilarity().getConfigEditDistance());
        threshold =  testConfig.getConfigSentenceSimilarity().getSENTENCE_SIMILARITY_THRESHOLD();
        range = testConfig.getConfigSentenceSimilarity().getSENTENCE_SIMILARITY_RANGE();
        sentenceThreshold = testConfig.getConfigSentenceSimilarity().getTOTAL_SIMILAR_SENTENCES();
        
    }
    
    
    @Override
    public String runPlagiarismTest() 
    {
        String result = "";
        
        int mTotalWords = master.getNumWords();
        int sTotalWords = suspect.getNumWords();

        int mTotalSentences = master.getNumSentences();
        int sTotalSentences = suspect.getNumSentences();

        ArrayList<String> mSentences = master.getSentences();
        ArrayList<String> sSentences = suspect.getSentences();

        ArrayList<String> mWords = master.getWords();
        ArrayList<String> sWords = suspect.getWords();

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
        result = result + configSetup + System.lineSeparator();
          
        return result;
    }

}
