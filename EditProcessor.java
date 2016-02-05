package edu.uis.csc478b;

import java.util.concurrent.ArrayBlockingQueue;

// If the strings are the same the acumulated distance is 0. Insertion, deletion, and 
// substitution all have costs associated with each action. 
// http://en.wikipedia.org/wiki/Levenshtein_distance


public class EditProcessor implements Runnable 
{
    // Below are the costs of each action. Normally people use 
    // floats but I scaled by x10 and using integers
    // this requires less space and is faster.
    
    static final float INSERT_COST = 1.0f;        // Move Right 1.0
    static final float DELETION_COST = 1.2f;      // Move Down 1.2
    static final float SUBSTITUTION_COST = 1.5f;  // Move Diagonal 1.5
    
    // To optimize space and speed I set the maximum size of the string to create a N x N grid
    // I reuse the grid for subsequent comparisons to reduce allocation costs.
    static int MAX_COMPARE_LEN = 8192;

    float table[][];
    
    // A queue of strings to calculate the distance from our master string.
    ArrayBlockingQueue<String> q;
    
    public String masterString;
    int masterStringLen;
    
    String compareString;
    int compareStringLen;
    
    // Create the grid and calculate the first row and the first column to pump prime the 
    // intial valuses
    public EditProcessor(ArrayBlockingQueue<String> q, String masterString)
    {
        this.q = q;
        this.masterString = masterString;
        masterStringLen = this.masterString.length();
        
        table = new float[masterStringLen + 1 ][MAX_COMPARE_LEN + 1];
        
        for(int i =0; i < masterStringLen + 1; i++)
        {
            table[i][0] = INSERT_COST * i;
        }
        
        for(int j =0; j < MAX_COMPARE_LEN + 1; j++)
        {
            table[0][j] = DELETION_COST * j;
        }
    }
    
    
    // Print the grid 
    private void printGrid()
    {
        for(int i = 0; i < masterStringLen + 1; i++)
        {
            for(int j = 0; j < compareStringLen + 1; j++)
            {
                System.out.printf("%4d\t", table[i][j]);
            }
            System.out.println();
        }    
    }
    
    // Take a string from the Queue and compute the distance between the two strings.
    @Override
    public void run()
    {
        float dist;
        
        while(true)
        {
           compareString = q.poll();
       
           if(compareString != null) 
           {
               
            compareStringLen = compareString.length();
            
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
            
            dist = (float) table[masterStringLen][compareStringLen];
            
            System.out.println(" The Compare String was: " + compareString + "  Distance is: " + dist);
            
           }
        }
    }
    
    public static void main(String[] args) 
    {
        
        
    }
    
}
