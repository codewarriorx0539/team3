
package edu.uis.csc478b.team3;

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
    ArrayList<ConfigComparison> list;
    
    /**
     * 
     * @param list 
     */
    @XmlElement
    public void setListOfComparisons( ArrayList<ConfigComparison> list )
    {
        this.list = list;
    }
    
    
}
