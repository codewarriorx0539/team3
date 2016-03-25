
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.FileData;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Edit distance is a way of quantifying how similar two strings are to one another by counting
 * the minimum number of operations required to transform one string into the other.
 * https://en.wikipedia.org/wiki/Edit_distance
 * 
 * The solution is a classical dynamic programming problem. Below are the operations: <br>
 * <ul>
 *  <li>Insertion</li>
 *  <li>Deletion</li>
 *  <li>Substitution</li>
 * </ul>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
@XmlRootElement
public class EditDistance extends PlagiarismFilter
{
    float insertCost; 
    float deletionCost;
    float substitutionCost;
    
    final protected String INSERT = "insertCost: ";
    final protected String DELETION = "deletionCost: ";
    final protected String SUBSTITUTION = "substitutionCost: ";
    
    public EditDistance()
    {
        insertCost = 1.0f;        
        deletionCost = 1.0f;      
        substitutionCost = 1.5f; 
    }
    

    public EditDistance(    float insertCost, 
                            float deletionCost, 
                            float substitutionCost) throws Exception
    {
        this.insertCost = insertCost;        
        this.deletionCost = deletionCost;      
        this.substitutionCost = substitutionCost; 
        
        // BOUNDS CHECK
        if(insertCost < 0 || deletionCost < 0 || substitutionCost < 0)
        {
            throw new Exception("EditDistance::EditDistance value out of bounds");
        }
    }
    
    /**
     * Calculate the distance between two strings. A perfect match returns zero
     * 
     * @param compareString String to compare
     * @param masterString String to compare
     * @return Total distance the strings are different based on the weighted operations
     */
    public float getDistance( String compareString, String masterString )
    {
        int masterStringLen = masterString.length();
        int compareStringLen = compareString.length();
        
        float table[][] = new float[ masterStringLen + 1 ][ compareStringLen + 1];
        
        for(int i =0; i < masterStringLen + 1; i++)
        {
            table[i][0] = insertCost * i;
        }
        
        for(int j =0; j < compareStringLen + 1; j++)
        {
            table[0][j] = deletionCost * j;
        }

        for(int i = 1; i < masterStringLen + 1; i++)
        {
            for(int j = 1; j < compareStringLen + 1; j++)
            {
                if(masterString.charAt(i - 1) == compareString.charAt(j - 1))
                {
                    table[i][j] = Math.min(table[i -1 ][j -1], Math.min( table[i ][j -1] + deletionCost , table[i -1 ][j] + insertCost));
                }
                else
                {
                    table[i][j] = Math.min(table[i -1 ][j -1] + substitutionCost, Math.min( table[i ][j -1] + deletionCost  , table[i -1 ][j] + insertCost));        
                }
            }
        }

        return table[masterStringLen][compareStringLen];
    }
    
    public float getInsertCost() 
    {
        return insertCost;
    }

    public void setInsertCost(float insertCost) 
    {
        this.insertCost = insertCost;
    }

    public float getDeletionCost() 
    {
        return deletionCost;
    }

    public void setDeletionCost(float deletionCost) 
    {
        this.deletionCost = deletionCost;
    }

    public float getSubstitutionCost() 
    {
        return substitutionCost;
    }

    public void setSubstitutionCost(float substitutionCost) 
    {
        this.substitutionCost = substitutionCost;
    }
    
    @Override
    public String getConfigSetup() 
    {
        String setup = TAB + INSERT + insertCost + System.lineSeparator();
        setup = setup + TAB + DELETION + deletionCost + System.lineSeparator();
        setup = setup + TAB + SUBSTITUTION + substitutionCost + System.lineSeparator();
    
        return setup;
    }

    @Override
    public String exec( FileData data1, FileData data2 ) throws Exception 
    {
        throw new UnsupportedOperationException("NOT SUPPORTED AS A FILTER");
    }
}
