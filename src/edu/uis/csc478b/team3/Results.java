
package edu.uis.csc478b.team3;

import java.util.ArrayList;

/**
 * Results: holds all the results for all the tests.
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public class Results 
{

    /**
     * Holds the results from all filters run on files.
     */
    public Results()
    {
       sentenceResults = new ArrayList<>();
       wordResults = new ArrayList<>();
    }

    public ArrayList< String > sentenceResults;
    public ArrayList< String > wordResults;
}
