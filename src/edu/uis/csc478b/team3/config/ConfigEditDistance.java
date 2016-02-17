/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uis.csc478b.team3.config;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Jake
 */
public class ConfigEditDistance extends Config
{

    float INSERT_COST;        
    float DELETION_COST;      
    float SUBSTITUTION_COST; 
    
    public ConfigEditDistance()
    {
        INSERT_COST = 1.0f;        
        DELETION_COST = 1.0f;      
        SUBSTITUTION_COST = 1.5f; 
    }

    public float getINSERT_COST() {
        return INSERT_COST;
    }

    @XmlElement
    public void setINSERT_COST(float INSERT_COST) {
        this.INSERT_COST = INSERT_COST;
    }

    public float getDELETION_COST() {
        return DELETION_COST;
    }

    @XmlElement
    public void setDELETION_COST(float DELETION_COST) {
        this.DELETION_COST = DELETION_COST;
    }

    public float getSUBSTITUTION_COST() {
        return SUBSTITUTION_COST;
    }

    @XmlElement
    public void setSUBSTITUTION_COST(float SUBSTITUTION_COST) {
        this.SUBSTITUTION_COST = SUBSTITUTION_COST;
    }
    
    @Override
    public String getConfigSetup() 
    {
        String setup;
        
        setup = "INSERT_COST: " + INSERT_COST + System.lineSeparator();
        setup = setup + "DELETION_COST: " + DELETION_COST + System.lineSeparator();
        setup = setup + "SUBSTITUTION_COST: " + SUBSTITUTION_COST + System.lineSeparator();
    
        return setup;
    }
    
}
