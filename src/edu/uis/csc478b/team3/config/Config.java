
package edu.uis.csc478b.team3.config;

/**
 * 
 * <p>
 * <h3>Class:</h3> Config
 * <h3>Project:</h3> Plagiarism
 * <h3>Description:</h3>
 * Abstract base config class for all classifiers
 * </p>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Programmer: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public abstract class Config 
{
    /**
     * Returns the textual configuration of the classifier
     * @return 
     */
    abstract public String getConfigSetup();
}
