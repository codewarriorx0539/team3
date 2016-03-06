
package edu.uis.csc478b.team3.filters;

import java.util.ArrayList;

/**
 * 
 * <h3>Class:</h3> PlagiarismFilter <br>
 * <h3>Project:</h3> Plagiarism <br>
 * <h3>Description:</h3> <br>
 * Abstract base for all filters including word and sentence filters. <br>
 * 
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public interface PlagiarismFilter 
{
    /**
     * Take in words or sentences and return the results
     * 
     * @param list1
     * @param list2
     * @return
     */
    String exec( ArrayList< String > list1, ArrayList< String > list2);
}
