
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.EditDistance;
import edu.uis.csc478b.team3.FileProcessor;
import edu.uis.csc478b.team3.config.ConfigDocumentSimilarity;
import edu.uis.csc478b.team3.config.PlagiarismTest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DocumentSimilarity extends Filter
{
    
    EditDistance editDistance;
    int threshold;
    
    public DocumentSimilarity( PlagiarismTest testConfig , FileProcessor master, FileProcessor suspect )
    {
        super( testConfig.getConfigDocumentSimilarity(), master,  suspect);
        
        editDistance = new EditDistance( testConfig.getConfigDocumentSimilarity().getConfigEditDistance() );
        threshold = testConfig.getConfigDocumentSimilarity().getDOCUMENT_SIMILARITY_THRESHOLD();
        
    }

    @Override
    public String runPlagiarismTest() 
    {
        String result = "";
        String textMaster = master.getText();
        String textSuspect = suspect.getText();

        float distance = editDistance.getDistance(textMaster, textSuspect);

        if( threshold >=  distance)
        {
            result = result + "DocumentSimilarity: PLAGIARISM FOUND" + System.lineSeparator();
        }
        else
        {
            result = result + "DocumentSimilarity: PLAGIARISM NOT FOUND" + System.lineSeparator();
        }

        result = result + "Calculated distance: " + distance + System.lineSeparator();
        result = result + configSetup + System.lineSeparator();
             
        return result;
    }
}
