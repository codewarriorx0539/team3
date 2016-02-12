
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.EditDistance;
import java.util.ArrayList;

/**
 * Test similarity
 * 
 * @author Jake
 */
public class SentenceSimilarity extends Filter
{
    private float threshold;
    EditDistance distance;

    public SentenceSimilarity( ArrayList<ArrayList<String> > mWords, ArrayList<ArrayList<String> > sWords, ArrayList<String> mSentences , ArrayList<String> sSentences )
    {
        super(mWords, sWords, mSentences , sSentences);
        
        distance = new EditDistance();
    }
    
    public float getThreshold() 
    {
        return threshold;
    }

    public void setThreshold(float threshold) 
    {
        this.threshold = threshold;
    }

    @Override
    public String runPlagiarismTest() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    
    /////////////////////// Compute Sentence Similarity
            SentenceSimilarity sentenceSim = new SentenceSimilarity();
            
            if( sentenceSim.similarity(sSentences, mSentences) == true )
            {
                output = output + "ALERT PLAGIARISM (SENTENCE SIMILARITY): " + suspectFile + System.lineSeparator();
                printOutput(output);
                return;
            }
            output = output + "PASSED SENTENCE SIMILARITY" + System.lineSeparator();
            ///////////////////////////////////////
    */

    @Override
    public void readData(String masterFile, String suspectFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
