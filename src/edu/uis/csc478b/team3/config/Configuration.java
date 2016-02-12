
package edu.uis.csc478b.team3.config;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Use JAXB to Serialize/Deserialize a configuration file
 * 
 * @author Jake
 */
@XmlRootElement
public class Configuration 
{
    String notes;
    ArrayList<PlagiarismTest> list;
    
    public Configuration()
    {
        notes = "hello";
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
    
    
    public ArrayList<PlagiarismTest> getConfigs()
    {
        return list;
    }

    @XmlElement
    public void setConfigs( ArrayList<PlagiarismTest> list )
    {
        this.list = list;
    }
}
