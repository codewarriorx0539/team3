
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.FileData;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Abstract base for all filters including word and sentence filters.
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
     * Take in words or sentences and return the results
     * 
     * @param list1
     * @param list2
     * @return Results of the execution of the filter
     * @throws java.lang.Exception
     */
    public abstract String exec( FileData data1, FileData data2 ) throws Exception;
    
    /**
     * 
     * @return 
     */
    public abstract String getConfigSetup();
}
