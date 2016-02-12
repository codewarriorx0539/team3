
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.EditDistance;
import edu.uis.csc478b.team3.config.ConfigDocumentSimilarity;
import edu.uis.csc478b.team3.config.PlagiarismTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DocumentSimilarity extends Filter
{
    ConfigDocumentSimilarity config;
    EditDistance editDistance;
    
    public DocumentSimilarity()
    {
        editDistance = new EditDistance();
    }
    
    public DocumentSimilarity( PlagiarismTest testConfig )
    {
        super(testConfig);
        editDistance = new EditDistance();
        config = testConfig.getConfigDocumentSimilarity();
    }

    @Override
    public String runPlagiarismTest() 
    {
        String result = "";
        
        editDistance.setINSERT_COST(config.getINSERT_COST());
        editDistance.setDELETION_COST(config.getDELETION_COST());
        editDistance.setSUBSTITUTION_COST(config.getSUBSTITUTION_COST());
        
        float threshold = config.getDOCUMENT_SIMILARITY_THRESHOLD();
        
        
        try 
        {
            String master = fileProcessor.fileAsAString(masterFile);
            String suspect = fileProcessor.fileAsAString(suspectFile);
            
            master = fileProcessor.removePuncuation(master);
            suspect = fileProcessor.removePuncuation(suspect);
            float distance = editDistance.getDistance(suspect, master);
            if( threshold >=  distance)
            {
                result = result + "DocumentSimilarity: PLAGIARISM FOUND" + System.lineSeparator();
            }
            else
            {
                result = result + "DocumentSimilarity: PLAGIARISM NOT FOUND" + System.lineSeparator();
            }
            
            result = result + "Calculated distance: " + distance + System.lineSeparator();
            result = result + config.getConfigSetup() + System.lineSeparator();
            
        } catch (IOException ex) 
        {
            Logger.getLogger(DocumentSimilarity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
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
    
}
