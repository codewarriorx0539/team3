
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.FileData;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * PlagiarismFilter: abstract base for all filters including word and sentence filters.
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
@XmlRootElement
public abstract class PlagiarismFilter 
{
    final protected String TAB = "\t";
    
    /**
     * Run the filter on the parsed files and return results as a string.
     * 
     * @param data1
     * @param data2
     * @return Results of filtering
     * @throws Exception 
     */
    public abstract String exec( FileData data1, FileData data2 ) throws Exception;
    
    /**
     * Get the configuration of this specific filter
     * 
     * @return 
     */
    public abstract String getConfigSetup();
}
