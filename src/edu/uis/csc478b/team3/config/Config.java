
package edu.uis.csc478b.team3.config;

/**
 * Abstract base class for all Configuration classifiers.
 *
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public abstract class Config 
{
    final protected String TAB = "\t";
    
    /**
     * Returns the textual configuration of the classifier
     * @return 
     */
    abstract public String getConfigSetup();
    
}
