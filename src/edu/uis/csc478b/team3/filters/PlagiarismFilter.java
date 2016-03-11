
package edu.uis.csc478b.team3.filters;

import java.util.ArrayList;

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
public interface PlagiarismFilter 
{
    /**
     * Take in words or sentences and return the results
     * 
     * @param list1
     * @param list2
     * @return Results of the execution of the filter
     * @throws java.lang.Exception
     */
    String exec( ArrayList< String > list1, ArrayList< String > list2) throws Exception;
}
