
package edu.uis.csc478b.team3;

import edu.uis.csc478b.team3.config.ConfigEditDistance;

/**
 * 
 * <p>
 * <h3>Class:</h3> EditDistance
 * <h3>Project:</h3> Plagiarism
 * <h3>Description:</h3>
 *
 * Edit distance is a way of quantifying how dissimilar two strings are to one another by counting </br>
 * the minimum number of operations required to transform one string into the other. https://en.wikipedia.org/wiki/Edit_distance </br>
 * Operations: </br>
 * <ul>
 *  <li>Insertion</li>
 *  <li>Deletion</li>
 *  <li>Substitution</li>
 * </ul>
 * </p>
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Programmer: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class EditDistance 
{
    final private float insertCost; 
    final private float deletionCost;
    final private float substitutionCost;
        
    public EditDistance(ConfigEditDistance config)
    {
        insertCost = config.getInsertCost(); 
        deletionCost = config.getDeletionCost();
        substitutionCost = config.getSubstitutionCost();
    }
    
    /**
     * Calculate the distance between two strings. A perfect match returns zero
     * 
     * @param compareString
     * @param masterString
     * @return 
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
}
