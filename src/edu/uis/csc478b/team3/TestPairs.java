
package edu.uis.csc478b.team3;

import java.util.ArrayList;

/**
 * 
 * 
 * <h3>Class:</h3> TestPairs <br>
 * <h3>Project:</h3> Plagiarism <br>
 * <h3>Description:</h3> <br>
 * Creates all the combinations possible of two files to test for plagiarism. <br>
 * 
 * 
 * @author Architect: <a href="mailto:jerak2@uis.edu">Jacob Eraklidis</a>
 *
 * @author Documentation: <a href="mailto:rrich9@uis.edu">Ron Richard</a>
 *
 * @author Quality Control: <a href="mailto:jcoat2@uis.edu">Jim Coates</a>
 *
 */
public class TestPairs 
{

    /**
     *
     * @param files
     * @return
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
