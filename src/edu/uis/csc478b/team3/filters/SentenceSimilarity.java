
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.config.ConfigSentenceSimilarity;
import edu.uis.csc478b.team3.filters.algorithms.EditDistance;
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
    
    ConfigSentenceSimilarity configSentenceSimilarity;
    
    final protected String TAB = "\t";
    
    final protected String CLASSIFIER = "CLASSIFIER: SENTENCE SIMILARITY";
    final protected String FOUND = "Sentence Similarity: PLAGIARISM FOUND";
    final protected String NOT_FOUND = "SentenceSimilarity: PLAGIARISM NOT FOUND";
    final protected String CONFIGURATION = "CONFIGURATION: ";
    final protected String TOTAL1 = "Total sentences file1: ";
    final protected String TOTAL2 = "Total sentences file2: ";
    final protected String SIMILAR = "Total similar sentences: ";
    final protected String SIMILAR_RATIO = "Sentence Similarity Ratio: ";

    public SentenceSimilarity( ConfigSentenceSimilarity configSentenceSimilarity )
    {
        this.configSentenceSimilarity = new ConfigSentenceSimilarity(configSentenceSimilarity);
        
        editDistance = new EditDistance(configSentenceSimilarity.getConfigEditDistance());
        threshold =  configSentenceSimilarity.getSentenceSimilarityThreshold();
        range = configSentenceSimilarity.getSentenceSimilarityRange();
        totalSentenceThreshold = configSentenceSimilarity.getTotalSentenceThreshold();
        consecutiveSentences = configSentenceSimilarity.getConsecutiveSentences();
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
        
        String result = TAB + CLASSIFIER + System.lineSeparator();
        
        if(consecutiveSentences != -1 && done == true)
        {
            result = result + TAB + FOUND + System.lineSeparator();
        }
        else
        {
            sentenceSimilarityRatio = (float)total/(float)total1;
            if( sentenceSimilarityRatio >= totalSentenceThreshold )
            {
                result = result + TAB + FOUND + System.lineSeparator();
            }
            else
            {
                result = result + TAB + NOT_FOUND + System.lineSeparator();
            }
        }
        
        result = result + TAB + TOTAL1 + total1 + System.lineSeparator();
        result = result + TAB + TOTAL2 + total2 + System.lineSeparator();
        result = result + TAB + SIMILAR + total + System.lineSeparator();
        result = result + TAB + SIMILAR_RATIO + sentenceSimilarityRatio + System.lineSeparator();
        result = result + TAB + CONFIGURATION + System.lineSeparator();
        result = result + configSentenceSimilarity.getConfigSetup();
          
        return result;
    }

}
