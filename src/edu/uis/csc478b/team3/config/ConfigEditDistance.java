
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

/**
 * Configuration for any classifier using the edit distance algorithm
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public class ConfigEditDistance extends Config
{

    float insertCost;        
    float deletionCost;      
    float substitutionCost; 
    
    final protected String INSERT = "insertCost: ";
    final protected String DELETION = "deletionCost: ";
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
     * 
     * @param config 
     */
    public ConfigEditDistance(ConfigEditDistance config) throws Exception
    {
        insertCost = config.getInsertCost();        
        deletionCost = config.getDeletionCost();      
        substitutionCost = config.getSubstitutionCost();
        
        // BOUNDS CHECK
        if(insertCost < 0 || deletionCost < 0 || substitutionCost < 0)
        {
            throw new Exception("ConfigEditDistance::ConfigEditDistance value out of bounds");
        }
    }
    
    /**
     * Set values of edit distance.
     * 
     * @param insertCost
     * @param deletionCost
     * @param substitutionCost 
     */
    public ConfigEditDistance(float insertCost, float deletionCost, float substitutionCost) throws Exception
    {
        this.insertCost = insertCost;        
        this.deletionCost = deletionCost;      
        this.substitutionCost = substitutionCost; 
        
        // BOUNDS CHECK
        if(insertCost < 0 || deletionCost < 0 || substitutionCost < 0)
        {
            throw new Exception("ConfigEditDistance::ConfigEditDistance value out of bounds");
        }
    }

    public float getInsertCost() 
    {
        return insertCost;
    }

    @XmlElement
    public void setInsertCost(float insertCost) 
    {
        this.insertCost = insertCost;
    }

    public float getDeletionCost() 
    {
        return deletionCost;
    }

    @XmlElement
    public void setDeletionCost(float deletionCost) 
    {
        this.deletionCost = deletionCost;
    }

    public float getSubstitutionCost() 
    {
        return substitutionCost;
    }

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
