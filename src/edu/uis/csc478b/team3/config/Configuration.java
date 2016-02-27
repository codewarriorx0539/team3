
package edu.uis.csc478b.team3.config;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * <p>
 * <h3>Class:</h3> Configuration
 * <h3>Project:</h3> Plagiarism
 * <h3>Description:</h3>
 * Configuration is the top level XML node. This contains system wide information. A child node is a list of all the </br>
 * Plagiarism tests to preform.
 * </p>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Programmer: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
@XmlRootElement
public class Configuration 
{
    String notes;
    ArrayList<PlagiarismTest> list;
    
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

    @XmlElement
    public void setTests( ArrayList<PlagiarismTest> list )
    {
        this.list = list;
    }
}
