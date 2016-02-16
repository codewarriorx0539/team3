
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.FileProcessor;
import edu.uis.csc478b.team3.config.Config;


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
    
    abstract public String runPlagiarismTest();

}
