
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.EditDistance;
import edu.uis.csc478b.team3.FileProcessor;
import edu.uis.csc478b.team3.config.PlagiarismTest;
import java.util.ArrayList;

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
    float totalSentenceThreshold;
    int consecutiveSentences;

    public SentenceSimilarity( PlagiarismTest testConfig, FileProcessor master, FileProcessor suspect )
    {
        super( testConfig.getConfigSentenceSimilarity() , master,  suspect);
        
        editDistance = new EditDistance(testConfig.getConfigSentenceSimilarity().getConfigEditDistance());
        threshold =  testConfig.getConfigSentenceSimilarity().getSENTENCE_SIMILARITY_THRESHOLD();
        range = testConfig.getConfigSentenceSimilarity().getSENTENCE_SIMILARITY_RANGE();
        totalSentenceThreshold = testConfig.getConfigSentenceSimilarity().getTOTAL_SENTENCE_THRESHOLD();
        consecutiveSentences = testConfig.getConfigSentenceSimilarity().getCONSECUTIVE_SENTENCES();
    }
    
    
    @Override
    public String runPlagiarismTest() 
    {
        String result = "";
        
        int mTotalSentences = master.getNumSentences();
        int sTotalSentences = suspect.getNumSentences();

        ArrayList<String> mSentences = master.getSentences();
        ArrayList<String> sSentences = suspect.getSentences();

        int total = 0;
        int totalConsecutiveSentences = 0;
        int indexLastTrigger = 0;
        boolean done = false;

        for(int index = 0; index < mTotalSentences && index < sTotalSentences && done == false; index++ )
        {
            boolean foundSimilar = false;
            
            // Sweep a few sentences behind and ahead to see if the senetence is slighlty aligned differently
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
                
                if(indexLastTrigger == (index - 1) )
                {
                    totalConsecutiveSentences++;
                    indexLastTrigger = index;
                    if(consecutiveSentences != -1 && totalConsecutiveSentences >= consecutiveSentences)
                    {
                        done = true;
                    }
      
                    
                }
                else
                {
                    indexLastTrigger = index;
                    totalConsecutiveSentences = 1;
                }
                
            }

        }

        float sentenceSimilarityRatio = 0.0f;
        // if block triggered
        if(consecutiveSentences != -1 && done == true)
        {
            result = result + "SentenceSimilarity: PLAGIARISM FOUND" + System.lineSeparator();
        }
        else
        {
            sentenceSimilarityRatio = (float)total/(float)mTotalSentences;
            if( sentenceSimilarityRatio >= totalSentenceThreshold )
            {
                result = result + "SentenceSimilarity: PLAGIARISM FOUND" + System.lineSeparator();
            }
            else
            {
                result = result + "SentenceSimilarity: PLAGIARISM NOT FOUND" + System.lineSeparator();
            }
        }
        result = result + "Total master sentences: " + mTotalSentences + System.lineSeparator();
        result = result + "Total similar sentences: " + total + System.lineSeparator();
        result = result + "Sentence Similarity Ratio: " + sentenceSimilarityRatio + System.lineSeparator();
        
        result = result + configSetup + System.lineSeparator();
          
        return result;
    }

}
