
package edu.uis.csc478b.team3.filters;

import java.util.ArrayList;

/**
 * SentenceSimilarity classifier compares a sentence in one file against the other 
 * file N number of sentences ahead, behind, and exact. A short circuit option 
 * is available that tests the number of consecutive sentences. If a block of 
 * sentences are the same one after the other An early detection can trigger.
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */

public class SentenceSimilarity extends PlagiarismFilter 
{
    EditDistance editDistance;
    float threshold;
    int range;
    float totalSentenceThreshold;
    int consecutiveSentences;
    
    final protected String TAB = "\t";
    final protected String CLASSIFIER = "CLASSIFIER: SENTENCE SIMILARITY";
    final protected String FOUND = "Sentence Similarity: PLAGIARISM FOUND";
    final protected String NOT_FOUND = "SentenceSimilarity: PLAGIARISM NOT FOUND";
    final protected String CONFIGURATION = "CONFIGURATION: ";
    final protected String TOTAL1 = "Total sentences file1: ";
    final protected String TOTAL2 = "Total sentences file2: ";
    final protected String SIMILAR = "Total similar sentences: ";
    final protected String SIMILAR_RATIO = "Sentence Similarity Ratio: ";
    
    final protected String RANGE = "sentenceSimilarityRange: ";
    final protected String SENTENCE_THRESHOLD = "sentenceSimilarityThreshold: ";
    final protected String TOTAL_THRESHOLD =  "totalSentenceThreshold: ";
    final protected String CONSECUTIVE = "consecutiveSentences: ";

    public SentenceSimilarity()
    {
        range = 3;                      // 3 behind 3 ahead
        threshold = .70f;               // if sentence is 70% 
        totalSentenceThreshold = .30f;  // If 30% of sentences in document are similar
        consecutiveSentences = -1;      // (-1 ignore blocks as a trigger) or (num consecutive or total sentence that are similar)
        
        editDistance = new EditDistance();
    }
    

    public  SentenceSimilarity( int range, 
                                float threshold, 
                                float totalSentenceThreshold, 
                                int consecutiveSentences, 
                                EditDistance editDistance) throws Exception
    {
        this.range = range;             
        this.threshold = threshold;      
        this.totalSentenceThreshold = totalSentenceThreshold;               
        this.consecutiveSentences = consecutiveSentences;                   
        
        this.editDistance = editDistance;
        
        // BOUNDS CHECK
        if( range == 0                      ||
            threshold < 0                   || 
            threshold > 1.0                 || 
            totalSentenceThreshold < 0      || 
            totalSentenceThreshold > 1.0    ||
            consecutiveSentences < -1       ||
            consecutiveSentences == 0)
        {
            throw new Exception("SentenceSimilarity::SentenceSimilarity value out of bounds");
        }
    }
   
    /**
     * Run similarity
     * 
     * @param list1
     * @param list2
     * @return
     */
    @Override
    public String exec( ArrayList< String > list1, ArrayList< String > list2) 
    {
        int total = 0;
        int totalConsecutiveSentences = 0;
        int indexLastTrigger = 0;
        boolean done = false;
        
        int total1 = list1.size();
        int total2 = list2.size();

        // Check behind and ahead
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
        result = result + getConfigSetup();
          
        return result;
    }
    
    public EditDistance getEditDistance() 
    {
        return editDistance;
    }

    public void setEditDistance(EditDistance editDistance) 
    {
        this.editDistance = editDistance;
    }
    
    public float getTotalSentenceThreshold() 
    {
        return totalSentenceThreshold;
    }

    public void setTotalSentenceThreshold(float totalSentenceThreshold) 
    {
        this.totalSentenceThreshold = totalSentenceThreshold;
    }

    public int getConsecutiveSentences() 
    {
        return consecutiveSentences;
    }

    public void setConsecutiveSentences(int consecutiveSentences) 
    {
        this.consecutiveSentences = consecutiveSentences;
    }

    public int getSentenceSimilarityRange() 
    {
        return range;
    }

    public void setSentenceSimilarityRange(int sentenceSimilarityRange) 
    {
        range = sentenceSimilarityRange;
    }

    public float getSentenceSimilarityThreshold() 
    {
        return threshold;
    }

    public void setSentenceSimilarityThreshold(float sentenceSimilarityThreshold) 
    {
        threshold = sentenceSimilarityThreshold;
    }
    
    @Override
    public String getConfigSetup() 
    {
        String setup = TAB + RANGE + range + System.lineSeparator();
        setup = setup + TAB + SENTENCE_THRESHOLD + threshold + System.lineSeparator();
        setup = setup + TAB + TOTAL_THRESHOLD + totalSentenceThreshold + System.lineSeparator();
        setup = setup + TAB +  CONSECUTIVE + consecutiveSentences + System.lineSeparator();

        setup = setup + editDistance.getConfigSetup();
        
        return setup;
    }

}
