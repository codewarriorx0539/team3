
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * 
 * <h3>Class:</h3> ConfigEditDistance <br>
 * <h3>Project:</h3> Plagiarism <br>
 * <h3>Description:</h3> <br>
 * Configuration for any classifier using the edit distance algorithm <br>
 * 
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class ConfigEditDistance extends Config
{

    float insertCost;        
    float deletionCost;      
    float substitutionCost; 
    
    /**
     *
     */
    final protected String INSERT = "insertCost: ";

    /**
     *
     */
    final protected String DELETION = "deletionCost: ";

    /**
     *
     */
    final protected String SUBSTITUTION = "substitutionCost: ";
    
    /**
     * Default values for edit distance
     */
    public ConfigEditDistance()
    {
        insertCost = 1.0f;        
        deletionCost = 1.0f;      
        substitutionCost = 1.5f; 
    }
    
    /**
     * Copy constructor
     * @param config 
     */
    public ConfigEditDistance(ConfigEditDistance config)
    {
        this.insertCost = config.getInsertCost();        
        this.deletionCost = config.getDeletionCost();      
        this.substitutionCost = config.getSubstitutionCost();
    }
    
    /**
     * Set values of edit distance.
     * 
     * @param insertCost
     * @param deletionCost
     * @param substitutionCost 
     */
    public ConfigEditDistance(float insertCost, float deletionCost, float substitutionCost)
    {
        this.insertCost = insertCost;        
        this.deletionCost = deletionCost;      
        this.substitutionCost = substitutionCost; 
    }

    /**
     *
     * @return
     */
    public float getInsertCost() 
    {
        return insertCost;
    }

    /**
     *
     * @param insertCost
     */
    @XmlElement
    public void setInsertCost(float insertCost) 
    {
        this.insertCost = insertCost;
    }

    /**
     *
     * @return
     */
    public float getDeletionCost() 
    {
        return deletionCost;
    }

    /**
     *
     * @param deletionCost
     */
    @XmlElement
    public void setDeletionCost(float deletionCost) 
    {
        this.deletionCost = deletionCost;
    }

    /**
     *
     * @return
     */
    public float getSubstitutionCost() 
    {
        return substitutionCost;
    }

    /**
     *
     * @param substitutionCost
     */
    @XmlElement
    public void setSubstitutionCost(float substitutionCost) 
    {
        this.substitutionCost = substitutionCost;
    }
    
    @Override
    public String getConfigSetup() 
    {
        String setup = TAB + INSERT + insertCost + System.lineSeparator();
        setup = setup + TAB + DELETION + deletionCost + System.lineSeparator();
        setup = setup + TAB + SUBSTITUTION + substitutionCost + System.lineSeparator();
    
        return setup;
    }
    
}
