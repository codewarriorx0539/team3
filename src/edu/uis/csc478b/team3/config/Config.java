
package edu.uis.csc478b.team3.config;

/**
 * 
 * <h3>Class:</h3> Config <br>
 * <h3>Project:</h3> Plagiarism <br>
 * <h3>Description:</h3> <br>
 * Abstract base class for all Configuration classifiers. <br>
 *
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public abstract class Config 
{

    /**
     *
     */
    final protected String TAB = "\t";
    /**
     * Returns the textual configuration of the classifier
     * @return 
     */
    abstract public String getConfigSetup();
    
}
