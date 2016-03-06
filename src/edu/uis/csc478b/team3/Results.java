
package edu.uis.csc478b.team3;

import java.util.ArrayList;

/**
 * 
 * <h3>Class:</h3> Results <br>
 * <h3>Project:</h3> Plagiarism <br>
 * <h3>Description:</h3> <br>
 * The results class holds all the results for all the tests. <br>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class Results 
{

    /**
     *
     */
    public Results()
    {
       sentenceResults = new ArrayList<>();
       wordResults = new ArrayList<>();
    }

    /**
     *
     */
    public ArrayList< String > sentenceResults;

    /**
     *
     */
    public ArrayList< String > wordResults;
}
