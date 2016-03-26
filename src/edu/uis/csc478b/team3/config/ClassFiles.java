
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classes file holds all the class names for the filters (due to abstract filter types).
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
@XmlRootElement
public class ClassFiles 
{
    Class[] classes;
    
    public ClassFiles()
    {
        
    }
    
    public ClassFiles(Class[] classes)
    {
        this.classes = classes;
    }
            
    public Class[] getClasses() 
    {
        return classes;
    }

    @XmlElement
    public void setClasses(Class[] classes) 
    {
        this.classes = classes;
    }
}
