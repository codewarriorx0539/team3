
package edu.uis.csc478b.team3;

import java.util.ArrayList;

/**
 * TestPairs: creates all the combinations possible of two files to test for plagiarism.
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a> <br>
 *
 * Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a> <br>
 *
 * Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a> <br>
 *
 */
public class TestPairs 
{

    /**
     * Create pairs of tests.
     * Req 14.2.0
     * 
     * @param files List of files from the configuration file
     * @return Pairs to test
     */
    public ArrayList< TestPair > createPairs( ArrayList< String > files )
    {
        ArrayList< TestPair > pairs = new ArrayList< >();
        
        for(int i = 0; i < files.size(); i++)
        {
            for(int j = i + 1; j < files.size(); j++ )
            {
                pairs.add(new TestPair( files.get(i), files.get(j) ));
            }
        }
        
        return pairs;
    }
    
    /**
     * Test to observe how the combination works.
     * 
     * @param args 
     */
    public static void main(String[] args) 
    {
        ArrayList< String > files = new ArrayList< >();
        TestPairs testPairs = new TestPairs();
        
        files.add("A");
        files.add("B");
        files.add("C");
        files.add("D");
        
        ArrayList< TestPair > pairs = testPairs.createPairs(files);
        
        for(TestPair tp : pairs)
        {
            System.out.println( tp.file1 + " " + tp.file2);
        }
    }
}
