
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.FileProcessor;
import edu.uis.csc478b.team3.config.Config;

/**
 * 
 * <p>
 * <h3>Class:</h3> Filter
 * <h3>Project:</h3> Plagiarism
 * <h3>Description:</h3>
 * Abstract base for all classifiers. This contains the fully processed master and suspect files in memory.
 * </p>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Programmer: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public abstract class Filter 
{
    
    protected FileProcessor master;
    protected FileProcessor suspect;
    protected String configSetup;
    
    public Filter( Config config, FileProcessor master, FileProcessor suspect )
    {
        this.master = master;
        this.suspect = suspect;
        
        configSetup = config.getConfigSetup();
    }
    
    /**
     * The entry point for a classifier to operate on data. Results and configurations are returned.
     * 
     * @return 
     */
    abstract public String runPlagiarismTest();

}
