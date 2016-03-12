/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
