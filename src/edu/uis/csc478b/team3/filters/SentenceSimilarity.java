
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.FileData;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * SentenceSimilarity: is a classifier that compares a sentence in one file against the other 
 * file N number of sentences ahead and behind. This classifier makes the
 * implicit assumption that someone who plagiarizes a document will plagiarize 
 * in the same locality. A short circuit option is available that tests the number 
 * of consecutive sentences. If a block of sentences are the same one after the 
 * other plagiarism detection can trigger.
 * 
 * Req 19.0.0
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
@XmlRootElement
public class SentenceSimilarity extends PlagiarismFilter 
{
    EditDistance editDistance;          // Used to calculate distance between sentences - Req 19.5.0, 19.6.0, 19.7.0
    float threshold;                    // Distance threshold when sweeping a sentence against a set of sentences in another file - Req 12.3.0, 19.2.0
    int range;                          // The number of sentences to sweep behind and ahead. - Req 12.2.0, 19.3.0
    float totalSentenceThreshold;       // Ratio of plagiarized sentences that triggers plagiarism - Req 12.4.0,  19.4.0
    int consecutiveSentences;           // Optional short circuit plagiarism based on plagiarized consecutive sentences - Req 12.1.0, 19.1.0
    
    final protected String CLASSIFIER = "CLASSIFIER: SENTENCE SIMILARITY";
    final protected String FOUND = "Sentence Similarity: PLAGIARISM FOUND";
    final protected String NOT_FOUND = "SentenceSimilarity: PLAGIARISM NOT FOUND";
    final protected String CONFIGURATION = "CONFIGURATION: ";
    final protected String TOTAL1 = "Total sentences ";
    final protected String TOTAL2 = "Total sentences ";
    final protected String SIMILAR = "Total similar sentences: ";
    final protected String SIMILAR_RATIO = "Sentence Similarity Ratio: ";
    
    final protected String RANGE = "sentenceSimilarityRange: ";
    final protected String SENTENCE_THRESHOLD = "sentenceSimilarityThreshold: ";
    final protected String TOTAL_THRESHOLD =  "totalSentenceThreshold: ";
    final protected String CONSECUTIVE = "consecutiveSentences: ";

    /**
     * Constructor: default that works for most situations
     * Req 7.3.1, 7.3.2, 7.3.3, 7.3.4, 7.3.5
     */
    public SentenceSimilarity()
    {
        range = 3;                      // 3 behind 3 ahead
        threshold = .70f;               // if sentence is 70% similar
        totalSentenceThreshold = .30f;  // If 30% of the sentences in document are similar
        consecutiveSentences = -1;      // -1 ignore blocks of similarity as a trigger 
        
        editDistance = new EditDistance();
    }
    
