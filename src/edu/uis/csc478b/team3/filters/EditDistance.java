
package edu.uis.csc478b.team3.filters;

import edu.uis.csc478b.team3.FileData;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Edit distance: is a way of quantifying how similar two strings are to one another by counting
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
    
    /**
     * Constructor: Default operation costs.
     * Req 7.3.2.1,7.3.2.2,7.3.2.3
     */
    public EditDistance()
    {
        insertCost = 1.0f;        
        deletionCost = 1.0f;      
        substitutionCost = 1.5f; 
    }
    
    /**
     * Constructor: Set the values for the operations
     * 
     * @param insertCost
     * @param deletionCost
     * @param substitutionCost
     * @throws Exception 
     */
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
     * Calculate the edit distance between two strings. A perfect match returns zero.
     * 
     * @param string1
     * @param string2
     * @return Total distance the strings are different based on the weighted operations
     */
    public float getDistance( String string1, String string2 )
    {
        int string1Len = string1.length();
        int string2Len = string2.length();
        
        
        float table[][] = new float[ string2Len + 1 ][ string1Len + 1];
        
        for(int i =0; i < string2Len + 1; i++)
        {
            table[i][0] = insertCost * i;
        }
        
        for(int j =0; j < string1Len + 1; j++)
        {
            table[0][j] = deletionCost * j;
        }

        for(int i = 1; i < string2Len + 1; i++)
        {
            for(int j = 1; j < string1Len + 1; j++)
            {
                if(string2.charAt(i - 1) == string1.charAt(j - 1))
                {
                    table[i][j] = Math.min(table[i -1 ][j -1], Math.min( table[i ][j -1] + deletionCost , table[i -1 ][j] + insertCost));
                }
                else
                {
                    table[i][j] = Math.min(table[i -1 ][j -1] + substitutionCost, Math.min( table[i ][j -1] + deletionCost  , table[i -1 ][j] + insertCost));        
                }
            }
        }

        return table[string2Len][string1Len];
    }
    
    public float getInsertCost() 
    {
        return insertCost;
    }

    synchronized public void setInsertCost(float insertCost) throws Exception 
    {
        // BOUNDS CHECK
        if(insertCost < 0 )
        {
            throw new Exception("EditDistance::setInsertCost value out of bounds: " + insertCost);
        }
        
        this.insertCost = insertCost;
    }

    public float getDeletionCost() 
    {
        return deletionCost;
    }

    synchronized public void setDeletionCost(float deletionCost) throws Exception 
    {
        // BOUNDS CHECK
        if(deletionCost < 0 )
        {
            throw new Exception("EditDistance::setDeletionCost value out of bounds: " + deletionCost);
        }
        this.deletionCost = deletionCost;
    }

    public float getSubstitutionCost() 
    {
        return substitutionCost;
    }

    synchronized public void setSubstitutionCost(float substitutionCost) throws Exception 
    {
        // BOUNDS CHECK
        if(substitutionCost < 0)
        {
            throw new Exception("EditDistance::setSubstitutionCost value out of bounds: " + substitutionCost);
        }
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
