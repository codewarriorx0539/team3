
package edu.uis.csc478b.team3;

/**
 * Calculate the edit dance of words
 * 
 * @author Jake
 */
public class EditDistance 
{
    private float INSERT_COST;        // Move Right 1.0
    private float DELETION_COST;      // Move Down 1.2
    private float SUBSTITUTION_COST;  // Move Diagonal 1.5
    
    // To optimize space and speed I set the maximum size of the string to create a N x N grid
    // I reuse the grid for subsequent comparisons to reduce allocation costs.
    static final int LENGTH = 8192;
    
    float table[][];
    
    public EditDistance()
    {
        table = new float[LENGTH][LENGTH];
        
        INSERT_COST = 1.0f;        // Move Right 1.0
        DELETION_COST = 1.2f;      // Move Down 1.2
        SUBSTITUTION_COST = 1.5f;  // Move Diagonal 1.5
    }
    
    /**
     * 
     * @param compareString
     * @param masterString
     * @return 
     */
    public float getDistance( String compareString, String masterString )
    {
        int masterStringLen = masterString.length();
        int compareStringLen = compareString.length();
        
        for(int i =0; i < masterStringLen + 1; i++)
        {
            table[i][0] = INSERT_COST * i;
        }
        
        for(int j =0; j < compareStringLen + 1; j++)
        {
            table[0][j] = DELETION_COST * j;
        }

        for(int i = 1; i < masterStringLen + 1; i++)
        {
            for(int j = 1; j < compareStringLen + 1; j++)
            {
                if(masterString.charAt(i - 1) == compareString.charAt(j - 1))
                {
                    table[i][j] = Math.min(table[i -1 ][j -1], Math.min( table[i ][j -1] + DELETION_COST , table[i -1 ][j] + INSERT_COST));
                }
                else
                {
                    table[i][j] = Math.min(table[i -1 ][j -1] + SUBSTITUTION_COST, Math.min( table[i ][j -1] + DELETION_COST  , table[i -1 ][j] + INSERT_COST));        
                }
            }
        }

        return table[masterStringLen][compareStringLen];
    }
    
    public float getINSERT_COST() 
    {
        return INSERT_COST;
    }

    public void setINSERT_COST(float INSERT_COST) 
    {
        this.INSERT_COST = INSERT_COST;
    }

    public float getDELETION_COST() 
    {
        return DELETION_COST;
    }

    public void setDELETION_COST(float DELETION_COST) 
    {
        this.DELETION_COST = DELETION_COST;
    }

    public float getSUBSTITUTION_COST() 
    {
        return SUBSTITUTION_COST;
    }

    public void setSUBSTITUTION_COST(float SUBSTITUTION_COST) 
    {
        this.SUBSTITUTION_COST = SUBSTITUTION_COST;
    }
}
