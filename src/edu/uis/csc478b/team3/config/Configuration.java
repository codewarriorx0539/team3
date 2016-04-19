
package edu.uis.csc478b.team3.config;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Configuration: is the top level XML node. This contains system wide 
 * information. A child node is a list of all the Plagiarism tests to be preformed
 * and related configuration.
 * Req 8.1.0
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
@XmlRootElement
public class Configuration 
{
    // Holds any notes
    String notes;
    // Holds the list of tests - Req 6.0.0
    ArrayList<PlagiarismTest> list;
        
    /**
     * Set the notes
     */
    public Configuration()
    {
        notes = "Hello Roger, thanks for allowing us to put a great project together.";
    }

    public String getNotes() 
    {
        return notes;
    }
    
    @XmlElement
    public void setNotes(String notes) 
    {
        this.notes = notes;
    }
    
    public ArrayList<PlagiarismTest> getTests()
    {
        return list;
    }

    @XmlElement(name = "test")
    public void setTests( ArrayList<PlagiarismTest> list )
    {
        this.list = list;
    }

}
