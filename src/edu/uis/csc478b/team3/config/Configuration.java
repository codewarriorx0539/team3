
package edu.uis.csc478b.team3.config;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * <h3>Class:</h3> Configuration <br>
 * <h3>Project:</h3> Plagiarism <br>
 * <h3>Description:</h3> <br>
 * Configuration is the top level XML node. This contains system wide <br>
 * information. A child node is a list of all the Plagiarism tests to be preformed. <br>
 * 
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
@XmlRootElement
public class Configuration 
{
    String notes;
    ArrayList<PlagiarismTest> list;
    
    /**
     * Default notes
     */
    public Configuration()
    {
        notes = "Hello Roger, thanks for allowing us to put a great project together.";
    }

    /**
     *
     * @return
     */
    public String getNotes() 
    {
        return notes;
    }
    
    /**
     *
     * @param notes
     */
    @XmlElement
    public void setNotes(String notes) 
    {
        this.notes = notes;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<PlagiarismTest> getTests()
    {
        return list;
    }

    /**
     *
     * @param list
     */
    @XmlElement
    public void setTests( ArrayList<PlagiarismTest> list )
    {
        this.list = list;
    }
}
