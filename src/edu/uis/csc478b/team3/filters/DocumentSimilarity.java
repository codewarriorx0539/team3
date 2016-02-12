
package edu.uis.csc478b.team3.filters;

import java.util.ArrayList;


public class DocumentSimilarity extends Filter
{
    public DocumentSimilarity(){}
    
    public DocumentSimilarity( ArrayList<ArrayList<String> > mWords, ArrayList<ArrayList<String> > sWords, ArrayList<String> mSentences , ArrayList<String> sSentences )
    {
        super(mWords, sWords, mSentences, sSentences);
    }

    @Override
    public String runPlagiarismTest() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*
    
     int DIFFERENCE_THRESHOLD = 0;
            int FREQUENCY_DIFFERENCE_THRESHOLD = 0;
            
            float SIMILAR_WORDS_THRESHOLD = 0;
            float DOCUMENT_SIMILARITY_THRESHOLD = 0;
    
    
    
    
    
    /////////////////////// Compute Document Similarity
            EditDistance dist = new EditDistance();
            
            String cleanMasterText = fileProcessor.removePuncuation(mText);
            String cleanSuspectText = fileProcessor.removePuncuation(sText);
            
            if( DOCUMENT_SIMILARITY_THRESHOLD >= dist.getDistance(cleanMasterText, cleanSuspectText) )
            {
                output = output + "ALERT PLAGIARISM (DOCUMENT SIMILARITY THRESHOLD): " + suspectFile + System.lineSeparator();
                printOutput(output);
                return;
            }
            output = output + "PASSED DOCUMENT SIMILARITY THRESHOLD" + System.lineSeparator();
            ///////////////////////
    */

    @Override
    public void readData(String masterFile, String suspectFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
