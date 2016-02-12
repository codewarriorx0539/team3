
package edu.uis.csc478b.team3.config;

import edu.uis.csc478b.team3.PlagiarismTest;
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
    ArrayList<PlagiarismTest> list;
    
    public ArrayList<PlagiarismTest> getConfigs()
    {
        return list;
    }

    @XmlElement
    public void setListOfComparisons( ArrayList<PlagiarismTest> list )
    {
        this.list = list;
    }
}
