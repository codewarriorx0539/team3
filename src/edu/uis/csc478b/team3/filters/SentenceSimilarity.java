
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.EditDistance;
import edu.uis.csc478b.team3.config.PlagiarismTest;
import java.util.ArrayList;

/**
 * 
 * <p>
 * <h3>Class:</h3> SentenceSimilarity
 * <h3>Project:</h3> Plagiarism
 * <h3>Description:</h3>
 * SentenceSimilarity classifier compares a sentence in the master against the suspect N number of sentences ahead, behind, and exact.</br>
 * A short circuit option is available that tests the number of consecutive sentences. If a block of sentences are the same one after the other </br>
 * An early detection can trigger.
 * </p>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Programmer: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class SentenceSimilarity implements PlagiarismFilter 
{
    final private EditDistance editDistance;
    final private float threshold;
    final private int range;
    final private float totalSentenceThreshold;
    final private int consecutiveSentences;

    public SentenceSimilarity( PlagiarismTest testConfig )
    {
        editDistance = new EditDistance(testConfig.getConfigSentenceSimilarity().getConfigEditDistance());
        threshold =  testConfig.getConfigSentenceSimilarity().getSentenceSimilarityThreshold();
        range = testConfig.getConfigSentenceSimilarity().getSentenceSimilarityRange();
        totalSentenceThreshold = testConfig.getConfigSentenceSimilarity().getTotalSentenceThreshold();
        consecutiveSentences = testConfig.getConfigSentenceSimilarity().getConsecutiveSentences();
    }
    
    @Override
    public String exec( ArrayList< String > list1, ArrayList< String > list2) 
    {
        int total = 0;
        int totalConsecutiveSentences = 0;
        int indexLastTrigger = 0;
        boolean done = false;
        
        int total1 = list1.size();
        int total2 = list2.size();

        for(int index = 0; index < total1 && index < total2 && done == false; index++ )
        {
            boolean foundSimilar = false;
            
            for(int i = index; (i < total1) && (i < total2) && (i - index <= range) && (foundSimilar != true) ; i++)
            {
               if( threshold >= editDistance.getDistance( list1.get(i), list2.get(index)) )
               {
                   foundSimilar = true;
               }
            }

            if(foundSimilar == false)
            {
                for(int i = index; (i >= 0) && (i > (index - range)) && (foundSimilar != true); i--)
                {
                    if( threshold >= editDistance.getDistance( list1.get(i), list2.get(index)) )
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
        String result;
        
        result = "CLASSIFIER: SENTENCE SIMILARITY" + System.lineSeparator();
        
        if(consecutiveSentences != -1 && done == true)
        {
            result = result + "Sentence Similarity: PLAGIARISM FOUND" + System.lineSeparator();
        }
        else
        {
            sentenceSimilarityRatio = (float)total/(float)total1;
            if( sentenceSimilarityRatio >= totalSentenceThreshold )
            {
                result = result + "SentenceSimilarity: PLAGIARISM FOUND" + System.lineSeparator();
            }
            else
            {
                result = result + "SentenceSimilarity: PLAGIARISM NOT FOUND" + System.lineSeparator();
            }
        }
        result = result + "CONFIGURATION:" + System.lineSeparator();
        result = result + "Total master sentences: " + total1 + System.lineSeparator();
        result = result + "Total similar sentences: " + total + System.lineSeparator();
        result = result + "Sentence Similarity Ratio: " + sentenceSimilarityRatio + System.lineSeparator();
        
        result = result + System.lineSeparator();
          
        return result;
    }

}