    /**
     * Constructor: set all thresholds and configurations
     * 
     * @param range
     * @param threshold
     * @param totalSentenceThreshold
     * @param consecutiveSentences
     * @param editDistance
     * @throws Exception 
     */
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
        if( range <= 0                      ||
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
     * Run the filter and try to detect plagiarism.
     * 
     * @param data1
     * @param data2
     * @return 
     */
    @Override
    public String exec( FileData data1, FileData data2 ) 
    {
        ArrayList< String > list1 = data1.getSentences();
        ArrayList< String > list2 = data2.getSentences();
        int total = 0;
        int totalConsecutiveSentences = 0;
        int indexLastTrigger = 0;
        boolean done = false;
        
        int total1 = list1.size();
        int total2 = list2.size();
        
        int lastSentence;
        int endLargest;
        int endSmallest;
        ArrayList< String > smallerList;
        ArrayList< String > largerList;
        
        if(total1 > total2)
        {
            endSmallest = total2;
            endLargest = total1;
            smallerList = list2;
            largerList = list1;
        }
        else
        {
            endSmallest = total1;
            endLargest = total2;
            smallerList = list1;
            largerList = list2;
        }

        // Check behind and ahead - 
        for(int index = 0; (index < endSmallest) && (done == false); index++ )
        {
            boolean foundSimilar = false;
            int start = 0;
            int end = endLargest;
            
            // Req 19.8.1
            if( (index - range) > 0)
            {
                start = index - range;
            }
            
            // Req 19.8.1
            if((index + range) < endLargest )
            {
                end = index + range;
            }
            
            for(int i = start; (i < end) && (foundSimilar != true); i++)
            {
                String sentence1 = smallerList.get(index);
                String sentence2 = largerList.get(i);
                
                float cost =  sentence1.length() * Math.max(editDistance.getInsertCost(), editDistance.getSubstitutionCost() );
                // Req 19.8.2
                float distance = editDistance.getDistance( sentence1 , sentence2 );
                float ratioOfCorrectness = distance/cost;

                // Req 19.8.2.1
                if( ratioOfCorrectness <= threshold  )
                {
                    foundSimilar = true;
                }
            }

            // Req 19.8.2.2
            if(foundSimilar == true)
            {
                total++;
                
                if(indexLastTrigger == (index - 1) )
                {             
                    // Req 19.8.3
                    totalConsecutiveSentences++;
                    indexLastTrigger = index;
                    
                    // Req 19.1.1,  19.8.3.1, 19.8.3.2
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
        
        // Test to see if we are testing consecutive blocks and if we short circuited.
        // Req 19.8.4
        if( done == true)
        {
            result = result + TAB + FOUND + System.lineSeparator();
        }
        // If we have not short circuited see if we have plagiarism
        else
        {
            float smallest;
            
            if(total1 > total2)
            {
                smallest = total2;
            }
            else
            {
                smallest = total1;
            }
            
            // Test to see if we surpassed the threshold
            // Req 19.8.0, 19.8.4.1, 19.8.4.2, Req 19.9.0
            sentenceSimilarityRatio = (float)total/(float)smallest;
            if( sentenceSimilarityRatio >= totalSentenceThreshold )
            {
                result = result + TAB + FOUND + System.lineSeparator();
            }
            else
            {
                result = result + TAB + NOT_FOUND + System.lineSeparator();
            }
        }
        
        
        DecimalFormat df = new DecimalFormat("###.##%");
        result = result + TAB + TOTAL1 + data1.getFileName() + ": " + total1 + System.lineSeparator();
        result = result + TAB + TOTAL2 + data2.getFileName() + ": " + total2 + System.lineSeparator();
        result = result + TAB + SIMILAR + total + System.lineSeparator();
        result = result + TAB + SIMILAR_RATIO + df.format(sentenceSimilarityRatio) + System.lineSeparator();
        result = result + TAB + CONFIGURATION + System.lineSeparator();
        result = result + getConfigSetup();
          
        return result;
    }
    
    public EditDistance getEditDistance() 
    {
        return editDistance;
    }

    synchronized public void setEditDistance(EditDistance editDistance) 
    {
        this.editDistance = editDistance;
    }
    
    public float getTotalSentenceThreshold() 
    {
        return totalSentenceThreshold;
    }

    synchronized public void setTotalSentenceThreshold(float totalSentenceThreshold) throws Exception 
    {
        // BOUNDS CHECK
        if( totalSentenceThreshold < 0      || 
            totalSentenceThreshold > 1.0 )
        {
            throw new Exception("SentenceSimilarity::setTotalSentenceThreshold value out of bounds: " + totalSentenceThreshold);
        }
        
        this.totalSentenceThreshold = totalSentenceThreshold;
    }

    public int getConsecutiveSentences() 
    {
        return consecutiveSentences;
    }

    synchronized public void setConsecutiveSentences(int consecutiveSentences) throws Exception 
    {
        if( consecutiveSentences < -1       ||
            consecutiveSentences == 0)
        {
            throw new Exception("SentenceSimilarity::setConsecutiveSentences value out of bounds: " + consecutiveSentences);
        }
        this.consecutiveSentences = consecutiveSentences;
    }

    public int getSentenceSimilarityRange() 
    {
        return range;
    }

    synchronized public void setSentenceSimilarityRange(int sentenceSimilarityRange) throws Exception 
    {
        // BOUNDS CHECK
        if( sentenceSimilarityRange <= 0)
        {
            throw new Exception("SentenceSimilarity::setSentenceSimilarityRange value out of bounds: " + sentenceSimilarityRange);
        }
        
        range = sentenceSimilarityRange;
    }

    public float getSentenceSimilarityThreshold() 
    {
        return threshold;
    }

    synchronized public void setSentenceSimilarityThreshold(float sentenceSimilarityThreshold) throws Exception 
    {
        // BOUNDS CHECK
        if( sentenceSimilarityThreshold < 0 || 
            sentenceSimilarityThreshold > 1.0 )
        {
            throw new Exception("SentenceSimilarity::setSentenceSimilarityThreshold value out of bounds: " + sentenceSimilarityThreshold);
        }
        threshold = sentenceSimilarityThreshold;
    }
    
    @Override
    public String getConfigSetup() 
    {
        DecimalFormat df = new DecimalFormat("###.##%");
        String setup = TAB + RANGE + range + System.lineSeparator();
        setup = setup + TAB + SENTENCE_THRESHOLD + df.format(threshold) + System.lineSeparator();
        setup = setup + TAB + TOTAL_THRESHOLD + df.format(totalSentenceThreshold) + System.lineSeparator();
        setup = setup + TAB +  CONSECUTIVE + consecutiveSentences + System.lineSeparator();

        setup = setup + editDistance.getConfigSetup();
        
        return setup;
    }

}
